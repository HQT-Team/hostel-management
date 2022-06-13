<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Tổng quan</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/admin-dashboard/style.css">

</head>

<body>

<div class="app">

    <!-- Navbar -->
    <%@include file="./components/navbar.jsp"%>

    <!-- Body -->
    <div class="container">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="./components/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- Welcome -->
                <div class="content-welcome mt-5">
                    <img src="./assets/images/avatars/user-avatar.jpg" alt="User avatar" class="welcome-image">
                    <div class="welcome-content">
                        <div class="welcome-title">Chào mừng, <span>${sessionScope.USER.accountInfo.information.fullname}</span></div>
                        <p class="welcome-paragraph">Quản trị hệ thống thật là dễ :3</p>
                    </div>
                </div>

                <div class="content__body d-flex flex-column align-items-center m-auto">
                    <h1 class="content__body-title">Quản Lí Tài Khoản</h1>
                    <p class="content__body-intro mb-4">
                        Ngày càng có nhiều nền khoa học thế giới thứ ba và
                        những người được đào tạo về công nghệ học đang hướng
                        đến các quốc gia thịnh vượng hơn với mức lương cao hơn
                        và điều kiện làm việc tốt hơn.
                    </p>
                    <button type="button" class="content__body-btn btn btn-primary fs-3 px-3 m-auto">Đọc
                        thêm
                    </button>
                    <img class="img-fluid mt-5" src="./assets/images/banners/illustration-2.svg" alt="">
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./components/footer.jsp"%>

</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
</body>

</html>
