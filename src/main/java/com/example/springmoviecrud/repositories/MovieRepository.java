package com.example.springmoviecrud.repositories;

import com.example.springmoviecrud.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE " +
            "(:genre IS NULL OR LOWER(m.genre) LIKE LOWER(CONCAT('%', :genre, '%'))) AND " +
            "(:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:minRating IS NULL OR m.rating >= :minRating) AND " +
            "(:startDate IS NULL OR m.releaseDate >= :startDate) AND " +
            "(:endDate IS NULL OR m.releaseDate <= :endDate)")
    List<Movie> advancedSearch(String title, String genre, BigDecimal minRating, LocalDate startDate, LocalDate endDate);

}
