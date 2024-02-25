package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Ticket;
import jakarta.persistence.EntityManager;
import repository.TicketRepository;
import service.TicketService;

public class TicketServiceImpl extends BaseServiceImpl<Long, Ticket, TicketRepository> implements TicketService {
    public TicketServiceImpl(EntityManager entityManager, TicketRepository repository) {
        super(entityManager, repository);
    }
}
