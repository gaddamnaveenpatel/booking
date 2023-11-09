package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.*;
import com.example.BookMyShow.Repostiory.CustomerRepostiory;
import com.example.BookMyShow.Repostiory.MovieShowRepository;
import com.example.BookMyShow.Repostiory.TicketRepo;
import com.example.BookMyShow.Service.TicketsService;
import com.example.BookMyShow.model.Customer;
import com.example.BookMyShow.model.Movie_Show;
import com.example.BookMyShow.model.Tickets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsImp implements TicketsService {

    private static final Logger logger = LoggerFactory.getLogger(TicketsImp.class);


    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private CustomerRepostiory customerRepostiory;

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public Tickets createTicket(Tickets tickets, Movie_Show showId) {




        Movie_Show movieShow = movieShowRepository.findById(showId.getShowId()).orElse(null);

        if (tickets == null) {
            logger.error("Movie Show not found with id: {}", showId.getShowId());

            throw new TicketNotFoundException("Movie Show not found with id: " + showId.getShowId());

        }

        tickets.setMovieShow(movieShow);


        return ticketRepo.save(tickets);
    }

    @Override
    public Tickets getTicketById(Long Ticket_id) {

        try {

            Tickets tickets = ticketRepo.findById(Ticket_id)
                    .orElseThrow(() -> {

                        logger.error("Ticket not found with id: {}", Ticket_id);
                        return new TicketNotFoundException("Ticket not found with id: " + Ticket_id);
                    });


            return tickets;
        } catch (ObjectOptimisticLockingFailureException ex) {

            throw new ConcurrentBookingException("Concurrent access to the ticket. Please refresh and try again.");
        } catch (TicketNotFoundException ex) {

            throw ex;
        } catch (Exception ex) {
            throw new TicketServiceException("An error occurred while checking ticket availability.", ex);
        }

    }

    @Override
    public List<Tickets> getall() {
        return ticketRepo.findAll();
    }

    @Override
    public Tickets updateTicket(Long Ticket_id, Tickets tickets) {
        Optional<Tickets> existtick = ticketRepo.findById(Ticket_id);

        if (!existtick.isPresent()) {
            throw new TicketServiceException("Ticket is not present this id" + Ticket_id);

        }

        Tickets updateticket = existtick.get();
        updateticket.setTicket_no(tickets.getTicket_no());
        updateticket.setPrice(tickets.getPrice());
        updateticket.setScreen_no(tickets.getScreen_no());
        updateticket.setVersion(tickets.getVersion());
        updateticket.setShow_date(tickets.getShow_date());
        updateticket.setBooked(tickets.isBooked());

        return ticketRepo.save(updateticket);
    }

    @Override
    public void deleteTicket(Long Ticket_id) {

        Optional<Tickets> existid = ticketRepo.findById(Ticket_id);

        if (!existid.isPresent()) {

            throw new TicketNotFoundException("Ticket with ID " + Ticket_id + " not found");
        }
        ticketRepo.deleteById(Ticket_id);
    }

    @Override
    public boolean checkTicketAvailability(Long Ticket_id) {
        try {
            Tickets ticket = ticketRepo.findById(Ticket_id)
                    .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + Ticket_id));

            synchronized (ticket) {
                return !ticket.isBooked();
            }
        } catch (TicketNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TicketServiceException("An error occurred while checking ticket availability.", ex);
        }
    }

//    @Override
//    public boolean bookTicket(Long Ticket_id) {
//        try {
//            Tickets ticket = ticketRepo.findById(Ticket_id)
//                    .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + Ticket_id));
//
//            synchronized (ticket) {
//                if (!ticket.isBooked()) {
//                    ticket.setBooked(true);
//                    ticketRepo.save(ticket);
//                    return true;
//                }
//            }
//            return false;
//        } catch (TicketNotFoundException ex) {
//            throw ex;
//        } catch (ObjectOptimisticLockingFailureException ex) {
//            throw new ConcurrentBookingException("Concurrent booking attempt. Please try again.");
//        } catch (Exception ex) {
//            throw new TicketServiceException("An error occurred while booking the ticket.", ex);
//        }
//    }
//}
@Override
public boolean bookTicket(Long ticketId) {
    try {
        Tickets ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));

        synchronized (ticket) {
            if (!ticket.isBooked()) {
                ticket.setBooked(true);
                ticketRepo.save(ticket);
                return true;
            } else {
                throw new TicketAlreadyBookedException("Ticket with ID " + ticketId + " is already booked.");
            }
        }
    } catch (TicketNotFoundException ex) {
        throw ex;
    } catch (ObjectOptimisticLockingFailureException ex) {
        throw new ConcurrentBookingException("Concurrent booking attempt. Please try again.");
    } catch (TicketAlreadyBookedException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new TicketServiceException("An error occurred while booking the ticket.", ex);
    }
}

    @Override
    public boolean bookTicket(Long Ticket_id, Long Cust_id) {

        try {
            Tickets ticket = ticketRepo.findById(Ticket_id)
                    .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + Ticket_id));

            Customer customer = customerRepostiory.findById(Cust_id)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + Cust_id));

            synchronized (ticket) {

                if (!ticket.isBooked()) {

                    ticket.setBooked(true);
                    ticket.getCustomerList().add(customer);
                    customer.setTickets(ticket);

                    ticketRepo.save(ticket);
                    return true;
                } else {

                    throw new TicketAlreadyBookedException("Ticket with ID " + Ticket_id + " is already booked.");

                }
            }


        } catch (TicketNotFoundException ex) {

            throw ex;
        } catch (CustomerNotFoundException ex) {

            throw ex;
        } catch (ObjectOptimisticLockingFailureException ex) {

            throw new ConcurrentBookingException("Concurrent booking attempt. Please try again.");
        } catch (TicketAlreadyBookedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TicketServiceException("An error occurred while booking the ticket.", ex);
        }


    }

    @Override
    public boolean cancelTicketBooking(Long Ticket_id) {


        Tickets tickets = ticketRepo.findById(Ticket_id).orElse(null);


        if(tickets !=null && tickets.isBooked()){


            tickets.setBooked(false);
            ticketRepo.save(tickets);

            return true;
        } else if(tickets==null){

            throw  new TicketNotFoundException("Ticket not found");
        } else {
            throw new TicketNotFoundException("Ticket is not booked");
        }
    }
}
