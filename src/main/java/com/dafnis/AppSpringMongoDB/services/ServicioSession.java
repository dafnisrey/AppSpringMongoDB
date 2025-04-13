package com.dafnis.AppSpringMongoDB.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.dafnis.AppSpringMongoDB.models.Session;
import com.dafnis.AppSpringMongoDB.repo.RepositorioSession;

@Service
public class ServicioSession {
    
    private RepositorioSession repositorioSession;
    private ServicioSession(RepositorioSession repositorioSession){
        this.repositorioSession = repositorioSession;
    }

    public List<Session> obtenerTodos(){
        return repositorioSession.findAll();
    }
}
