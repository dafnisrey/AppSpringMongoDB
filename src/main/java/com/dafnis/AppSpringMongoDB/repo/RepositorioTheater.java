package com.dafnis.AppSpringMongoDB.repo;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dafnis.AppSpringMongoDB.models.Theater;

@Repository
public interface RepositorioTheater extends MongoRepository<Theater, String>{

    Page<Theater> findAll(Pageable pageable);
    Optional<Theater> findByTheaterId(Integer id);
    void deleteByTheaterId(Integer id);

    
}
