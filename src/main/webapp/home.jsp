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
    <link rel="stylesheet" href="./assets/css/system_style/home_style/home.css">
</head>

<body class="position-relative over-flow-hidden">
<div id="preloader">
    <div class="dots">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

<!-- Navbar -->
<div class="home-navbar">
    <div class="container navbar-pc">
        <div class="row">
            <div class="col-3">
                <!-- Logo -->
                <div class="home-navbar__logo">
                    <a href="#top" class="home-navbar__logo-link">
                        <img class="home-navbar__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9">
                <!-- Actions -->
                <div class="home-navbar__actions">
                    <div id="" class="home-navbar__links">
                        <div class="home-navbar__actions-group">
                            <a href="#hostel-owner"
                               class="home-navbar__actions-link home-navbar__actions-link--owner">
                                Chủ trọ
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <a href="#renter"
                               class="home-navbar__actions-link home-navbar__actions-link--renter">Người
                                thuê</a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <a href="loginPage"
                               class="home-navbar__actions-link home-navbar__actions-link--login">
                                Đăng nhập
                            </a>
                        </div>

                        <div class="home-navbar__actions-group">
                            <button class="home-navbar__actions-link home-navbar__actions-link--register">
                                Đăng ký
                                <div class="register-modal">
                                    <a href="registerPage" class="register-owner">Chủ trọ</a>
                                    <a href="renter-register-page" class="register-renter">Người thuê</a>
                                </div>
                            </button>
                        </div>
                    </div>
                    <div id="home-navbar__mobile-btn" class="home-navbar__mobile-btn">
                        <i class="fa-solid fa-bars"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Menu mobile -->
    <div id="navbar-mobile" class="container navbar-mobile">
        <div class="navbar-mobile-group">
            <div class="navbar-mobile-group-header">Đăng ký</div>
            <a href="registerPage" class="navbar-mobile-link navbar-mobile-link--owner">Chủ trọ</a>
            <a href="#" class="navbar-mobile-link navbar-mobile-link--renter">Người thuê</a>
        </div>
        <div class="navbar-mobile-group">
            <a href="loginPage" class="navbar-mobile-link navbar-mobile-link--login">Đăng nhập</a>
        </div>
    </div>
</div>

<!-- Banner -->
<div id="hostel-owner" class="banner banner-hostel-owner-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <div class="banner-left">
                    <div class="banner-title" data-aos="fade-right" data-aos-duration="1000">
                        Quản lý toàn bộ khu trọ và phòng trọ ở cùng một nơi
                    </div>
                    <div class="banner-subtitle" data-aos="fade-right" data-aos-duration="1500">
                        HQT-Hostel là một nền tảng cung cấp giải pháp quản lý thuận tiện, thông minh,...
                    </div>
                    <a href="registerPage" class="banner-link mb-5 mb-lg-9" data-aos="fade-up"
                       data-aos-duration="2000">
                        Trở thành chủ nhà Ngay
                    </a>
                </div>
            </div>
            <div class="col-12 col-lg-6">
                <div class="banner-right">
                    <img src="./assets/images/banners/hostel-1.png" alt="Hostel owner banner" class="banner-image"
                         data-aos="flip-down" data-aos-duration="2000">
                </div>
            </div>
        </div>
    </div>
</div>
<div id="renter" class="banner banner-renter-wrapper">
    <div class="bg-holder" style="background-image: url('./assets/images/background/shape.png');"></div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <div class="banner-right">
                    <img src="./assets/images/banners/hostel-2.png" alt="Hostel owner banner" class="banner-image"
                         data-aos="flip-up" data-aos-duration="1500">
                </div>
            </div>
            <div class="col-12 col-lg-6">
                <div class="banner-left">

                    <div class="banner-title mt-5 mt-lg-0" data-aos="fade-down-left" data-aos-duration="1500">
                        Kết nối với chủ nhà nhanh chóng và phản hồi sự cố kịp thời
                    </div>
                    <div class="banner-subtitle" data-aos="zoom-in" data-aos-duration="1000">
                        HQT-Hostel là một nền tảng cho phép người thuê dễ dàng kết nối với
                        chủ trọ, xem thông tin và hàng loạt tính năng mới
                    </div>
                    <a href="renter-register-page" class="banner-link renter-link" data-aos="fade-up" data-aos-duration="1000">
                        Trở thành người thuê Ngay
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row wrapper">
            <div class="col-12 mb-5">
                <div class="row">
                    <div class="col-6 col-md-3">
                        <div class="footer-header">
                            <img class="footer-logo" src="./assets/images/logos/logo.png" alt="Logo">
                        </div>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="footer-header">Quick links</div>
                        <ul class="footer-list">
                            <li class="footer-item"><a href="" class="footer-link">About us</a></li>
                            <li class="footer-item"><a href="" class="footer-link">FAQ</a></li>
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
                                <a href="" class="footer-link">
                                    <i class="footer-icon fa-brands fa-facebook-square"></i>
                                </a>
                                <a href="" class="footer-link">
                                    <i class="footer-icon fa-brands fa-twitter-square"></i></i>
                                </a>
                                <a href="https://github.com/HQT-Team" class="footer-link">
                                    <i class="footer-icon fa-brands fa-github-square"></i>
                                </a>
                                <a href="" class="footer-link">
                                    <i class="footer-icon fa-brands fa-linkedin"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center pt-5 pb-5">
                    <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- Script Bootstrap !important -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/system/home-handle.js"></script>
<!-- Loader -->
<script src="./assets/js/loading-handler.js"></script>
</body>

</html>