<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="./assets/css/system_style/verification-reigster-style/style.css">
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
            <a href="choose-type-register-page" class="turn-back__link">
                <i class="turn-back__link-icon fa-solid fa-caret-left"></i>
                Quay lại
            </a>
        </div>

        <!-- Register form -->
        <div class="col-12">
            <div class="row">
                <div class="col-12 col-sm-10 col-md-7 col-lg-5 col-xl-4 m-auto">
                    <c:choose>
                        <c:when test="${requestScope.verifyType eq 'admin-verify'}">
                            <form action="handle-verify" method="POST" id="input-email-form" class="custom-form verify-wrapper">
                                <div class="form-title">
                                    Cảm Ơn Bạn Đã Đăng Ký Tài Khoản Của Hệ Thống Chúng Tôi
                                </div>
                                <div class="form-subtitle">
                                    Tài khoản của bạn đang được quản trị viên xem xét và phê duyệt trong vòng 24 tiếng.
                                </div>
                                <input type="hidden" name="verifyType" value="admin-verify">
                                <div class="form-group">
                                    <label for="email" class="form-label">Vui lòng nhập email để nhận kết quả</label>
                                    <input id="email" name="email" type="text" class="form-control"
                                           placeholder="Nhập email của bạn">
                                    <span class="form-message"></span>
                                </div>
                                <button type="submit" class="form-submit">
                                    Xác Nhận Và Quay Về Trang Chủ
                                </button>
                            </form>
                        </c:when>
                    </c:choose>
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
<script>

    Validator({
        form: "#input-email-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#email", "Vui lòng nhập trường này"),
            Validator.isEmail("#email", "Vui lòng nhập đúng định dạng email"),
        ],
    });

</script>
</body>

</html>
