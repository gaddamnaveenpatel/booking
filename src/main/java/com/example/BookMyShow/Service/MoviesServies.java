package com.example.BookMyShow.Service;

import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Movies;

import java.util.List;
import java.util.Optional;

public interface MoviesServies {

    Movies createMovie(Movies movies, Movie_Show showId);

    List<Movies> getall();

    Optional<Movies>getMovieById(Long movie_id);

    Movies updateMovie(Long movie_id,Movies updatedmovie);

    void deleteMovie(Long movie_id);
    }


