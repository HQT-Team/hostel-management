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
  <title>Chi tiết thông báo</title>

  <!-- Link Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

  <!-- Core CSS -->
  <link rel="stylesheet" href="./assets/css/core_style/core.css">

  <!-- Link your CSS here -->
  <link rel="stylesheet" href="./assets/css/hostel_owner_style/notification-detail_style/style.css">

</head>

<body class="over-flow-hidden">
<!-- Loader -->
<div id="preloader">
  <div class="dots">
    <div></div>
    <div></div>
    <div></div>
  </div>
</div>

<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

<!-- Body -->
<div class="container">
  <div class="row position-relative">
    <!-- Side bar -->
    <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
      <%@include file="./components/sidebar.jsp"%>
    </div>

    <!-- Content -->
    <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
      <!-- History link bar -->
      <div class="content-history">
        <a href="./notifications.html" class="history-link">Danh sách thông báo</a>
        <i class="fa-solid fa-chevron-right"></i>
        <div class="current">Thông báo #TB123</div>
      </div>
      <!-- Infor box -->
      <div class="col-xl-9 col-xxl-7 m-auto">
        <div class="content__body">
          <div class="notification">
            <h1 class="notification__title">Thông báo #TB123</h1>
            <div class="row">
              <div class="col-12 col-sm-6">
                <p class="notification__item">Khu trọ: <span>Nova land</span></p>
              </div>
              <div class="col-12 col-sm-6">
                <p class="notification__item">Ngày tạo: <span>22/02/2022</span></p>
              </div>
            </div>
            <div class="notification__item">Nội dung:</div>
            <p class="mt-3">
              Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
              has been the industry's standard dummy text ever since the 1500s, when an unknown
              printer took a galley of type and scrambled it to make a type specimen book. It has
              survived not only five centuries, but also the leap into electronic typesetting,
              remaining essentially unchanged. It was popularised in the 1960s with the release of
              Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
              publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
            <div class="notification__spacer"></div>
            <div class="notification__action">
              <a href="./notifications.html" class="notification__action-link">
                <i class="fa-solid fa-circle-arrow-left"></i> Quay lại
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp"%>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Link your script here -->


<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
