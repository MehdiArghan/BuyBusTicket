package controller.signup;

import base.repository.util.HibernateUtil;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.PersonRepository;
import repository.impl.PersonRepositoryImpl;
import service.PersonService;
import service.impl.PersonServiceImpl;

import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "FilterSingUp", urlPatterns = "/signup")
public class FilterSignup implements Filter {
    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final PersonRepository personRepository = new PersonRepositoryImpl(entityManager);
    private final PersonService personService = new PersonServiceImpl(entityManager, personRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<Person> personList = personService.loadAll();
        String userName = request.getParameter("userName");

        boolean personExist = false;
        for (Person users : personList) {
            if (users.getUserName().equals(userName)) {
                personExist = true;
                break;
            }
        }

        if (personExist) {
            String error = (userName + " already exists!");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signup.jsp");
            requestDispatcher.forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}