package com.example.BookMyShow.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ticket_id;

    private Long price;

    private Long ticket_no;

    private Integer Screen_no;

    private LocalTime show_time;

    private LocalDate show_date;

    private Integer seat_no;

    private boolean booked;  // Added property to represent the booked status


    @Version
    private Long version;  // Version field for optimistic locking


    @OneToMany(mappedBy = "tickets", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Customer> customerList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    @JsonBackReference
   private Movie_Show movieShow;

    public Tickets(Long ticket_id, Long price, Long ticket_no, Integer screen_no, LocalTime show_time, LocalDate show_date, Integer seat_no, boolean booked, Long version, List<Customer> customerList, Movie_Show movieShow) {
        Ticket_id = ticket_id;
        this.price = price;
        this.ticket_no = ticket_no;
        Screen_no = screen_no;
        this.show_time = show_time;
        this.show_date = show_date;
        this.seat_no = seat_no;
        this.booked = booked;
        this.version = version;
        this.customerList = customerList;
        this.movieShow = movieShow;
    }

    public Tickets() {
    }

    public Long getTicket_id() {
        return Ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        Ticket_id = ticket_id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(Long ticket_no) {
        this.ticket_no = ticket_no;
    }

    public Integer getScreen_no() {
        return Screen_no;
    }

    public void setScreen_no(Integer screen_no) {
        Screen_no = screen_no;
    }

    public LocalTime getShow_time() {
        return show_time;
    }

    public void setShow_time(LocalTime show_time) {
        this.show_time = show_time;
    }

    public LocalDate getShow_date() {
        return show_date;
    }

    public void setShow_date(LocalDate show_date) {
        this.show_date = show_date;
    }

    public Integer getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(Integer seat_no) {
        this.seat_no = seat_no;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Movie_Show getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(Movie_Show movieShow) {
        this.movieShow = movieShow;
    }
}
