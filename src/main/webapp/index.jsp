<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<h1>Welcome ${sessionScope.USER.accountInfo.information.fullname}</h1>
<h1>Welcome ${sessionScope.USER.username}</h1>
</body>
</html>