package com.example.BookMyShow.Excpetion;

import com.example.BookMyShow.Service.TicketsService;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message){

        super(message);
    }
}
