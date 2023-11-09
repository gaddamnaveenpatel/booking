package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Service.MovieShowService;
import com.example.BookMyShow.model.Movie_Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movieshow")
public class MovieShowController {

    @Autowired
    private MovieShowService movieShowService;


    @GetMapping("/{id}")
    public ResponseEntity<Movie_Show> getMovieShowById(@PathVariable Long id) {
        Movie_Show movieShow = movieShowService.getMovieShowById(id);
        return ResponseEntity.ok(movieShow);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie_Show>> getAllMovieShows() {
        List<Movie_Show> movieShows = movieShowService.getAllMovieShows();
        return ResponseEntity.ok(movieShows);
    }

    @PostMapping("/save")
    public ResponseEntity<Movie_Show> createMovieShow(
            @RequestParam("image") MultipartFile image,
            @RequestParam("movie") String movie,
            @RequestParam("startTime") @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
            @RequestParam("language") String language,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam("showDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date showDate
    ) {
        try {
            Movie_Show movieShow = new Movie_Show();
            movieShow.setMovie(movie);
            movieShow.setStartTime(startTime);
            movieShow.setLanguage(language);
            movieShow.setEndTime(endTime);
            movieShow.setShowDate(showDate);

            if (image != null) {
                byte[] imageData = image.getBytes();
                movieShow.setImageData(imageData);
            }

            Movie_Show createdMovieShow = movieShowService.createMovieShow(movieShow);
            return new ResponseEntity<>(createdMovieShow, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<Movie_Show> updateMovieShow(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image,
            @RequestParam("movie") String movie,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("language") String language,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam("showDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date showDate
    ) {
        try {
            Movie_Show movieShow = new Movie_Show();
            movieShow.setMovie(movie);
            movieShow.setStartTime(startTime);
            movieShow.setLanguage(language);
            movieShow.setEndTime(endTime);
            movieShow.setShowDate(showDate);

            if (image != null) {
                byte[] imageData = image.getBytes();
                movieShow.setImageData(imageData);
            }

            Movie_Show updatedMovieShow = movieShowService.updateMovieShow(id, movieShow);
            if (updatedMovieShow != null) {
                return ResponseEntity.ok(updatedMovieShow);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieShow(@PathVariable Long id) {
        movieShowService.deleteMovieShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
