package com.example.BookMyShow.Excpetion;

public class MovieShowNotFoundException  extends  RuntimeException{

    public MovieShowNotFoundException(String message) {
        super(message);
    }

}
