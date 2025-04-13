package com.dafnis.AppSpringMongoDB.controllers;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dafnis.AppSpringMongoDB.models.Movie;
import com.dafnis.AppSpringMongoDB.models.UnaPagina;
import com.dafnis.AppSpringMongoDB.services.ServicioMovies;

@RestController
@RequestMapping("/movies")
public class ControladorMovies {

    private ServicioMovies servicio;
    ControladorMovies(ServicioMovies servicio){
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> getAllMovies(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        Page<Movie> page = servicio.obtenerTodas(numPagina, tamañoPagina);
        return new ResponseEntity<Page<Movie>>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        if(ObjectId.isValid(id)){
            Optional<Movie> opt = servicio.obtenerPeliculaPorId(id);
            if(opt.isPresent()){
                return ResponseEntity.ok().body(opt.get());
            }else{
                return ResponseEntity.notFound().build();
            }   
        }else{
            return ResponseEntity.badRequest().body("ID con formato no válido.");
        }
    }

    @GetMapping("/title")
    public ResponseEntity<?> getByTitle(@RequestParam String title){
        Optional<Movie> opt = servicio.obtenerPeliculaPorTitulo(title);
        if(opt.isPresent()){
            return ResponseEntity.ok().body(opt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Page<Movie>> getByGenre(@PathVariable String genre, @RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicio.obtenerPeliculasPorGenero(genre, numPagina, tamañoPagina));
    }

    @GetMapping("/actor/{actor}")
    public ResponseEntity<List<Movie>> getByCast(@PathVariable String actor){
        return ResponseEntity.ok().body(servicio.obtenerPeliculasPorActor(actor));
    }

    @GetMapping("/director/{director}")
    public ResponseEntity<List<Movie>> getByDirector(@PathVariable String director){
        return ResponseEntity.ok().body(servicio.obtenerPeliculasPorDirector(director));
    }

    @GetMapping("/genre/todos")
    public ResponseEntity<List<String>> getDistinctGenres(){
        return ResponseEntity.ok().body(servicio.getDistinctGenres());
    }

    @GetMapping("/actor/todos")
    public ResponseEntity<UnaPagina<String>> getDistinctActors(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicio.getDistinctActors(numPagina, tamañoPagina));
    }

    @GetMapping("/director/todos")
    public ResponseEntity<UnaPagina<String>> getDistinctDirectors(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicio.getDistinctDirectors(numPagina, tamañoPagina));
    }
}
