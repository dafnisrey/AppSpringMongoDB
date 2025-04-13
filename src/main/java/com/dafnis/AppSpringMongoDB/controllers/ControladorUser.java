package com.dafnis.AppSpringMongoDB.controllers;

import java.util.Collections;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dafnis.AppSpringMongoDB.aux.JwtUtil;
import com.dafnis.AppSpringMongoDB.models.User;
import com.dafnis.AppSpringMongoDB.services.ServicioUsers;

@RestController
@RequestMapping("/users")
public class ControladorUser {

    private final JwtUtil jwtUtil;
    private ServicioUsers servicioUsers;
    private ControladorUser(ServicioUsers servicioUsers, JwtUtil jwtUtil){
        this.servicioUsers = servicioUsers;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAll(@RequestParam int numPagina, @RequestParam int tamañoPagina){
        return ResponseEntity.ok().body(servicioUsers.obtenerTodos(numPagina, tamañoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        try{
            return ResponseEntity.ok().body(servicioUsers.obtenerPorId(id));
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            return ResponseEntity.ok().body(servicioUsers.guardarNuevoUser(user));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user){
        try{
            return ResponseEntity.ok().body(servicioUsers.actualizarUser(id, user));
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        try{
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok().body(servicioUsers.consultarUserPorEmail(email));
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        try{
            if(servicioUsers.loginUser(email, password)){
                String token = jwtUtil.generarToken(email);
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
