package com.dafnis.AppSpringMongoDB.repo;

import com.dafnis.AppSpringMongoDB.models.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMovie extends MongoRepository<Movie, String>{

    Page<Movie> findAll(Pageable pageable);
    public Optional<Movie> findByTitle(String title);
    public Page<Movie> findByGenres(String genres, Pageable pageable);
    public List<Movie> findByCast(String cast);
    public List<Movie> findByDirectors(String directors);

}
