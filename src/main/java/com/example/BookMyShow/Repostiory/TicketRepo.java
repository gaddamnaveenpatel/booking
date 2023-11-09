package com.example.BookMyShow.Repostiory;

import com.example.BookMyShow.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Tickets,Long> {
}
