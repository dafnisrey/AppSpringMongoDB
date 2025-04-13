package com.dafnis.AppSpringMongoDB.repo;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dafnis.AppSpringMongoDB.models.User;

@Repository
public interface RepositorioUser extends MongoRepository<User, String>{

    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);

    
    
}
