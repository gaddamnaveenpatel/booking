package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.MovieShowNotFoundException;
import com.example.BookMyShow.Repostiory.MovieShowRepository;
import com.example.BookMyShow.Service.MovieShowService;
import com.example.BookMyShow.model.Movie_Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieShowServiceImpl implements MovieShowService {

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Override
    public Movie_Show getMovieShowById(Long id) {
        return movieShowRepository.findById(id)
                .orElseThrow(() -> new MovieShowNotFoundException("Movie Show not found with ID: " + id));
    }

    @Override
    public Movie_Show createMovieShow(Movie_Show movieShow) {
        return movieShowRepository.save(movieShow);
    }


    @Override
    public Movie_Show updateMovieShow(Long id, Movie_Show movieShow) {
        // Check if the movie show with the given ID exists
        Movie_Show existingMovieShow = getMovieShowById(id);

        if (existingMovieShow != null) {
            // Update properties of the existing movie show
            existingMovieShow.setMovie(movieShow.getMovie());
            existingMovieShow.setStartTime(movieShow.getStartTime());
            existingMovieShow.setLanguage(movieShow.getLanguage());
            existingMovieShow.setEndTime(movieShow.getEndTime());
            existingMovieShow.setShowDate(movieShow.getShowDate());
            // Update other properties as needed

            return movieShowRepository.save(existingMovieShow);
        } else {
            // If the movie show with the given ID is not found, throw an exception
            throw new MovieShowNotFoundException("Movie Show not found with ID: " + id);
        }
    }
    @Override
    public void deleteMovieShow(Long id) {
        // Check if the movie show with the given ID exists
        Movie_Show existingMovieShow = getMovieShowById(id);

        if (existingMovieShow != null) {
            movieShowRepository.deleteById(id);
        } else {
            // If the movie show with the given ID is not found, throw an exception
            throw new MovieShowNotFoundException("Movie Show not found with ID: " + id);
        }
    }

    @Override
    public List<Movie_Show> getAllMovieShows() {
        return movieShowRepository.findAll();
    }



}
