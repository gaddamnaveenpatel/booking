package com.example.BookMyShow.Service;

import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Tickets;

import java.util.List;

public interface TicketsService {

    Tickets createTicket(Tickets tickets, Movie_Show showId);

    Tickets getTicketById(Long Ticket_id);

    List<Tickets>getall();


    Tickets updateTicket(Long Ticket_id,Tickets tickets);

    void deleteTicket(Long Ticket_id);

    boolean checkTicketAvailability(Long Ticket_id);
    boolean bookTicket(Long Ticket_id );
    boolean bookTicket(Long Ticket_id,Long Cust_id );


    boolean cancelTicketBooking(Long Ticket_id);

}
