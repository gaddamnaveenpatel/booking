package com.example.BookMyShow.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long movie_id;
    private String movie_Title;

    private String movie_Description;

    private long stars;


    @OneToOne
    @JoinColumn(name = "show_id")
    private Movie_Show movieShow;


    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_Title() {
        return movie_Title;
    }

    public void setMovie_Title(String movie_Title) {
        this.movie_Title = movie_Title;
    }

    public String getMovie_Description() {
        return movie_Description;
    }

    public void setMovie_Description(String movie_Description) {
        this.movie_Description = movie_Description;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }

    public Movie_Show getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(Movie_Show movieShow) {
        this.movieShow = movieShow;
    }


    public Movies(Long movie_id, String movie_Title, String movie_Description, long stars, Movie_Show movieShow) {
        this.movie_id = movie_id;
        this.movie_Title = movie_Title;
        this.movie_Description = movie_Description;
        this.stars = stars;
        this.movieShow = movieShow;
    }

    public Movies() {
    }
}
