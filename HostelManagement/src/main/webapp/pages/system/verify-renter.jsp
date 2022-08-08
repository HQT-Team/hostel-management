<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>Xác thực</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/verify-renter_style/style.css">
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
                    Xác thực
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Content -->
<div class="container pt-5 verify-content">
    <div class="row">

        <!-- Register form -->
        <div class="col-12">
            <div class="row">
                <div class="col-xs-9 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4 m-auto">
                    <form action="check-otp" method="POST" id="verify-form" class="custom-form verify-form">
                        <input type="hidden" name="account_id" value="${requestScope.ACCOUNT_ID}">
                        <div class="form-header">
                            <h3 class="form-title">Xác thực bạn chính xác là người thuê</h3>
                            <div class="form-subtitle">
                                <span>*</span> Một mã xác nhận đã được gửi đến email mà bạn cung cấp cho chủ trọ!
                                Vui lòng điền vào ô
                                dưới để xác thực bạn chính là người thuê!
                            </div>
                        </div>
                        <div class="spacer"></div>
                        <div class="row form-group">
                            <div class="col-8">
                                <label for="confirm-code" class="form-label">Mã xác thực <span>*</span></label>
                                <input id="confirm-code" name="otp" type="confirm-code"
                                       placeholder="Nhập mã xác thực" class="form-control">
                            </div>
                            <div class="col-4">
                                <label class="form-label"
                                       style="color: transparent; user-select: none;">Button</label>
                                <button id="resend-btn" class="form-control btn btn-primary disabled">
                                    Đang gửi
                                </button>
                            </div>
                            <span class="form-message">
                                ${requestScope.RESPONSE_MSG.content}
                            </span>
                        </div>
                        <div class="spacer"></div>
                        <button type="submit" class="form-submit">Xác thực</button>
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

<!-- Toast element -->
<div id="toast">&nbsp;</div>

<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/valid-form.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<script>
    $ = document.querySelector.bind(document);

    Validator({
        form: "#verify-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#confirm-code", "Vui lòng nhập mã xác thực"),
        ],
    });

    const resendBtnElement = $('#resend-btn');

    const handleResendBtn = (currentSecond) => {
        let count = currentSecond ? currentSecond : 180;

        let intervalId = setInterval(() => {
            count--;
            resendBtnElement.innerHTML = count + ` giây`;

            if (count == 0) {
                clearInterval(intervalId);
                resendBtnElement.innerHTML = "Gửi lại"
                resendBtnElement.classList.remove("disabled");
            }
        }, 1000);
    }

    const handleSendOtp = () => {

            axios.get('http://localhost:8080/HappyHostel/send-otp', {
                params: {
                    account_id: '${requestScope.ACCOUNT_ID}'
                }
            })
            .then(function (response) {
                if (response.data.status === true) {
                    handleResendBtn(180);
                    toast({
                        title: 'Thành công',
                        message: response.data.content,
                        type: 'success',
                        duration: 10000
                    });
                } else {
                    resendBtnElement.innerHTML = "Gửi lại"
                    resendBtnElement.classList.remove("disabled");
                    toast({
                        title: 'Thất bại',
                        message: response.data.content,
                        type: 'error',
                        duration: 10000
                    });
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    resendBtnElement.addEventListener('click', (e) => {
        e.preventDefault();
        resendBtnElement.classList.add("disabled");
        resendBtnElement.innerHTML = 'Đang gửi';
        handleSendOtp();
    })

    <c:choose>
        <c:when test="${requestScope.RESPONSE_MSG eq null}">
            handleSendOtp();
        </c:when>
        <c:otherwise>
            resendBtnElement.classList.remove("disabled");
            resendBtnElement.innerHTML = "Gửi lại";
        </c:otherwise>
    </c:choose>
</script>
</body>

</html>
