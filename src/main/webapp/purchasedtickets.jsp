<%@ page import="entity.Person" %>
<%@ page import="base.repository.util.HibernateUtil" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="service.TicketService" %>
<%@ page import="service.impl.TicketServiceImpl" %>
<%@ page import="repository.impl.TicketRepositoryImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Ticket" %>
<%@ page import="java.util.ArrayList" %>
<%
    Person currentPerson = (Person) session.getAttribute("currentPerson");
    if (currentPerson == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>purchasedTickets</title>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>purchasedTickets</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/purchasedtickets.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg style=" style="background-color: #c0c8d0">

    <div class="container-fluid">

        <div class="collapse navbar-collapse">

            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active " style="color: black" aria-current="page"
                       href="index.jsp"><strong>Home</strong></a>
                </li>
            </ul>

            <ul class="navbar-nav ml-auto">
                <%
                    Person person = (Person) session.getAttribute("currentPerson");
                    if (person == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" style="color: black" aria-current="page"
                       href="signup.jsp"><strong>Signup</strong></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: black" aria-current="page" href="login.jsp"><strong>Login</strong></a>
                </li>
                <%
                } else {
                %>
                <li class="nav-item">
                    <a class="nav-link" style="color: black" aria-current="page" href="ticket.jsp">
                        <strong><%=person.getUserName()%>
                        </strong>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: black" aria-current="page"
                       data-bs-toggle="modal" data-bs-target="#statsModal" href="">
                        <strong>Logout</strong></a>
                </li>
                <%
                    }
                %>
            </ul>

        </div>
    </div>
</nav>

<div class="modal fade" id="statsModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">Logout</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <strong>Do you want to Logout of your account?</strong>
            </div>
            <div class="modal-footer">
                <div class="container text-center">
                    <a href="Logout" class="btn btn-primary">YES</a>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">NO</button>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <table class="table table-bordered table-striped text-center mt-5">
        <thead>
        <tr>
            <th class="col-md-1">Row</th>
            <th class="col-md-2">origin</th>
            <th class="col-md-2">destination</th>
            <th class="col-md-2">departureDate</th>
            <th class="col-md-2">departureTime</th>
            <th class="col-md-2">travelId</th>
            <th class="col-md-1">cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
            TicketService ticketService = new TicketServiceImpl(entityManager, new TicketRepositoryImpl(entityManager));
            String userName = person.getUserName();
            List<Ticket> tickets;
            if (userName == null) {
                tickets = new ArrayList<>();
            } else {
                tickets = ticketService.findByUserName(person.getUserName());
            }
            if (tickets == null) {

        %>
        <tr>

        </tr>
        <%
        } else {
            int counter = 1;
            for (Ticket tickets1 : tickets) {
        %>
        <tr>
            <td><%= counter++ %>
            </td>
            <td><%= tickets1.getOrigin() %>
            </td>
            <td><%= tickets1.getDestination() %>
            </td>
            <td><%= tickets1.getDepartureDate() %>
            </td>
            <td><%= tickets1.getDepartureTime() %>
            </td>
            <td><%= tickets1.getTravelId() %>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" data-travel-id="<%= tickets1.getTravelId() %>"
                        data-bs-toggle="modal" data-bs-target="#selectTicket">cancel</button>
            </td>
        </tr>
        <% }
        }
        %>
        </tbody>
    </table>
</div>


<div class="modal fade" id="selectTicket" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">remove ticket</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <strong>Do you want to cancel your ticket?</strong>
            </div>
            <div class="modal-footer">
                <div class="container text-center">
                    <a id="confirmBuyTicket" href="#" class="btn btn-primary">YES</a>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">NO</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#selectTicket').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var travelId = button.data('travel-id');
            var modal = $(this);
            modal.find('#confirmBuyTicket').attr('href', 'remove?travelId=' + travelId);
        });
    });
</script>


</body>
</html>
