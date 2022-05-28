<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21/05/2022
  Time: 8:52 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login" METHOD="post">
        <input type="text" name="txtemail">
        <input type="password" name="txtpassword">
        <input type="checkbox" name="savelogin" value="savelogin" id="rememberMe"><label for="rememberMe">Remember me</label>
        <input type="submit" value="Login" name="Login">
        <p style="color: #ff0000"><%= (request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%></p>
    </form>
</body>
</html>
