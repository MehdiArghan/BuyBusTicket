package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Person;
import entity.Ticket;
import jakarta.persistence.EntityManager;
import repository.TicketRepository;
import service.TicketService;

import java.time.LocalDate;
import java.util.List;

public class TicketServiceImpl extends BaseServiceImpl<Long, Ticket, TicketRepository> implements TicketService {
    public TicketServiceImpl(EntityManager entityManager, TicketRepository repository) {
        super(entityManager, repository);
    }

    @Override
    public List<Ticket> findTicket(String origin, String destination, LocalDate date) {
        return repository.findByTicket(origin,destination,date);
    }

    @Override
    public Ticket findByTravelId(String travelId) {
        return repository.findByTravelId(travelId);
    }

    @Override
    public List<Ticket> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}
