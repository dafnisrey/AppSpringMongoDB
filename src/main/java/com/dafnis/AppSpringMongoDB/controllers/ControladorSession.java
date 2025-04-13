package com.dafnis.AppSpringMongoDB.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dafnis.AppSpringMongoDB.models.Session;
import com.dafnis.AppSpringMongoDB.services.ServicioSession;

@RestController
@RequestMapping("/session")
public class ControladorSession {

    private ServicioSession servicioSession;
    private ControladorSession(ServicioSession servicioSession){
        this.servicioSession = servicioSession;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Session>> getAll(){
        return ResponseEntity.ok().body(servicioSession.obtenerTodos());
    }
}
