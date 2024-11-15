package com.example.springmoviecrud.seeders;

import com.example.springmoviecrud.models.Movie;
import com.example.springmoviecrud.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class MovieSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public MovieSeeder(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (movieRepository.count() == 0) {
            Movie movie1 = new Movie(
                    null,
                    "The Dark Knight",
                    "Batman faces his greatest challenge yet, the Joker.",
                    LocalDate.of(2008, 7, 18),
                    152,
                    "Action, Drama, Thriller",
                    "Christopher Nolan",
                    new BigDecimal("9.0"),
                    null,
                    null
            );

            Movie movie2 = new Movie(
                    null,
                    "Inception",
                    "A skilled thief is given a chance to have his criminal record erased if he can successfully perform an inception.",
                    LocalDate.of(2010, 7, 16),
                    148,
                    "Action, Sci-Fi, Thriller",
                    "Christopher Nolan",
                    new BigDecimal("8.8"),
                    null,
                    null
            );

            Movie movie3 = new Movie(
                    null,
                    "The Matrix",
                    "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                    LocalDate.of(1999, 3, 31),
                    136,
                    "Action, Sci-Fi",
                    "The Wachowskis",
                    new BigDecimal("8.7"),
                    null,
                    null
            );

            movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));

            System.out.println("[*] Data movie berhasil di-seed");
        }
    }
}
