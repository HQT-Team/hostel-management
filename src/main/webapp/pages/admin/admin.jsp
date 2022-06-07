<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi" id="top">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Trang chủ</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- AOS JS -->
    <link rel="stylesheet" href="./assets/js/aos_js/dist/aos.css">
    <script src="./assets/js/aos_js/dist/aos.js"></script>

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/admin.css">
</head>

<body>
<div class="navbar row">
    <div class="col-md-9 col-sm-9 col-lg-9 title">
        <h2>Admin</h2>
    </div>
    <div class="col-md-3 col-sm-3 col-lg-3 logout"><a href="../system/login.jsp"><button>Logout</button></a>
    </div>
</div>
<div class="content row">
    <div class="content-left col-sm-3">
        <a href=""><button style="color: #ffffff; background-color: #2f4eff;">Trang Chủ</button></a>
        <br>
        <form action="list-owner-account">
            <button type="submit">Tài Khoản</button>
        </form>

    </div>
    <div class="content-right col-sm-9">
        <h3>Quản Lí Phòng Trọ</h3>
        <p id="content--add">Ngày càng có nhiều nền khoa học thế giới thứ ba và
            những người được đào tạo về công nghệ học đang hướng
            đến các quốc gia thịnh vượng hơn với mức lương cao hơn
            và điều kiện làm việc tốt hơn.</p>
        <button class="btn btn-primary">Đọc thêm</button><br>
        <img src="https://s3.us-west-2.amazonaws.com/www.bookingninjas.com/img/illustration-2.svg">
    </div>
</div>
<footer>

</footer>











<!-- Script Bootstrap !important -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/system/home-handle.js"></script>
<script src="./assets/js/admin/admin-home-page-1.js"></script>
</body>

</html>