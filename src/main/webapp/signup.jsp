<%@ page import="entity.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Signup</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/signup.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background-color: #c0c8d0">

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
                    String messageSavePerson = (String) session.getAttribute("messageSavePerson");
                    if (messageSavePerson != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong><%= messageSavePerson %>
                    </strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <%
                        session.removeAttribute("messageSavePerson");
                    }
                %>
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
                <div class="card-header text-center fs-4"><h3>Signup</h3></div>
                <div class="card-body">
                    <form action="signup" method="post" target="_self">
                        <div class="mb-3">
                            <label for="firstName" class="form-label"><strong>Firstname :</strong></label>
                            <input type="text" class="form-control" id="firstName" name="firstName"
                                   placeholder="Enter your first name..." required>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label"><strong>Lastname :</strong></label>
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="Enter your last name..." required>
                        </div>
                        <div class="mb-3">
                            <label for="username" class="form-label"><strong>Username :</strong></label>
                            <input type="text" class="form-control" id="username" name="userName"
                                   placeholder="Enter your username..." required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label"><strong>Password :</strong></label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter your password..." required>
                        </div>
                        <div class="mb-3">
                            <strong>
                                <label>Gender :</label>
                                <label for="male">Male</label>
                                <input type="radio" id="male" name="optionGender" value="MALE" checked/>
                                <label for="female" style="margin-left: 10px">Female</label>
                                <input type="radio" id="female" name="optionGender" value="FEMALE"/>
                            </strong>
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-primary">Signup</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </div>
                        <div class="mt-3 text-center">
                            <strong>Already have an account? <a href="login.jsp">Login</a></strong>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
