package com.example.BookMyShow.Repostiory;

import com.example.BookMyShow.model.Movie_Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<Movie_Show, Long> {
}
