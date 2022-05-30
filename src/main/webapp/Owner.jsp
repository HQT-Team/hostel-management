<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28/05/2022
  Time: 10:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Owner</title>
</head>
<body>
<h1>Welcome ${sessionScope.USER.accountInfo.information.fullname}</h1>
<h1>Welcome ${sessionScope.USER.username}</h1>
<a href="logout">Log out</a>
</body>
</html>
