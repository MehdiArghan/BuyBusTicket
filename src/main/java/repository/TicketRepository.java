package repository;

import base.repository.BaseRepository;
import entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends BaseRepository<Long, Ticket> {
    List<Ticket> findByTicket(String origin, String destination, LocalDate date);

    Ticket findByTravelId(String travelId);
}
