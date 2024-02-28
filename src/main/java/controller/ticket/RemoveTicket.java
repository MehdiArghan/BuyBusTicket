package controller.ticket;

import base.repository.util.HibernateUtil;
import entity.Person;
import entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.TicketRepository;
import repository.impl.TicketRepositoryImpl;
import service.TicketService;
import service.impl.TicketServiceImpl;

import java.io.IOException;

@WebServlet(name = "removeTicket", urlPatterns = "/remove")
public class RemoveTicket extends HttpServlet {
    private EntityManager entityManager;
    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        ticketRepository = new TicketRepositoryImpl(entityManager);
        ticketService = new TicketServiceImpl(entityManager, ticketRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String travelId = req.getParameter("travelId");
        Ticket ticket = ticketService.findByTravelId(travelId);
        ticket.setPerson(null);
        ticketService.update(ticket);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/purchasedtickets.jsp");
        requestDispatcher.forward(req, resp);
    }
}
