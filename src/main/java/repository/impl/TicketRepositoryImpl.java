package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import entity.Ticket;
import jakarta.persistence.EntityManager;
import repository.TicketRepository;

import java.time.LocalDate;
import java.util.List;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Long, Ticket> implements TicketRepository {
    EntityManager entityManager;

    public TicketRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

    @Override
    public List<Ticket> findByTicket(String origin, String destination, LocalDate date) {
        return entityManager.createQuery(
                        "from Ticket t where t.origin =:ORIGIN and t.destination =:DESTINATION and t.departureDate =:DEPARTURE order by t.departureTime asc "
                        , Ticket.class)
                .setParameter("ORIGIN", origin)
                .setParameter("DESTINATION", destination)
                .setParameter("DEPARTURE", date)
                .getResultList();
    }

    @Override
    public Ticket findByTravelId(String travelId) {
        return entityManager.createQuery
                        ("from Ticket t where t.travelId =:TRAVELID", Ticket.class)
                .setParameter("TRAVELID", travelId)
                .getSingleResult();
    }

    @Override
    public List<Ticket> findByUserName(String userName) {
        return entityManager.createQuery(
                        "from Ticket t where t.person.userName =:USERNAME  order by t.departureTime asc "
                        , Ticket.class)
                .setParameter("USERNAME", userName)
                .getResultList();
    }
}
