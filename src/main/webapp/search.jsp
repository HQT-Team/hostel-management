<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/06/2022
  Time: 9:59 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="search" method="POST" >
    <input type="text" name="txtkeyword" value="<%= (request.getParameter("txtsearch") == null ? "" : request.getParameter("txtsearch"))%>">
    <input type="text" name="txtsearchby">
    <input type="submit" value="search" name="action">
</form>
</body>
</html>
