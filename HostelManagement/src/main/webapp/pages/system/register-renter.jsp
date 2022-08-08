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
  <link rel="stylesheet" href="./assets/css/system_style/register-renter_style/style.css">
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
          Tham gia phòng trọ
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Content -->
<div class="container pt-5 pb-5 register-content">
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
          <form action="invite-code" method="POST" id="register-form"
                class="custom-form register-form">
            <div class="form-header">
              <h3 class="form-title">Mã tham gia</h3>
              <div class="form-subtitle"><span>*</span>
                Vui lòng nhập mã do chủ
                trọ của bạn cung cấp
                để tiến hành
                đăng ký!</div>
            </div>
            <div class="spacer"></div>
            <div class="form-group">
              <label for="invite-code" class="form-label">Mã tham gia <span>*</span></label>
              <input id="invite-code" name="invite-code" type="text" value=""
                     placeholder="Nhập mã tham gia do chủ trọ cung cấp" class="form-control">
              <span class="form-message"></span>
            </div>
            <div class="form-error-message">
                ${requestScope.RESPONSE_MSG.content}
            </div>
            <button class="form-submit">Tham gia</button>
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

<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>

<!-- Link your script here -->
<script src="./assets/js/valid-form.js"></script>
</body>

</html>
