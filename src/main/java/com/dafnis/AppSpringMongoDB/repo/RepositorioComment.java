package com.dafnis.AppSpringMongoDB.repo;

import com.dafnis.AppSpringMongoDB.models.Comment;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioComment extends MongoRepository<Comment, String>{
    
    Page<Comment> findAll(Pageable pageable);
    List<Comment> findByMovieId(ObjectId movieId);
    List<Comment> findByEmail(String email);
    
}
