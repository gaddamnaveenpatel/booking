package com.example.BookMyShow.Controller;


import com.example.BookMyShow.Service.TicketsService;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Ticket")
@RestController
public class TicketController {

    @Autowired
    private TicketsService ticketsService;


    @PostMapping("/save/{showId}")
public ResponseEntity<Tickets> saveTicket(@RequestBody Tickets tickets, @PathVariable Movie_Show showId){


    Movie_Show movieShow = new Movie_Show();


    movieShow.setShowId(showId.getShowId());
    tickets.setMovieShow(movieShow);


Tickets createticket = ticketsService.createTicket(tickets,movieShow);

return ResponseEntity.status(HttpStatus.CREATED).body(createticket);


}
    @GetMapping("/{Ticket_id}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable Long Ticket_id) {
        // Logic to retrieve a ticket by ID and return the response
        Tickets ticket = ticketsService.getTicketById(Ticket_id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    @PutMapping("/{Ticket_id}")
    public ResponseEntity<Tickets> updateTicket(@PathVariable Long Ticket_id, @RequestBody Tickets ticket) {
        Tickets updatedTicket = ticketsService.updateTicket(Ticket_id, ticket);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }
    @DeleteMapping("/{Ticket_id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long Ticket_id) {
        ticketsService.deleteTicket(Ticket_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to check ticket availability
    @GetMapping("/{Ticket_id}/checkAvailability")
    public ResponseEntity<Boolean> checkTicketAvailability(@PathVariable Long Ticket_id) {
        boolean isAvailable = ticketsService.checkTicketAvailability(Ticket_id);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    // Endpoint to book a ticket
    @PostMapping("/{Ticket_id}/book")
    public ResponseEntity<Boolean> bookTicket(@PathVariable Long Ticket_id) {
        boolean isBooked = ticketsService.bookTicket(Ticket_id);
        if (isBooked) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }


    // Endpoint for booking a ticket for a specific customer
    @PostMapping("/book/{customerId}/{ticketId}")
    public ResponseEntity<String> bookTicket(
            @PathVariable Long ticketId,
            @PathVariable Long customerId
    ) {
        boolean isBooked = ticketsService.bookTicket(ticketId, customerId);

        if (isBooked) {
            return ResponseEntity.ok("Ticket successfully booked for customer with ID: " + customerId);
        } else {
            return ResponseEntity.badRequest().body("Failed to book the ticket.");
        }
    }






    @PostMapping("/{Ticket_id}/cancel")
    public  ResponseEntity<Void>cancelticketbooking(@PathVariable Long Ticket_id){

        boolean iscalled = ticketsService.cancelTicketBooking(Ticket_id);

        if(iscalled){

            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Ticket cancellation successful
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Ticket not found or not eligible for cancellation
        }
        }


}
