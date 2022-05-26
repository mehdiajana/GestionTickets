package com.example.gestiontickets.repository;

import com.example.gestiontickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> getTicketsByAttrIsFalse();
}
