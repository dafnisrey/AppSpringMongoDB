package com.dafnis.AppSpringMongoDB.services;

import com.dafnis.AppSpringMongoDB.models.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.dafnis.AppSpringMongoDB.repo.RepositorioMovie;

@Service
public class ServicioMovies {
    @Autowired
    private MongoTemplate mongoTemplate;

    private RepositorioMovie repositorioMovie;
    ServicioMovies(RepositorioMovie repositorioMovie){
        this.repositorioMovie = repositorioMovie;
    }

    public Page<Movie> obtenerTodas(int numPagina, int tamañoPagina){
        Pageable pageable = PageRequest.of(numPagina, tamañoPagina);
        Page<Movie> page = repositorioMovie.findAll(pageable);
        return page;
    }

    public Optional<Movie> obtenerPeliculaPorId(String id){
        return repositorioMovie.findById(id);
    }

    public Optional<Movie> obtenerPeliculaPorTitulo(String title){
        Optional<Movie> opt = repositorioMovie.findByTitle(title);
        return opt;
    }

    public Page<Movie> obtenerPeliculasPorGenero(String genre, int numPagina, int tamañoPagina){
        Pageable pageable = PageRequest.of(numPagina, tamañoPagina);
        return repositorioMovie.findByGenres(genre, pageable);
    }

    public List<Movie> obtenerPeliculasPorActor(String actor){
        return repositorioMovie.findByCast(actor);
    }

    public List<Movie> obtenerPeliculasPorDirector(String director){
        return repositorioMovie.findByDirectors(director);
    }
    
    public List<String> getDistinctGenres(){
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("genres"),
            Aggregation.group("genres"),
            Aggregation.sort(Sort.Direction.ASC, "_id")
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "movies", Document.class);
        return results.getMappedResults().stream().map(doc -> doc.getString("_id")).collect(Collectors.toList());
    }

    public UnaPagina<String> getDistinctActors(int numPagina, int tamañoPagina){
        int skip = numPagina * tamañoPagina;
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("cast"),
            Aggregation.group("cast"),
            Aggregation.sort(Sort.Direction.ASC, "_id"),
            Aggregation.skip(skip),
            Aggregation.limit(tamañoPagina)
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "movies", Document.class);
        List<String> content = results.getMappedResults().stream().map(doc -> doc.getString("_id")).collect(Collectors.toList());
        Aggregation aggregationContar = Aggregation.newAggregation(
            Aggregation.unwind("cast"),
            Aggregation.group("cast"),
            Aggregation.count().as("total")
        );
        Document cuenta = mongoTemplate.aggregate(aggregationContar, "movies", Document.class).getUniqueMappedResult();
        int total = cuenta != null ? cuenta.getInteger("total") : 0;
        return new UnaPagina<String>(content, numPagina, tamañoPagina, total);
    }

    public UnaPagina<String> getDistinctDirectors(int numPagina, int tamañoPagina){
        int skip = numPagina * tamañoPagina;
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("directors"),
            Aggregation.group("directors"),
            Aggregation.sort(Sort.Direction.ASC, "_id"),
            Aggregation.skip(skip),
            Aggregation.limit(tamañoPagina)
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "movies", Document.class);
        List<String> content = results.getMappedResults().stream().map(doc -> doc.getString("_id")).collect(Collectors.toList());
        Aggregation aggregationContar = Aggregation.newAggregation(
            Aggregation.unwind("directors"),
            Aggregation.group("directors"),
            Aggregation.count().as("total")
        );
        Document cuenta = mongoTemplate.aggregate(aggregationContar, "movies", Document.class).getUniqueMappedResult();
        int total = cuenta != null ? cuenta.getInteger("total") : 0;
        return new UnaPagina<String>(content, numPagina, tamañoPagina, total);
    }
    
}
