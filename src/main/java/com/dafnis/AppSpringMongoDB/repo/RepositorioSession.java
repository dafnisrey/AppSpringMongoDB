package com.dafnis.AppSpringMongoDB.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dafnis.AppSpringMongoDB.models.Session;

@Repository
public interface RepositorioSession extends MongoRepository<Session, String>{

    Page<Session> findAll(Pageable pageable);
    
}
