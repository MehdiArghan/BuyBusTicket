<%@ page import="entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Ticket" %>
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
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Election</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/election.css">
    <link rel="icon" href="picture/images.jpg">
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


<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card mt-3">
                <%
                    String error = (String) session.getAttribute("error");
                    if (error != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong><%= error %>
                    </strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                        session.removeAttribute("error");
                    }
                %>
                <div class="card-header text-center fs-4">Select ticket</div>
                <div class="card-body">
                    <form action="ticket" method="post" target="_self">
                        <div class="form-group mb-2">
                            <label for="origin">Choose a origin :</label>
                            <select class="form-control" id="origin" name="origin">
                                <option value="shiraz">shiraz</option>
                                <option value="tehran" selected>tehran</option>
                                <option value="mashhad">mashhad</option>
                                <option value="sari">sari</option>
                            </select>
                        </div>
                        <div class="form-group mb-2">
                            <label for="destination">Choose a destination :</label>
                            <select class="form-control" id="destination" name="destination">
                                <option value="shiraz">shiraz</option>
                                <option value="tehran">tehran</option>
                                <option value="mashhad" selected>mashhad</option>
                                <option value="sari">sari</option>
                            </select>
                        </div>
                        <div class="form-group mb-2">
                            <label for="date">Select a date:</label>
                            <input type="date" id="date" name="date" class="form-control" value="2022-03-15">
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-primary">Searching</button>
                        </div>
                    </form>
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
            <th class="col-md-1">Buy tickets</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ticket> tickets = (List<Ticket>) session.getAttribute("tickets");
            if (tickets==null){

                %>
        <tr>

        </tr>
        <%
            }else{
            int counter = 1;
            for (Ticket tickets1 :tickets)
            {
        %>
        <tr>
            <td><%= counter++ %></td>
            <td><%= tickets1.getOrigin() %></td>
            <td><%= tickets1.getDestination() %></td>
            <td><%= tickets1.getDepartureDate() %></td>
            <td><%= tickets1.getDepartureTime() %></td>
            <td><%= tickets1.getTravelId() %></td>
            <td><button class="btn btn-danger btn-sm"><a class="nav-link" aria-current="page"
                   data-bs-toggle="modal" data-bs-target="#selectTicket" href="">BUY</a></button></td>
        </tr>
        <div class="modal fade" id="selectTicket" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5">select ticket</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <strong>Do you want to choose this ticket?</strong>
                    </div>
                    <div class="modal-footer">
                        <div class="container text-center">
                            <a href="saveTicket?travelId=<%= tickets1.getTravelId() %>" class="btn btn-primary">YES</a>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">NO</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% }
            session.removeAttribute("tickets");
            }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
