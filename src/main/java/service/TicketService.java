package service;

import base.service.BaseService;
import entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService extends BaseService<Long, Ticket> {
    List<Ticket> findTicket(String origin, String destination, LocalDate date);
    Ticket findByTravelId(String travelId);
}
