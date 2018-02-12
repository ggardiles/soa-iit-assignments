<%@ page import="models.Customer" %><%--
  Created by IntelliJ IDEA.
  User: ggardiles
  Date: 2/9/18
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <title>Title</title>
</head>
<body>
<div class="jumbotron d-flex align-items-center">
    <div class="container">

        <ul class="list-group">
            <%
                Customer customer = (Customer) session.getAttribute("customer");
                String fullAddress = "";
                if (customer != null) {
                    fullAddress = customer.getStreet() + ", " + customer.getCity() + ", " + customer.getState();
                }
            %>

            <jsp:include page="/LatLngServlet">
                <jsp:param name="fullAddress" value="<%= fullAddress %>" />
            </jsp:include>

            <%
                if (customer != null) {
                    out.println("<li class=\"list-group-item\"><strong>Customer Name: </strong>" + customer.getName() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>SSN: </strong>" + customer.getSsn() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>ZIP: </strong>" + customer.getZip() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>Email: </strong>" + customer.getEmail() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>Street: </strong>" + customer.getStreet() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>City: </strong>" + customer.getCity() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>State: </strong>" + customer.getState() + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>Latitude: </strong>" + request.getAttribute("latitude") + "</li>");
                    out.println("<li class=\"list-group-item\"><strong>Longitude: </strong>" + request.getAttribute("longitude") + "</li>");
                }
            %>

        </ul>
    </div>
</div>
</body>
</html>
