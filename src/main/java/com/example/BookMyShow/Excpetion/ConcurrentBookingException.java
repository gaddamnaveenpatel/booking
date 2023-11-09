package com.example.BookMyShow.Excpetion;

public class ConcurrentBookingException extends RuntimeException{

    public ConcurrentBookingException(String message){

        super(message);
    }
}
