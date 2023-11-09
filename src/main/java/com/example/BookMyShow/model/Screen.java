package com.example.BookMyShow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Screen {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer screen_id;

    private String screen_name;

    private Integer screen_no;

    private  Integer no_of_seats;

@ManyToOne(fetch = FetchType.LAZY)
@JsonBackReference
    @JoinColumn(name = "theater_id")
private Theatre theatre;

    public Screen(Integer screen_id, String screen_name, Integer screen_no, Integer no_of_seats, Theatre theatre) {
        this.screen_id = screen_id;
        this.screen_name = screen_name;
        this.screen_no = screen_no;
        this.no_of_seats = no_of_seats;
        this.theatre = theatre;
    }

    public Integer getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(Integer screen_id) {
        this.screen_id = screen_id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public Integer getScreen_no() {
        return screen_no;
    }

    public void setScreen_no(Integer screen_no) {
        this.screen_no = screen_no;
    }

    public Integer getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(Integer no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Screen() {
    }
}

