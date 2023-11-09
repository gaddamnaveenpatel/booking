package com.example.BookMyShow.Excpetion;

public class TicketAlreadyBookedException extends RuntimeException {
    public TicketAlreadyBookedException(String message) {
        super(message);
    }
}