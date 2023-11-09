package com.example.BookMyShow.Service;

import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Theatre;

import java.util.List;

public interface TheatreService {


    Theatre createTheatre(Theatre theatre, Movie_Show showId);



    Theatre getTheatreById(Integer id);
    List<Theatre> getAllTheatres();
    Theatre updateTheatre(Integer id, Theatre theatre);
    void deleteTheatre(Integer id);


}



