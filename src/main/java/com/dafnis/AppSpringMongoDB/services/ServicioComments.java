package com.dafnis.AppSpringMongoDB.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.dafnis.AppSpringMongoDB.models.Comment;
import com.dafnis.AppSpringMongoDB.models.CommentBody;
import com.dafnis.AppSpringMongoDB.models.CommentRequest;
import com.dafnis.AppSpringMongoDB.repo.RepositorioComment;

@Service
public class ServicioComments {
    
    private RepositorioComment repositorio;
    private ServicioComments(RepositorioComment repositorio){
        this.repositorio = repositorio;
    }

    public Page<Comment> obtenerTodas(int numPagina, int tamañoPagina){
        Pageable pageable = PageRequest.of(numPagina - 1, tamañoPagina);
        return repositorio.findAll(pageable);
    }

    public Comment guardarComment(String movieId, CommentRequest comment){
        ObjectId movieObjectId = new ObjectId(movieId);
        Comment comm = new Comment();
        comm.setName(comment.getName());
        comm.setEmail(comment.getEmail());
        comm.setText(comment.getText());
        comm.setDate(new Date());
        comm.setMovieId(movieObjectId);
        return repositorio.save(comm);
    }

    public Comment obtenerComment(String id){
        Optional<Comment> opt = repositorio.findById(id);
        return opt.orElseThrow(() -> new NoSuchElementException("Comentario no encontrado."));
    }

    public Comment actualizarComment(String id, CommentBody commentBody)throws NoSuchElementException{
        Optional<Comment> opt = repositorio.findById(id);
        if(opt.isPresent()){
            Comment comment = opt.get();
            comment.setText(commentBody.getText());
            repositorio.save(comment);
            return comment;
        }else{
            throw new NoSuchElementException("Comentario no encontrado.");
        }
    }

    public void eliminarComment(String id)throws IllegalArgumentException{
        repositorio.deleteById(id);
    }

    public List<Comment> obtenerCommentsPorMovieId(ObjectId movieId){
        return repositorio.findByMovieId(movieId);
    }

    public List<Comment> obtenerCommentsPorUserEmail(String email){
        return repositorio.findByEmail(email);
    }
}
