package com.example.BookMyShow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Cust_id;

    private  String Dob;

    private  String Age;

    private String Name;

    private  String Email_id;

    private Integer Mobile_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "ticket_id")
    private Tickets tickets;

    public Customer(Long cust_id, String dob, String age, String name, String email_id, Integer mobile_no, Tickets tickets) {
        Cust_id = cust_id;
        Dob = dob;
        Age = age;
        Name = name;
        Email_id = email_id;
        Mobile_no = mobile_no;
        this.tickets = tickets;
    }

    public Customer() {
    }

    public Long getCust_id() {
        return Cust_id;
    }

    public void setCust_id(Long cust_id) {
        Cust_id = cust_id;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public Integer getMobile_no() {
        return Mobile_no;
    }

    public void setMobile_no(Integer mobile_no) {
        Mobile_no = mobile_no;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }
}
