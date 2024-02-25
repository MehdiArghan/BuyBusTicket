package controller.signup;

import base.repository.util.HibernateUtil;
import dto.PersonDto;
import dto.mapper.PersonDtoMapper;
import entity.Gender;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.PersonRepository;
import repository.impl.PersonRepositoryImpl;
import security.PasswordHash;
import service.PersonService;
import service.impl.PersonServiceImpl;
import util.CheckValidation;

import java.io.IOException;

@WebServlet(name = "servletSignup", urlPatterns = "/signup")
public class Signup extends HttpServlet {
    EntityManager entityManager;
    PersonRepository personRepository;
    PersonService personService;
    PersonDtoMapper personDtoMapper;
    PasswordHash passwordHash;

    @Override
    public void init() {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        personRepository = new PersonRepositoryImpl(entityManager);
        personService = new PersonServiceImpl(entityManager, personRepository);
        personDtoMapper = new PersonDtoMapper();
        passwordHash = new PasswordHash();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String gender = req.getParameter("optionGender");
        try {
            String hashedPassword = passwordHash.createHashedPassword(password);

            PersonDto personDto = PersonDto.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .gender(Gender.valueOf(gender))
                    .userName(userName)
                    .password(hashedPassword)
                    .build();

            boolean validate = CheckValidation.isValid(personDto);
            HttpSession httpSession = req.getSession();

            if (!validate) {
                httpSession.setAttribute("error", "person validation is error");
                getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
            } else {
                personService.save(personDtoMapper.getPersonDtoToPerson(personDto));
                resp.setCharacterEncoding("UTF-8");
                String message = firstName + " " + lastName + " added successfully";
                httpSession.setAttribute("messageSavePerson", message);
                getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
