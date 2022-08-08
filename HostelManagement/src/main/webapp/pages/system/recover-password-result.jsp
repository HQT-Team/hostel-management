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
  <link rel="stylesheet" href="./assets/css/system_style/recover-password-result_style/style.css">
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
          Đặt lại mật khẩu
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Content -->
<div class="container pt-5 pb-5 recover-psw-result__content">
  <div class="row">

    <!-- Recover Password Form -->
    <div class="col-12">
      <div class="row">
        <div class="col-xs-11 col-sm-10 col-md-7 col-lg-6 col-xl-5 col-xxl-4 m-auto">
          <div class="recover-psw-result__wrapper">
            <div class="recover-psw-result__icon-wrapper">
              <i class="recover-psw-result__icon fa-solid fa-envelope-open-text"></i>
            </div>
            <h1 class="recover-psw-result__title">Kiểm tra email của bạn</h1>
            <div class="recover-psw-result__subtitle">
              Chúng tôi đã gửi một hướng dẫn khôi phục mật khẩu đến email của bạn.
            </div>
            <a href="https://mail.google.com/" target="_blank" class="recover-psw-result__btn">
              Mở ứng dụng email
            </a>
            <a class="recover-psw-result__link-return" href="loginPage">
              Bỏ qua, tôi sẽ xác nhận sau</a>
            <p>Chưa nhận được email? Kiểm tra hộp thư spam hoặc
              <a class="recover-psw-result__link-retry" href="recover-password">
                thử lại với email khác
              </a>
            </p>
          </div>
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
<div id="toast">&nbsp</div>

<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Link your script here -->
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
</body>

</html>
