package com.example.BookMyShow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie_shows")
public class Movie_Show {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Long showId;


    @Column(name = "movie")
    private String movie;

    @Column(name = "start_time")
    private LocalTime startTime;

    private String language;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "show_date")
    private Date showDate;

    @JsonIgnore
    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;



    @OneToMany(mappedBy = "movieShow", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tickets> ticketsList = new ArrayList<>();


      @OneToMany(mappedBy = "movieShow",cascade = CascadeType.ALL, orphanRemoval = true)
      @JsonManagedReference
      private List<Theatre>theatreList = new ArrayList<>();


    public Movie_Show(Long showId, String movie, LocalTime startTime, String language, LocalTime endTime, Date showDate, byte[] imageData, List<Tickets> ticketsList, List<Theatre> theatreList) {
        this.showId = showId;
        this.movie = movie;
        this.startTime = startTime;
        this.language = language;
        this.endTime = endTime;
        this.showDate = showDate;
        this.imageData = imageData;
        this.ticketsList = ticketsList;
        this.theatreList = theatreList;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public List<Theatre> getTheatreList() {
        return theatreList;
    }

    public void setTheatreList(List<Theatre> theatreList) {
        this.theatreList = theatreList;
    }

    public Movie_Show() {
    }
}
