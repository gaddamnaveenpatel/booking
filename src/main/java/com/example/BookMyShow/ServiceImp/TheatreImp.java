package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.MoviesNotFoundException;
import com.example.BookMyShow.Excpetion.TheatreNotfoundException;
import com.example.BookMyShow.Repostiory.MovieShowRepository;
import com.example.BookMyShow.Repostiory.TheatreRepo;
import com.example.BookMyShow.Service.TheatreService;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreImp implements TheatreService {
@Autowired
    private TheatreRepo theatreRepo;

@Autowired
    private MovieShowRepository movieShowRepository;

    @Override
    public Theatre createTheatre(Theatre theatre, Movie_Show showId) {

        Movie_Show existid = movieShowRepository.findById(showId.getShowId()).orElse(null);
                if(existid == null){

                    throw new TheatreNotfoundException("The specified show ID is not available in the database: " + showId.getShowId());

                }

      theatre.setMovieShow(existid);

                return theatreRepo.save(theatre);



    }

    @Override
    public Theatre getTheatreById(Integer id) {
        return theatreRepo.findById(id)
                .orElseThrow(() -> new TheatreNotfoundException("Theatre not found with ID: " + id));
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepo.findAll();
    }


    @Override
    public Theatre updateTheatre(Integer id, Theatre updatedTheatre) {
        Theatre existingTheatre = getTheatreById(id);
        if (existingTheatre != null) {
            // Update the properties of existingTheatre with those from updatedTheatre
            // You may choose to implement this.
            // Then save the updated entity.
            existingTheatre.setCity(updatedTheatre.getCity());
            existingTheatre.setLoctions(updatedTheatre.getLoctions());
            existingTheatre.setTheatre_Name(updatedTheatre.getTheatre_Name());
            existingTheatre.setMovieShow(updatedTheatre.getMovieShow());
            return theatreRepo.save(existingTheatre);
        } else {
            throw new TheatreNotfoundException("Theatre not found with ID: " + id);
        }
    }

    @Override
    public void deleteTheatre(Integer id) {
        if (theatreRepo.existsById(id)) {
            theatreRepo.deleteById(id);
        } else {
            throw new TheatreNotfoundException("Theatre not found with ID: " + id);
        }
    }


}
