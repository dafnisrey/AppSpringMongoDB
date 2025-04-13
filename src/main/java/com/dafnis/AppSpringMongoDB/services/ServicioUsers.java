package com.dafnis.AppSpringMongoDB.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dafnis.AppSpringMongoDB.models.User;
import com.dafnis.AppSpringMongoDB.repo.RepositorioUser;

@Service
public class ServicioUsers {
    
    private RepositorioUser repositorioUser;
    private ServicioUsers(RepositorioUser repositorioUser){
        this.repositorioUser = repositorioUser;
    }

    public Page<User> obtenerTodos(int numPagina, int tamañoPagina){
        Pageable pageable = PageRequest.of(numPagina, tamañoPagina);
        return repositorioUser.findAll(pageable);
    }

    public User obtenerPorId(String id) throws NoSuchElementException{
        Optional<User> opt = repositorioUser.findById(id);
        return opt.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));
    }

    public User guardarNuevoUser(User user)throws IllegalArgumentException{
        try{
            return repositorioUser.save(user);
        }catch(IllegalArgumentException e){
            throw e;
        }
    }

    public User actualizarUser(String id, User user){
        Optional<User> opt = repositorioUser.findById(id);
        if(opt.isPresent()){
            User userExistente = opt.get();
            userExistente.setEmail(user.getEmail());
            userExistente.setName(user.getName());
            userExistente.setPassword(user.getPassword());
            return repositorioUser.save(userExistente);
        }else{
            throw new IllegalArgumentException("Usuario no existente.");
        }
    }

    public void eliminarUser(String id)throws IllegalArgumentException{
        try{
            repositorioUser.deleteById(id);
        }catch(IllegalArgumentException e){
            throw e;
        }
    }

    public User consultarUserPorEmail(String email)throws IllegalArgumentException{
        return repositorioUser.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    }

    public boolean loginUser(String email, String password)throws IllegalArgumentException{
        Optional<User> opt = repositorioUser.findByEmail(email);
        if(opt.isPresent()){
            User user = opt.get();
            if(user.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }else{
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }
    
}
