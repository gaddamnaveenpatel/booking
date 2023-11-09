package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Excpetion.TheatreNotfoundException;
import com.example.BookMyShow.Service.MovieShowService;
import com.example.BookMyShow.Service.TheatreService;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/threate")
@RestController
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieShowService movieShowService;

//    @PostMapping("/save/{showId}")
//    public ResponseEntity<Theatre> saveTheater(@PathVariable Movie_Show showId, @RequestBody Theatre theatre) {
//
//
//        Movie_Show savemoveshow = new Movie_Show();
//
//        savemoveshow.setShowId(showId.getShowId());
//
//        theatre.setMovieShow(savemoveshow);
//
//        Theatre create = theatreService.createTheatre(theatre, savemoveshow);
//        return ResponseEntity.status(HttpStatus.CREATED).body(create);
//    }

    @PostMapping("/save/{showId}")
    public ResponseEntity<Theatre> saveTheater(@PathVariable Movie_Show showId, @RequestBody Theatre theatre) {
        // Fetch the existing Movie_Show entity based on the showId
        Movie_Show existingMovieShow = movieShowService.getMovieShowById(showId.getShowId());

        // Set the existing Movie_Show entity in the Theatre object
        theatre.setMovieShow(existingMovieShow);

        // Create the Theatre entity
        Theatre createdTheatre = theatreService.createTheatre(theatre, showId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheatre);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getTheatreById(@PathVariable Integer id) {
        try {
            Theatre theatre = theatreService.getTheatreById(id);
            return new ResponseEntity<>(theatre, HttpStatus.OK);
        } catch (TheatreNotfoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheatre(@PathVariable Integer id, @RequestBody Theatre updatedTheatre) {
        try {
            Theatre updated = theatreService.updateTheatre(id, updatedTheatre);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (TheatreNotfoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheatre(@PathVariable Integer id) {
        try {
            theatreService.deleteTheatre(id);
            return new ResponseEntity<>("Theatre with ID " + id + " has been deleted", HttpStatus.OK);
        } catch (TheatreNotfoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
