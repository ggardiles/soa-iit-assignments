<%@ page import="javax.validation.constraints.NotNull" %>
<%@ page import="javax.validation.ValidatorFactory" %>
<%@ page import="javax.validation.Validation" %>
<%@ page import="javax.validation.Validator" %>
<%@ page import="models.Customer" %>
<%@ page import="javax.validation.ConstraintViolation" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: ggardiles
  Date: 2/7/18
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/error.css">

    <title>Title</title>
</head>
<body>
<center>
    <div class="alert alert-danger">
        <h1><strong>Invalid Data!</strong></h1>
        <ul>
            <%
                Customer customer = new Customer(request);

                System.out.println(customer.toString());

                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
                if (violations.isEmpty()) {
                    // Save Session Object
                    session.setAttribute("customer", customer);

                    // Forward user to ProcessCustomerDataRequest
                    response.sendRedirect("/ProcessCustomerDataRequest.jsp");
                } else {

                    // Report all errors to the user
                    for (ConstraintViolation<Customer> c : violations) {
                        out.println("<li><p>");
                        out.println(c.getMessage());
                        out.println("</p></li>");
                    }
                }
            %>
        </ul>
    </div>
    </ul>
</center>
</body>
</html>
