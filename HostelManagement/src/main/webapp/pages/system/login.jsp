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
    <title>Đăng nhập</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/login_style/login.css">
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
                        Đăng nhập
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Content -->
    <div class="container pt-5 pb-5 login-content">
        <div class="row">
            <!-- Turn back -->
            <div class="col-12">
                <a href="HomePage" class="turn-back__link">
                    <i class="turn-back__link-icon fa-solid fa-caret-left"></i>
                    Quay về trang chủ
                </a>
            </div>

            <!-- Login Form -->
            <div class="col-12">
                <div class="row">
                    <div class="col-xs-11 col-sm-10 col-md-7 col-lg-6 col-xl-5 col-xxl-4 m-auto">
                        <form action="login" method="POST" id="login-form" class="custom-form login-form">
                            <div class="form-header">
                                <h3 class="form-title">Đăng nhập</h3>
                                <div class="form-subtitle">Đăng nhập và bắt đầu sử dụng ứng dụng</div>
                            </div>
                            <div class="spacer"></div>
                            <div class="form-group">
                                <label for="username" class="form-label">Tài khoản <span>*</span></label>
                                <input id="username" name="txtemail" type="text" value="" placeholder="Nhập tài khoản"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="password" class="form-label">Mật khẩu <span>*</span></label>
                                <input id="password" name="txtpassword" type="password" placeholder="Nhập mật khẩu"
                                    class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-error-message">
                                ${requestScope.RESPONSE_MSG.content}
                            </div>
                            <div class="row more-action">
                                <div class="col-6">
                                    <div class="form-group">
                                        <input id="remember" name="savelogin" type="checkbox" value="true"
                                            class="more-action__checkbox">
                                        <label for="remember" class="form-label more-action__checkbox-title">Ghi nhớ
                                            đăng nhập</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <a href="" class="more-action__forgot-link" data-bs-toggle="modal"
                                        data-bs-target="#forgot-password-modal">Quên mật khẩu</a>
                                </div>
                            </div>
                            <button class="form-submit">Đăng nhập</button>
                            <div class="spacer"></div>
                            <div class="form-other-link">Chưa có tài khoản? <a href="registerPage">Đăng ký ngay!</a>
                            </div>
                        </form>
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
                    <div class="copyright-wrapper d-flex justify-content-center">
                        <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Modal -->
    <div class="modal fade forgot-password-modal" id="forgot-password-modal" tabindex="-1"
        aria-labelledby="forgot-password-modal-label" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title forgot-password-modal-label" id="forgot-password-modal-label">Quên
                        mật khẩu?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body forgot-password-modal-body">
                    Hãy thư giãn và cố gắng thử nhớ lại mật khẩu của bạn!
                </div>
                <div class="modal-footer">
                    <a href="recover-password" class="btn btn-primary forgot-password-modal-btn">
                        Cảm ơn
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Toast element -->
    <div id="toast">&nbsp;</div>

    <!-- Script Bootstrap -->
    <script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <!-- Valid form -->
    <script src="./assets/js/valid-form.js"></script>
    <!-- Toast -->
    <script src="./assets/js/toast-alert.js"></script>
    <!-- Link your script here -->
    <script src="./assets/js/system/login-handle.js"></script>

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