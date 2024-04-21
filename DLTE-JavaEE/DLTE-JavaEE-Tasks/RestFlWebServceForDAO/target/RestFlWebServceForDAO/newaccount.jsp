<%--
  Created by IntelliJ IDEA.
  User: xxjainne
  Date: 4/21/2024
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("username") != null) {
%>

<%
    String info = (String) request.getAttribute("info");
%>

<div class="container">
    <% if (info != null && info != "") { %>
    <h1 class="text-center text-success"><%= info %></h1>
    <% } %>
    <h1>Create Account</h1>
    <form action="CreateAccountServlet" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="customerId" class="form-label">Customer ID</label>
            <input type="text" id="customerId" name="customerId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="accountNumber" class="form-label">Account Number</label>
            <input type="text" id="accountNumber" name="accountNumber" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="balance" class="form-label">Balance</label>
            <input type="text" id="balance" name="balance" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<% } else {
    response.sendRedirect("index.jsp");
} %>

</body>
</html>
