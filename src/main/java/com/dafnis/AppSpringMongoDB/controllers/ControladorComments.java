package com.dafnis.AppSpringMongoDB.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dafnis.AppSpringMongoDB.models.Comment;
import com.dafnis.AppSpringMongoDB.models.CommentBody;
import com.dafnis.AppSpringMongoDB.models.CommentRequest;
import com.dafnis.AppSpringMongoDB.services.ServicioComments;

@RestController
public class ControladorComments {

    private ServicioComments servicio;
    private ControladorComments(ServicioComments servicio){
        this.servicio = servicio;
    }

    @GetMapping("/comments")
    public ResponseEntity<Page<Comment>> getAllComments(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicio.obtenerTodas(numPagina, tamañoPagina));
    }

    @PostMapping("/movies/{id}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable String id, @RequestBody CommentRequest comment){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardarComment(id, comment));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getComment(@PathVariable String id){
        try{
            return ResponseEntity.ok().body(servicio.obtenerComment(id));
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id, @RequestBody CommentBody commentBody){
        try{
            return ResponseEntity.ok().body(servicio.actualizarComment(id, commentBody));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id){
        try{
            servicio.eliminarComment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/comments/movie/{movieId}")
    public ResponseEntity<List<Comment>> getCommentsByMovieId(@PathVariable String movieId){
        ObjectId movieObjectId = new ObjectId(movieId);
        return ResponseEntity.ok().body(servicio.obtenerCommentsPorMovieId(movieObjectId));
    }

    @GetMapping("/comments/user/{email}")
    public ResponseEntity<List<Comment>> getCommentsByUserEmail(@PathVariable String email){
        return ResponseEntity.ok().body(servicio.obtenerCommentsPorUserEmail(email));
    }
    
}
