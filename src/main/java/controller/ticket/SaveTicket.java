package controller.ticket;

import base.repository.util.HibernateUtil;
import entity.Gender;
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

@WebServlet(name = "saveTicket", urlPatterns = "/saveTicket")
public class SaveTicket extends HttpServlet {
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
        HttpSession httpSession = req.getSession();
        Person person = (Person) httpSession.getAttribute("currentPerson");

        if (ticket.getPerson() == null) {
            ticket.setPerson(person);
            String error;
            ticketService.update(ticket);
            if (person.getGender().equals(Gender.MALE)){
                error = "Mr "+person.getFirstName()+" "+person.getLastName()+" your ticket purchase was successful";
            }else {
                error = "Mrs "+person.getFirstName()+" "+person.getLastName()+" your ticket purchase was successful";
            }
            httpSession.setAttribute("error", error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ticket.jsp");
            requestDispatcher.forward(req, resp);
        } else{
            String error = "This ticket has already been selected";
            httpSession.setAttribute("error", error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ticket.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
