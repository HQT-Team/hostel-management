<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21/05/2022
  Time: 8:52 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/images/favicon.png" type="image/x-icon" />
    <title>Đăng nhập</title>
    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Base CSS !important -->
    <link rel="stylesheet" href="./assets/css/style.css">
    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/login/login_style.css">
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
