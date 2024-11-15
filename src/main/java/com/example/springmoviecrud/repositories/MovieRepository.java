package com.example.springmoviecrud.repositories;

import com.example.springmoviecrud.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Mencari film berdasarkan judul
    List<Movie> findByTitle(String title);

    // Mencari film berdasarkan genre
    List<Movie> findByGenre(String genre);

    // Mencari film dengan rating lebih besar atau sama dengan nilai tertentu
    List<Movie> findByRatingGreaterThanEqual(Double rating);
}

