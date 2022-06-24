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
    <title>Đăng ký</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/register_style/register.css">
</head>

<body class="bg-light ${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">
<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <div id="preloader">
        <div class="dots">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</c:if>

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
                        <form action="handle-register" method="POST" id="register-form" class="custom-form register-form">
                            <div class="form-header">
                                <h3 class="form-title">Tạo tài khoản mới</h3>
                                <div class="form-subtitle">Nhanh chóng và dễ dàng</div>
                            </div>
                            <input type="hidden" name="registertype" value="owner">
                            <div class="spacer"></div>
                            <div class="form-group">
                                <label for="fullname" class="form-label">Tên đầy đủ <span>*</span></label>
                                <input id="fullname" name="fullname" value="${requestScope.fullname}" type="text" placeholder="VD: Nguyễn Văn A"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="username" class="form-label">Tên tài khoản <span>*</span></label>
                                <input id="username" name="username" type="text" value="${requestScope.username}" placeholder="Nhập tên tài khoản"
                                    class="form-control">
                                <span class="form-message">${requestScope.ERROR_TYPE ne null && requestScope.ERROR_TYPE eq "username" && requestScope.RESPONSE_MSG ne null ? requestScope.RESPONSE_MSG.content : ""}</span>
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
                                <input id="email" name="email" value="${requestScope.email}" type="text" placeholder="Nhập email của bạn"
                                       class="form-control">
                                <span class="form-message">${requestScope.ERROR_TYPE ne null && requestScope.ERROR_TYPE eq "email" && requestScope.RESPONSE_MSG ne null ? requestScope.RESPONSE_MSG.content : ""}</span>
                            </div>
                            <div class="form-group">
                                <label for="cccd" class="form-label">CCCD/CMND <span>*</span></label>
                                <input id="cccd" name="cccd" value="${requestScope.cccd}" type="text" placeholder="Nhập số CCCD/CMND"
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
                            <div class="form-other-link">Đã có tài khoản? <a href="loginPage">Đăng nhập
                                    ngay!</a>
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
                        <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Toast element -->
    <div id="toast">&nbsp;</div>

    <!-- Script Bootstrap -->
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>

    <!-- Link your script here -->
    <script src="./assets/js/valid-form.js" charset="UTF-8"></script>
    <script type="module" src="./assets/js/system/register-handle.js" charset="UTF-8"></script>
    <script src="./assets/js/toast-alert.js"></script>
    <script>
        <c:choose>
            <c:when test="${requestScope.RESPONSE_MSG.status eq true}">
                toast({
                    title: 'Thành công',
                    message: '${requestScope.RESPONSE_MSG.content}',
                    type: 'success',
                    duration: 5000
                });
            </c:when>
            <c:when test="${requestScope.RESPONSE_MSG.status eq false}">
                toast({
                    title: 'Lỗi',
                    message: '${requestScope.RESPONSE_MSG.content}',
                    type: 'error',
                    duration: 5000
                });
        </c:when>
        </c:choose>
    </script>

    <c:if test="${requestScope.RESPONSE_MSG eq null}">
        <!-- Loader -->
        <script src="./assets/js/loading-handler.js"></script>
    </c:if>
</body>

</html>