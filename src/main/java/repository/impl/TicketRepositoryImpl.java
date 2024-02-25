package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Ticket;
import jakarta.persistence.EntityManager;
import repository.TicketRepository;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Long, Ticket> implements TicketRepository {
    public TicketRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}
