package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Service.MoviesServies;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Movies")
public class MoviesController {


    @Autowired
    private MoviesServies moviesServies;

    @PostMapping("/save/{showId}")
    public ResponseEntity<Movies> saveMovies(@RequestBody Movies movies, @PathVariable Movie_Show showId) {

        Movie_Show movieShow = new Movie_Show();

        movieShow.setShowId(showId.getShowId());
        movies.setMovieShow(movieShow);

        Movies createMovie = moviesServies.createMovie(movies, movieShow);

        return ResponseEntity.status(HttpStatus.CREATED).body(createMovie);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movies>> getall() {

        List<Movies> movies = moviesServies.getall();

        if (!movies.isEmpty()) {

            return new ResponseEntity<>(movies, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{movie_id}")
    public ResponseEntity<Movies> getMovieById(@PathVariable Long movie_id) {

        Optional<Movies> movies = moviesServies.getMovieById(movie_id);

        if (movies.isPresent()) {

            return ResponseEntity.ok(movies.get());
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{movie_id}")
    public ResponseEntity<Movies> updateMovie(@PathVariable Long movie_id, @RequestBody Movies updatedMovie) {

        Movies update = moviesServies.updateMovie(movie_id, updatedMovie);

        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/{movie_id}")
    public ResponseEntity<?> deletemovie(@PathVariable Long movie_id){

        moviesServies.deleteMovie(movie_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

}