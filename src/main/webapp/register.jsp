<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/images/favicon.png" type="image/x-icon" />
    <title>Đăng ký</title>
    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Base CSS !important -->
    <link rel="stylesheet" href="./assets/css/style.css">
    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/register/register_style.css">
</head>

<body class="bg-light">

    <!-- Navbar -->
    <div class="main-nav bg-white">
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <div class="main-nav__logo">
                        <a href="HomePage" class="main-nav__logo-link">
                            <img class="main-nav__logo-img" src="./assets/images/hql_logo.png" alt="Logo">
                        </a>
                    </div>
                </div>
                <div class="col-9">
                    <div class="main-nav__title">
                        Đăng ký
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
                <a href="HomePage" class="turn-back__link">
                    <i class="turn-back__link-icon fa-solid fa-caret-left"></i>
                    Quay về trang chủ
                </a>
            </div>

            <!-- Register form -->
            <div class="col-12">
                <div class="row">
                    <div class="col-xs-11 col-sm-11 col-md-8 col-lg-6 col-xl-5 col-xxl-5 m-auto">
                        <form action="" method="POST" id="register-form" class="custom-form register-form">
                            <div class="form-header">
                                <h3 class="form-title">Tạo tài khoản mới</h3>
                                <div class="form-subtitle">Nhanh chóng và dễ dàng</div>
                            </div>
                            <div class="spacer"></div>
                            <div class="form-group">
                                <label for="fullname" class="form-label">Tên đầy đủ <span>*</span></label>
                                <input id="fullname" name="fullname" type="text" placeholder="VD: Nguyễn Văn A"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="username" class="form-label">Tên tài khoản <span>*</span></label>
                                <input id="username" name="username" type="text" placeholder="Nhập tên tài khoản"
                                    class="form-control">
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
                                <label for="email" class="form-label">Email <span>*</span></label>
                                <input id="email" name="email" type="text" placeholder="Nhập email"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="cccd" class="form-label">CCCD/CMND <span>*</span></label>
                                <input id="cccd" name="cccd" type="text" placeholder="Nhập số CCCD/CMND"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="register-policy">
                                Bằng cách nhấp vào Đăng ký, bạn đồng ý với <a href="">Điều
                                    khoản</a>, <a href="">Chính
                                    sách Dữ liệu</a> và <a href="">Chính sách
                                    Cookie</a> của chúng tôi.
                            </div>
                            <button class="form-submit">Đăng ký</button>
                            <div class="spacer"></div>
                            <div class="form-other-link">Đã có tài khoản? <a href="loginPage">Đăng nhập ngay!</a>
                            </div>
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
                        <div class="copyright-logo">
                            <img src="./assets/images/hql_logo_white_notext.svg" alt="Logo">
                        </div>
                        <div class="copyright-content">© 2022 HQT-Hostel. All rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>


    <!-- Script Bootstrap !important -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <!-- Link your script here -->
    <script src="./assets/js/valid-form.js"></script>
    <script>
        Validator({
            form: '#register-form',
            formGroupSelector: '.form-group',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#fullname', 'Vui lòng nhập tên đầy đủ của bạn'),
                Validator.isRequired('#username', 'Vui lòng nhập tên tài khoản'),
                Validator.isRequired('#email', 'Vui lòng nhập email của bạn'),
                Validator.isEmail('#email'),
                Validator.isRequired('#cccd', 'Vui lòng nhập số CMND hoặc CCCD của bạn'),
                Validator.isCCCD('#cccd'),
                Validator.minLength('#password', 6),
                Validator.isRequired('#confirm-password'),
                Validator.isConfirmed('#confirm-password', function () {
                    return document.querySelector('#register-form #password').value;
                }, 'Mật khẩu nhập lại không chính xác'),
            ]
        });
    </script>
</body>

</html>