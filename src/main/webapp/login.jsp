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
        <button type="submit" value="Login" name="action">Submit</button>
    </form>
</body>
</html>
