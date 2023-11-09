package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.MoviesNotFoundException;
import com.example.BookMyShow.Repostiory.MovieShowRepository;
import com.example.BookMyShow.Repostiory.MoviesRepo;
import com.example.BookMyShow.Service.MoviesServies;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MoviesServiceImp implements MoviesServies {

    @Autowired
    private MoviesRepo moviesRepo;

    @Autowired
    private MovieShowRepository movieShowRepository;


    @Override
    public Movies createMovie(Movies movies, Movie_Show showId) {

        Movie_Show existingShow = movieShowRepository.findById(showId.getShowId()).orElse(null);

        if (existingShow == null) {

            throw new MoviesNotFoundException("The specified show ID is not available in the database: " + showId.getShowId());

        }
        movies.setMovieShow(existingShow);
        movies.setStars(movies.getStars());

        return moviesRepo.save(movies);
    }

    @Override
    public List<Movies> getall() {
        return moviesRepo.findAll();
    }

    @Override
    public Optional<Movies> getMovieById(Long movie_id) {


        Optional<Movies> movies = moviesRepo.findById(movie_id);

        if (!movies.isPresent()) {

            throw new MoviesNotFoundException("Movie with ID " + movie_id + " not found");
        }
        return movies;
    }

    @Override
    public Movies updateMovie(Long movie_id, Movies updatedmovie) {
        Optional<Movies>existMovie = moviesRepo.findById(movie_id);

        if(!existMovie.isPresent()){

            throw new MoviesNotFoundException("Movie with ID " + movie_id + " not found");
        }

        Movies movieToUpdated = existMovie.get();
        movieToUpdated.setMovie_Description(updatedmovie.getMovie_Description());
        movieToUpdated.setMovie_Title(updatedmovie.getMovie_Title());
        movieToUpdated.setStars(updatedmovie.getStars());
        return moviesRepo.save(movieToUpdated) ;
    }

    @Override
    public void deleteMovie(Long movie_id) {

        Optional<Movies> moviedelete = moviesRepo.findById(movie_id);

        if(!moviedelete.isPresent()){

            throw new MoviesNotFoundException("Movie with ID " + movie_id + " not found");
        }
        moviesRepo.deleteById(movie_id);
    }

}

