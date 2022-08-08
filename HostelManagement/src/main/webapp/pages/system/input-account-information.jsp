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
    <title>Đăng ký</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/input-account-information_style/style.css">
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
                    Tài khoản phòng trọ
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
            <div class="content__label">
                <div class="content__label-icons">
                    <i class="content__label-icon-1 fa-solid fa-certificate"></i>
                    <i class="content__label-icon-2 fa-solid fa-check"></i>
                </div>
                <p class="content__label-title">Một bước cuối nữa là xong rồi!</p>
            </div>
        </div>

        <!-- Register form -->
        <div class="col-12">
            <div class="row">
                <div class="col-xs-11 col-sm-11 col-md-8 col-lg-6 col-xl-5 col-xxl-5 m-auto">
                    <form action="renter-register" method="POST" id="register-form" class="custom-form register-form">
                        <input type="hidden" name="username" value="${sessionScope.RENTER_ACCOUNT_USERNAME}">
                        <input type="hidden" name="roomId" value="${sessionScope.RENTER_ACCOUNT_USERNAME}">
                        <div class="form-header">
                            <h3 class="form-title">Tài khoản đăng nhập</h3>
                            <div class="form-subtitle">
                                <span>*</span> Vui lòng điền thông tin để khôi phục tài khoản khi quên mật khẩu
                            </div>
                        </div>
                        <div class="spacer"></div>
                        <div class="form-group">
                            <label for="username" class="form-label">Tên tài khoản <span>*</span></label>
                            <input id="username" name="username" value="${sessionScope.RENTER_ACCOUNT_USERNAME}" type="text" disabled class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="row">
                            <div class="form-group col-6">
                                <label for="password" class="form-label">Mật khẩu <span>*</span></label>
                                <input id="password" name="password" type="password" placeholder="Nhập mật khẩu"
                                       class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group col-6">
                                <label for="confirm-password" class="form-label">Xác nhận <span>*</span></label>
                                <input id="confirm-password" name="confirm-password" type="password"
                                       placeholder="Xác nhận mật khẩu" class="form-control">
                                <span class="form-message"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fullname" class="form-label">Tên đại diện <span>*</span></label>
                            <input id="fullname" name="fullname" type="text" placeholder="VD: Nguyễn Văn A"
                                   class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="register-policy">
                            Bằng cách nhấp vào Gia nhập, bạn đồng ý với <a href="">Điều
                            khoản</a>, <a href="">Chính
                            sách Dữ liệu</a> và <a href="">Chính sách
                            Cookie</a> của chúng tôi.
                        </div>
                        <button type="submit" class="form-submit">Gia nhập</button>
                    </form>
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
        form: "#register-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#fullname", "Vui lòng nhập tên người đại diện"),
            Validator.minLength("#password", 6),
            Validator.isRequired("#confirm-password"),
            Validator.isConfirmed(
                "#confirm-password",
                function () {
                    return document.querySelector("#register-form #password").value;
                },
                "Mật khẩu nhập lại không chính xác"
            ),
        ],
    });

</script>
</body>

</html>
