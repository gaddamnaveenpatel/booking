package com.example.BookMyShow.Repostiory;

import com.example.BookMyShow.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepo extends JpaRepository<Movies,Long> {
}
