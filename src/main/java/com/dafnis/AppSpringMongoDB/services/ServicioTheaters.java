package com.dafnis.AppSpringMongoDB.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;
import com.dafnis.AppSpringMongoDB.aux.TheaterLocator;
import com.dafnis.AppSpringMongoDB.models.Coord;
import com.dafnis.AppSpringMongoDB.models.Coordinates;
import com.dafnis.AppSpringMongoDB.models.Theater;
import com.dafnis.AppSpringMongoDB.repo.RepositorioTheater;

@Service
public class ServicioTheaters {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    private RepositorioTheater repositorioTheater;
    private ServicioTheaters(RepositorioTheater repositorioTheater){
        this.repositorioTheater = repositorioTheater;
    }

    public Page<Theater> obtenerTodos(int numPagina, int tamañoPagina){
        Pageable pageable = PageRequest.of(numPagina, tamañoPagina);
        return repositorioTheater.findAll(pageable);
    }

    public Theater obtenTheater(int id)throws IllegalArgumentException{
        return repositorioTheater.findByTheaterId(id).orElseThrow(() -> new IllegalArgumentException("Theater no encontrado."));
    }
    
    public Theater actualizarTheater(int id, Theater theater)throws NoSuchElementException{
        Optional<Theater> opt = repositorioTheater.findByTheaterId(id);
        if(opt.isPresent()){
            Theater th = opt.get();
            th.setLocation(theater.getLocation());
            repositorioTheater.save(th);
            return th;
        }else{
            throw new NoSuchElementException("Theater no encontrado.");
        }
    }

    public void eliminarTheater(int id)throws NoSuchElementException{
        repositorioTheater.deleteByTheaterId(id);
    }

    public Coord encontrarTeatroCercano(Coord coord){
        ProjectionOperation projectStage = Aggregation.project().andExclude("_id").and("location.geo.coordinates").as("coordinates");
        Aggregation aggregation = Aggregation.newAggregation(projectStage);
        AggregationResults<Coordinates> results = mongoTemplate.aggregate(aggregation, "theaters", Coordinates.class);
        List<Coordinates> lista = results.getMappedResults();
        List<Coord> listaCoords = new ArrayList<>();
        for(Coordinates c : lista){
            Coord l = new Coord(c.getCoordinates().get(0), c.getCoordinates().get(1));
            listaCoords.add(l);
        }
        return TheaterLocator.findClosestTheater(coord, listaCoords);
    }
}
