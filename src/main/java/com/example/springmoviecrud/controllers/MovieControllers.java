package com.example.springmoviecrud.controllers;

import com.example.springmoviecrud.models.Movie;
import com.example.springmoviecrud.repositories.MovieRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieControllers {

    private final MovieRepository movieRepository;

    public MovieControllers(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

//    private static final Logger logger = LoggerFactory.getLogger(MovieControllers.class);

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movieReq) {
        try {
            Movie savedMovie = movieRepository.save(movieReq);
            return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie MovieReq) {
        Optional<Movie> existingMovie = movieRepository.findById(id);

        if (existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            movie.setTitle(MovieReq.getTitle());
            movie.setDescription(MovieReq.getDescription());
            movie.setReleaseDate(MovieReq.getReleaseDate());
            movie.setDuration(MovieReq.getDuration());
            movie.setGenre(MovieReq.getGenre());
            movie.setDirector(MovieReq.getDirector());
            movie.setRating(MovieReq.getRating());

            Movie updatedMovie = movieRepository.save(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        try {
            movieRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

//        logger.info("Search Parameters received: title={}, genre={}, minRating={}, startDate={}, endDate={}",
//                title, genre, minRating, startDate, endDate);

        List<Movie> movies = movieRepository.advancedSearch(title, genre, minRating, startDate, endDate);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

}
