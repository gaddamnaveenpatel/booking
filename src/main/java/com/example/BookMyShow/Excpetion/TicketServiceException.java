package com.example.BookMyShow.Excpetion;

public class TicketServiceException  extends RuntimeException{


         public TicketServiceException(String message) {
            super(message);
        }

    public TicketServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

