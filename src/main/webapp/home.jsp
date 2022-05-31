<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27/05/2022
  Time: 9:01 CH
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
    <link rel="stylesheet" href="./assets/css/home_style/style.css">
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
            <div class="col-9 nav-left-side">
                <div id="main-nav__actions" class="main-nav__actions">
                    <div class="action-group">
                        <a href="" class="action-link about-us">Về chúng tôi</a>
                    </div>
                    <div class="action-group">
                        <button class="action-link register">Đăng ký

                            <div class="register-model">
                                <a href="registerPage" class="register-owner">Chủ trọ</a>
                                <a href="" class="register-renter">Người thuê</a>
                            </div>
                        </button>
                    </div>
                    <div class="action-group">
                        <a href="loginPage" class="action-link login">Đăng nhập</a>
                    </div>
                </div>
                <div id="mobile-menu-btn" class="mobile-menu-btn"><i class="fa-solid fa-bars"></i></div>
            </div>
        </div>
    </div>
</div>

<!-- Content -->
<div class="banner banner-hostel-owner-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6">
                <div class="banner-left">
                    <div class="banner-title">Quản lý toàn bộ khu trọ và phòng trọ ở cùng một nơi</div>
                    <div class="banner-subtitle">HQT-Hostel là một nền tảng cung cấp giải pháp quản lý
                        thuận tiện, thông minh,...</div>
                    <a href="registerPage" class="banner-link">Trở thành chủ nhà Ngay</a>
                </div>
            </div>
            <div class="col-12 col-md-6">
                <div class="banner-right">
                    <img src="./assets/images/hostel-1.png" alt="Hostel owner banner" class="banner-image">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="banner banner-renter-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6">
                <div class="banner-right">
                    <img src="./assets/images/hostel-3.png" alt="Hostel owner banner" class="banner-image">
                </div>
            </div>
            <div class="col-12 col-md-6">
                <div class="banner-left">

                    <div class="banner-title mobile-mt">Kết nối với chủ nhà nhanh chóng và phản hồi sự cố kịp
                        thời</div>
                    <div class="banner-subtitle">HQT-Hostel là một nền tảng cho phép người thuê dễ dàng kết nối với
                        chủ trọ, xem thông tin và hàng loạt tính năng mới</div>
                    <a href="" class="banner-link renter-link">Trở thành người thuê Ngay</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Product</div>
                        <ul class="footer-list">
                            <li class="footer-item"><a href="" class="footer-link">Feature</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Integration</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Chorme extension</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Company</div>
                        <ul class="footer-list">
                            <li class="footer-item"><a href="" class="footer-link">Team</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Customer</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Blog</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Terms of Service</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Privacy Policy</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Support</div>
                        <ul class="footer-list">
                            <li class="footer-item"><a href="" class="footer-link">Slack community</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Support Docs</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Helpdesk API Docs</a></li>
                            <li class="footer-item"><a href="" class="footer-link">Services Status</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Contact</div>
                        <ul class="footer-list">
                            <li class="footer-item">support@hqlhostel.com</li>
                            <li class="footer-item">+84(999) 123456</li>
                            <li class="footer-item footer-socials">
                                <a href="" class="footer-link"><i
                                        class="footer-icon fa-brands fa-facebook-square"></i></a>
                                <a href="" class="footer-link"><i
                                        class="footer-icon fa-brands fa-twitter-square"></i></a>
                                <a href="" class="footer-link"><i
                                        class="footer-icon fa-brands fa-github-square"></i></a>
                                <a href="" class="footer-link"><i class="footer-icon fa-brands fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
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
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Link your script here -->
<script>
    $(document).ready(function () {
        document.addEventListener("click", function (event) {
            const navMobileBtn = $("#mobile-menu-btn");
            const navActions = $("#main-nav__actions");

            // If user clicks inside the element, do nothing
            if (event.target.closest("#mobile-menu-btn")) {
                if (navActions.css("display") === "block") {
                    navActions.css("display", "none");
                    navMobileBtn.removeClass("active");
                } else {
                    navActions.css("display", "block");
                    navMobileBtn.addClass("active");
                }
            } else {
                // If user clicks outside the element, hide it!
                navActions.css("display", "none");
                navMobileBtn.removeClass("active");
            }

            if (event.target.closest("#main-nav__actions")) {
                navMobileBtn.addClass("active");
                navActions.css("display", "block");
            }
        });
    });
</script>
</body>

</html>
