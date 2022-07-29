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
    <title>Đặt lại mật khẩu</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/recover-password-reset_style/style.css">
</head>

<body class="bg-light ${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">
<!-- Preloader -->
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
                    Đặt lại mật khẩu
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Content -->
<div class="container pt-5 pb-5 recover-psw-content">
    <div class="row">
        <!-- Recover Password Form -->
        <div class="col-12">
            <div class="row">
                <div class="col-xs-11 col-sm-10 col-md-7 col-lg-6 col-xl-5 col-xxl-4 m-auto">
                    <form action="recover-password-reset" method="POST" id="recover-psw-form" class="custom-form recover-psw-form">
                        <input type="hidden" name="accountId" value="${requestScope.ACCOUNT_ID}" />
                        <div class="form-header">
                            <h3 class="form-title">
                                Tạo mới mật khẩu
                            </h3>
                            <div class="form-subtitle">
                                Mật khẩu của bạn nên khác với mật khẩu trước đó.!
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="form-label">Mật khẩu: <span>*</span></label>
                            <input id="password" name="password" type="password" value=""
                                   placeholder="Nhập mật khẩu" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <label for="confirm-password" class="form-label">
                                Xác nhận mật khẩu: <span>*</span>
                            </label>
                            <input id="confirm-password" name="confirm-password" type="password" value=""
                                   placeholder="Xác nhận mật khẩu" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <button type="submit" class="form-submit">Đặt Lại Mật Khẩu</button>
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

<c:if test="${requestScope.RESPONSE_MSG.status eq true}">
    <!-- Success Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Thông báo</h5>
                </div>
                <div class="modal-body mt-5 mb-5 text-success fs-2">
                    Cập nhật mật khẩu mới thành công
                </div>
                <div class="modal-footer justify-content-between">
                    <a href="HomePage" class="btn btn-secondary">Để sau</a>
                    <a href="loginPage"  class="btn btn-primary">Đăng nhập ngay</a>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- Toast element -->
<div id="toast">&nbsp</div>

<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Link your script here -->
<script src="./assets/js/valid-form.js"></script>
<script src="./assets/js/toast-alert.js"></script>
<script>

    <c:if test="${requestScope.RESPONSE_MSG.status eq false}">
        toast({
            title: 'Lỗi',
            message: '${requestScope.RESPONSE_MSG.content}',
            type: 'error',
            duration: 5000
        });
    </c:if>
    <c:if test="${requestScope.RESPONSE_MSG.status eq true}">
        const myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
        myModal.show();
    </c:if>

    Validator({
        form: "#recover-psw-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#password", "Vui lòng nhập mật khẩu"),
            Validator.minLength("#password", 6),
            Validator.isRequired("#confirm-password", "Vui lòng xác nhận lại mật khẩu"), ,
            Validator.isConfirmed("#confirm-password",
                function () {
                    return document.querySelector("#recover-psw-form #password").value;
                },
                "Mật khẩu nhập lại không chính xác"
            ),
        ],
    });
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>
