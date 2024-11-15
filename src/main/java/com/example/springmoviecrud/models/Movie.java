package com.example.springmoviecrud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Check(constraints = "rating >= 1.0 AND rating <= 10.0") // Menambahkan constraint CHECK
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Integer duration; // in minutes

    @Column(nullable = false, length = 255)
    private String genre;

    @Column(nullable = false, length = 255)
    private String director;

    @Column(nullable = false, precision = 3, scale = 1)
    @DecimalMin(value = "1.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    private BigDecimal rating;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE) // Prevent modification from outside
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column()
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
