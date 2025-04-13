package com.dafnis.AppSpringMongoDB.controllers;

import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dafnis.AppSpringMongoDB.models.Coord;
import com.dafnis.AppSpringMongoDB.models.Theater;
import com.dafnis.AppSpringMongoDB.services.ServicioTheaters;

@RestController
@RequestMapping("/theaters")
public class ControladorTheater {
    
    private ServicioTheaters servicioTheaters;
    private ControladorTheater(ServicioTheaters servicioTheaters){
        this.servicioTheaters = servicioTheaters;
    }

    @GetMapping
    public ResponseEntity<Page<Theater>> getAll(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicioTheaters.obtenerTodos(numPagina, tamañoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(servicioTheaters.obtenTheater(id));
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheater(@PathVariable int id, @RequestBody Theater theater){
        try{
            return ResponseEntity.ok().body(servicioTheaters.actualizarTheater(id, theater));
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheater(@PathVariable int id){
        try{
            servicioTheaters.eliminarTheater(id);
            return ResponseEntity.ok().build();
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/closest")
    public ResponseEntity<Coord> findClosestTheater(@RequestBody Coord coord){
        return ResponseEntity.ok().body(servicioTheaters.encontrarTeatroCercano(coord));
    }
    
}
