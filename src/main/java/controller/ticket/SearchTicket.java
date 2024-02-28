package controller.ticket;

import base.repository.util.HibernateUtil;
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
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "TicketServlet", urlPatterns = "/ticket")
public class SearchTicket extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        try {
            List<entity.Ticket> tickets = ticketService.findTicket(origin, destination, date);
            if (!tickets.isEmpty()) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("tickets", tickets);
                String error = ("The desired ticket was found");
                httpSession.setAttribute("error", error);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ticket.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                HttpSession httpSession = req.getSession();
                String error = ("No ticket found");
                httpSession.setAttribute("error", error);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ticket.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            HttpSession httpSession = req.getSession();
            String error = ("No ticket found");
            httpSession.setAttribute("error", error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ticket.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
