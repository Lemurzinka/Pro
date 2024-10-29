<%@ page import="academy.prog.User" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: kakac
  Date: 29.10.2024
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Response</title>
</head>
<body>
<% ArrayList<User> users = (ArrayList<User>) session.getAttribute("users"); %>

<% for (User user: users) {%>
<div class="name"> <span>Name: <%= user.getName() %> </span></div>
<div class="lastname"> <span>Lastname: <%= user.getLastName() %> </span></div>
<div class="age"> <span>Age: <%= user.getAge() %> </span></div>
<div class="season"> <span>Season: <%= user.getSeason() %> </span></div>
<div class="gadget"> <span>Gadget: <%= user.getGadget() %> </span></div>


<%}%>

<a href="/index.jsp">Home</a>

</body>
</html>
