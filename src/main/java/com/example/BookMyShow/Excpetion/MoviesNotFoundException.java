package com.example.BookMyShow.Excpetion;



public class MoviesNotFoundException extends RuntimeException{

    public MoviesNotFoundException(String message){


        super(message);
    }
}
