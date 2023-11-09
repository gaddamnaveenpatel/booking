package com.example.BookMyShow.Service;

import com.example.BookMyShow.model.Movie_Show;

import java.util.List;

public interface MovieShowService {


    Movie_Show getMovieShowById(Long id);
    Movie_Show createMovieShow(Movie_Show movieShow);
    Movie_Show updateMovieShow(Long id, Movie_Show movieShow);
    void deleteMovieShow(Long id);

    List<Movie_Show> getAllMovieShows();

}
