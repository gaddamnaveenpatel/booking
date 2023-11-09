package com.example.BookMyShow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer the_id;

    private String city;

    private String Loctions;

    private String Theatre_Name;


//    @JsonManagedReference
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "Screen_id")
//    private Screen screen;        @OneToMany(mappedBy = "movieShow",cascade = CascadeType.ALL, orphanRemoval = true)

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Screen> screenList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieshow-id")
    @JsonBackReference
    private Movie_Show movieShow;


    public Theatre(Integer the_id, String city, String loctions, String theatre_Name, List<Screen> screenList, Movie_Show movieShow) {
        this.the_id = the_id;
        this.city = city;
        Loctions = loctions;
        Theatre_Name = theatre_Name;
        this.screenList = screenList;
        this.movieShow = movieShow;
    }

    public Integer getThe_id() {
        return the_id;
    }

    public void setThe_id(Integer the_id) {
        this.the_id = the_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLoctions() {
        return Loctions;
    }

    public void setLoctions(String loctions) {
        Loctions = loctions;
    }

    public String getTheatre_Name() {
        return Theatre_Name;
    }

    public void setTheatre_Name(String theatre_Name) {
        Theatre_Name = theatre_Name;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<Screen> screenList) {
        this.screenList = screenList;
    }

    public Movie_Show getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(Movie_Show movieShow) {
        this.movieShow = movieShow;
    }

    public Theatre() {
    }
}