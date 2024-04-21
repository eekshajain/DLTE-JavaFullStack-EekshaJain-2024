<%--
  Created by IntelliJ IDEA.
  User: xxjainne
  Date: 4/21/2024
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Withdraw Amount</title>
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
    <% if (info != null && !info.isEmpty()) { %>
    <h1 class="text-center text-success"><%= info %></h1>
    <% } %>
    <h1>Withdraw Amount</h1>
    <form action="WithdrawServlet" method="post">
        <div class="mb-3">
            <label for="amount" class="form-label">Amount to Withdraw</label>
            <input type="text" id="amount" name="amount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Withdraw</button>
    </form>
</div>
<% } else {
    response.sendRedirect("index.jsp");
} %>
</body>
</html>
