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
  <link rel="stylesheet" href="./assets/css/system_style/recover-password_style/style.css">
</head>

<body class="bg-light">

<!-- Navbar -->
<div class="main-nav bg-white">
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="main-nav__logo">
          <a href="" class="main-nav__logo-link">
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
    <!-- Turn back -->
    <div class="col-12">
      <a href="./login.html" class="turn-back__link">
        <i class="turn-back__link-icon fa-solid fa-caret-left"></i>
        Quay lại trang đăng nhập
      </a>
    </div>

    <!-- Recover Password Form -->
    <div class="col-12">
      <div class="row">
        <div class="col-xs-11 col-sm-10 col-md-7 col-lg-6 col-xl-5 col-xxl-4 m-auto">
          <form id="recover-psw-form" class="custom-form recover-psw-form">
            <div class="form-header">
              <h3 class="form-title">Đặt lại mật khẩu</h3>
              <div class="form-subtitle">
                Vui lòng nhập email liên kết với tài khoản của bạn, chúng tôi sẽ gửi một hướng dẫn
                cách lấy lại mật khẩu về email đó.
              </div>
            </div>
            <div class="form-group">
              <label for="email" class="form-label">Địa chỉ email: <span>*</span></label>
              <input id="email" name="email" type="email" value="" placeholder="Nhập tài khoản"
                     class="form-control">
              <span class="form-message"></span>
            </div>
            <div class="form-error-message">
              Email bạn vừa nhập không ứng với tài khoản nào trong hệ thống! Vui lòng kiểm tra lại!
            </div>
            <!-- <button class="form-submit">Gửi Hướng Dẫn</button> -->
            <!-- Remove this a tag and using button tag above when you implement -->
            <a href="./recover-password-result.html" onMouseOver="this.style.color='#fff'"
               onMouseOut="this.style.color='#fff'" class="form-submit d-block w-100 text-center">
              Gửi Hướng Dẫn
            </a>
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
<script>
  Validator({
    form: "#recover-psw-form",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
      Validator.isRequired("#email", "Vui lòng nhập email"),
      Validator.isEmail("#email", "Vui lòng nhập đúng định dạng email"),
    ],
  });
</script>
</body>

</html>
