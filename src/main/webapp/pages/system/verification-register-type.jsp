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
    <title>Xác thực tài khoản</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/verification-register-type-style/style.css">
</head>

<body class="bg-light">

<!-- Navbar -->
<div class="main-nav bg-white">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="main-nav__logo">
                    <a href="HomePage" class="main-nav__logo-link">
                        <img class="main-nav__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9">
                <div class="main-nav__title">
                    Xác thực tài khoản
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Content -->
<div class="container pt-5 register-content">
    <div class="row">
        <!-- Turn back -->
        <div class="col-12">
            <a href="registerPage" class="turn-back__link">
                <i class="turn-back__link-icon fa-solid fa-caret-left"></i>
                Quay lại
            </a>
        </div>

        <!-- Register form -->
        <div class="col-12">
            <div class="row">
                <div class="col-12 col-sm-10 col-md-7 col-lg-5 col-xl-4 col-xxl-4 m-auto">
                    <div class="verify-wrapper">
                        <div class="verify-welcome">Chào mừng,</div>
                        <div class="verify-username">${sessionScope.REGISTER_ACCOUNT.accountInfo.information.fullname}</div>
                        <div class="verify-subtitle">Vui lòng chọn phương thức xác thực để tài khoản được kích hoạt
                            và có thể sử dụng
                        </div>
                        <div class="verify-links">
                            <a href="handle-verify?verifyType=admin-verify" class="verify-link">
                                Đợi quản trị viên duyệt
                            </a>
                            <a href="handle-verify?verifyType=email" class="verify-link">
                                Xác thực bằng email
                            </a>
                            <a href="handle-verify?verifyType=phone" class="verify-link">
                                Xác thực bằng số điện thoại
                            </a>
                        </div>
                        <div class="verify-notes">
                            <div class="verify-notes_title">Lưu ý:</div>
                            <ul class="verify-notes_list">
                                <li class="verify-notes_item">
                                    Thời gian duyệt tài khoản có thể bị kéo dài
                                </li>
                                <li class="verify-notes_item">
                                    Hãy xác thực tài khoản nhanh chóng bằng email hoặc số điện thoại
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="register-footer">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center">
                    <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/valid-form.js"></script>
</body>

</html>
