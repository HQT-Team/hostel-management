<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <title>Tài khoản</title>

  <!-- Link Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

  <!-- Core CSS -->
  <link rel="stylesheet" href="./assets/css/core_style/core.css">

  <!-- Link your CSS here -->
  <link rel="stylesheet" href="./assets/css/hostel_owner_style/profile_style/style.css">

  <!-- CSS Push Notification -->
  <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

<!-- Loader -->
<c:if test="${requestScope.RESPONSE_MSG eq null}">
  <div id="preloader">
    <div class="dots">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
</c:if>

<!-- Body -->
<div class="container">
  <div class="row position-relative">
    <!-- Side bar -->
    <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
      <%@include file="./components/sidebar.jsp"%>
    </div>

    <!-- Content -->
    <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">

      <div class="col-xxl-9 m-auto">
        <!-- Tab menu -->
        <div class="tabs">
          <div class="tabs-item">
            <i class="tabs-icon fa-solid fa-user-tie"></i> Tài khoản của tôi
          </div>
          <div class="tabs-item">
            <i class="tabs-icon fa-solid fa-file-pen"></i> Chỉnh sửa
          </div>
          <div class="tabs-item">
            <i class="tabs-icon fa-solid fa-wrench"></i> Đổi mật khẩu
          </div>
          <div class="line"></div>
        </div>

        <!-- Content item - Account Information -->
        <div class="content__item">
          <div class="row">
            <div class="col-12 col-md-8 col-lg-8 col-xl-7 m-auto">
              <div class="account__wrapper">
                <img src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg" alt="User avatar"
                     class="account__sub-img">
                <div class="account__sub-info">
                  <h2 class="account__sub-name">${sessionScope.USER.accountInfo.information.fullname}</h2>
                  <p class="account__sub-role">Chủ trọ</p>
                </div>
              </div>
              <div class="account__wrapper">
                <div class="account__group">
                  <p class="account__title">Họ và tên:</p>
                  <h3 class="account__content">${sessionScope.USER.accountInfo.information.fullname}</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Giới tính:</p>
                  <h3 class="account__content">
                    <c:choose>
                      <c:when test="${sessionScope.USER.accountInfo.information.sex ne null}">
                        ${sessionScope.USER.accountInfo.information.sex eq 1 ? "Nam" : "Nữ"}
                      </c:when>
                      <c:otherwise>
                        Trống
                      </c:otherwise>
                    </c:choose>
                  </h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Ngày tháng năm sinh:</p>
                  <h3 class="account__content">
                    <c:choose>
                      <c:when test="${sessionScope.USER.accountInfo.information.birthday ne null}">
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${sessionScope.USER.accountInfo.information.birthday}" var="dateOfBirth"/>
                        <fmt:formatDate var="dateOfBirthFormatted" pattern="dd/MM/yyyy" value="${dateOfBirth}"/>
                        ${dateOfBirthFormatted}
                      </c:when>
                      <c:otherwise>
                        Trống
                      </c:otherwise>
                    </c:choose>
                  </h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Email:</p>
                  <h3 class="account__content">${sessionScope.USER.accountInfo.information.email eq null ? "Trống" : sessionScope.USER.accountInfo.information.email}</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Số điện thoại:</p>
                  <h3 class="account__content">${sessionScope.USER.accountInfo.information.phone eq null ? "Trống" : sessionScope.USER.accountInfo.information.phone}</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Địa chỉ:</p>
                  <h3 class="account__content">
                    ${sessionScope.USER.accountInfo.information.address eq null ? "Trống" : sessionScope.USER.accountInfo.information.address}
                  </h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Số CMND/CCCD:</p>
                  <h3 class="account__content">
                    ${sessionScope.USER.accountInfo.information.cccd eq null ? "Trống" : sessionScope.USER.accountInfo.information.cccd}
                  </h3>
                </div>
              </div>
              <div class="account__actions">
                <a href="logout" class="account__action account__logout">
                  Đăng xuất
                </a>
                <button id="account__btn-update" class="account__action account__update">
                  Chỉnh sửa
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Content item - Update Account Information -->
        <div class="content__item">
          <div class="row">
            <div class="col-md-10 col-lg-9 col-xl-8 m-auto">
              <div class="update__wrapper">
                <div class="update__image">
                  <img id="update__img" src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg"
                       alt="" class="update__img">
                  <input id="update__input-img" type="file"
                         accept="image/x-png,image/gif,image/jpeg" class="update__input-img">
                  <button id="update__reset-img" class="update__reset-img">Đặt lại</button>
                </div>
                <form action="update-profile" method="POST" class="row mt-4" id="form-update-information">
                  <div class="form-group col-6">
                    <label for="fullname" class="form-label">Họ và tên:
                      <span>*</span></label>
                    <input id="fullname" name="fullname" value="${sessionScope.USER.accountInfo.information.fullname}" type="text"
                           class="form-control" placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="gender" class="form-label">Giới tính:</label>
                    <select id="gender" name="gender" class="form-control">
                      <option value="1" ${sessionScope.USER.accountInfo.information.sex eq 1 ? "selected" : ""}>Nam</option>
                      <option value="0" ${sessionScope.USER.accountInfo.information.sex eq 0 ? "selected" : ""}>Nữ</option>
                    </select>
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="birthday" class="form-label">Ngày sinh:</label>
                    <input id="birthday" name="birthday" value="${dateOfBirthFormatted}" type="text"
                           class="form-control" placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="cccd" class="form-label">Số CMND/CCCD:
                      <span>*</span></label>
                    <input id="cccd" name="cccd" value="${sessionScope.USER.accountInfo.information.cccd}" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="email" class="form-label">Email: <span>*</span></label>
                    <input id="email" name="email" value="${sessionScope.USER.accountInfo.information.email}" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="phone" class="form-label">Số điện thoại:</label>
                    <input id="phone" name="phone" value="${sessionScope.USER.accountInfo.information.phone}" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-12">
                    <label for="address" class="form-label">Địa chỉ:</label>
                    <input id="address" name="address" value="${sessionScope.USER.accountInfo.information.address}" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-actions">
                    <button type="submit" class="update-btn update-submit-btn">
                      Lưu thay đổi
                    </button>
                    <button id="update-cancel-btn" class="update-btn update-cancel-btn">
                      Hủy bỏ
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <!-- Content item - Change Password -->
        <div class="content__item">
          <div class="row">
            <div class="col-md-12 m-auto">
              <div class="change-psw__wrapper">
                <h1 class="change-psw__title">Thay đổi mật khẩu</h1>
                <form action="change-password" method="POST" class="row" id="form-update-password">
                  <div class="col-6 d-none d-md-block">
                    <div class="change-psw__suggest">Mật khẩu nên chứa</div>
                    <ul class="change-psw__list">
                      <div class="change-psw__item">
                        <i class="fa-solid fa-check"></i>
                        Ít nhất 6 kí tự và tối đa 60 kí tự
                      </div>
                      <div class="change-psw__item">
                        <i class="fa-solid fa-check"></i>
                        Nên chứa ít nhất 1 chữ hoa (A..Z)
                      </div>
                      <div class="change-psw__item">
                        <i class="fa-solid fa-check"></i>
                        Nên chứa ít nhất 1 chữ thường (a..z)
                      </div>
                      <div class="change-psw__item">
                        <i class="fa-solid fa-check"></i>
                        Nên chứa ít nhất 1 số (0..9)
                      </div>
                      <div class="change-psw__item">
                        <i class="fa-solid fa-check"></i>
                        Nên chứa ít nhất 1 kí tự đặc biệt (!@..)
                      </div>
                    </ul>
                  </div>
                  <div class="col-12 col-md-6">
                    <div class="form-group">
                      <label for="old-password" class="form-label">
                        Mật khẩu cũ: <span>*</span>
                      </label>
                      <input id="old-password" name="old-password" type="password" placeholder="Nhập mật khẩu hiện tại"
                             class="form-control">
                      <span class="form-message">${requestScope.ERROR ne null && requestScope.RESPONSE_MSG ne null ? requestScope.RESPONSE_MSG.content : ""}</span>
                    </div>
                    <div class="form-group">
                      <label for="new-password" class="form-label">
                        Mật khẩu mới: <span>*</span>
                      </label>
                      <input id="new-password" name="new-password" type="password" placeholder="Nhập mật khẩu mới"
                             class="form-control">
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="confirm-password" class="form-label">
                        Xác nhận mật khẩu: <span>*</span>
                      </label>
                      <input id="confirm-password" type="password" placeholder="Xác nhận mật khẩu mới"
                             class="form-control">
                      <span class="form-message"></span>
                    </div>
                  </div>
                  <div class="form-actions d-flex justify-content-evenly">
                    <button id="change-psw-cancel-btn" class="update-btn update-cancel-btn">
                      Hủy bỏ
                    </button>
                    <button type="submit" class="update-btn update-submit-btn">
                      Lưu thay đổi
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp"%>
<!-- Push notification element -->
<div id="push-noti"></div>


<!-- Toast element -->
<div id="toast">&nbsp;</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Validation -->
<script src="./assets/js/valid-form.js" charset="UTF-8"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>

<script type="text/javascript">
  // Receive
  receiveWebsocket(alertPushNoti);

  // Close when leave
  window.onbeforeunload = function(){
    receiveWebsocket.disconnectWebSocket();
  };
</script>

<script>
  Validator({
    form: "#form-update-information",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
      Validator.isRequired("#fullname", "Vui lòng nhập tên đầy đủ của bạn"),
      Validator.isRequired("#cccd", "Vui lòng nhập số CMND hoặc CCCD của bạn"),
      Validator.isCCCD("#cccd"),
      Validator.isViePhoneNumber("#phone"),
      Validator.isRequired("#email", "Vui lòng nhập email của bạn"),
      Validator.isEmail("#email"),
    ]
  });

  Validator({
    form: "#form-update-password",
    formGroupSelector: ".form-group",
    errorSelector: ".form-message",
    rules: [
      Validator.isRequired("#old-password", "Vui lòng nhập mật khẩu cũ"),
      Validator.isRequired("#new-password", "Vui lòng nhập mật khẩu mới"),
      Validator.minLength("#new-password", 6, "Mật khẩu mới phải chứa ít nhất 6 kí tự"),
      Validator.maxLength("#new-password", 50, "Mật khẩu mới dài tối đa 50 kí tự"),
      Validator.isRequired("#confirm-password", "Vui lòng xác nhận lại mật khẩu mới đã nhập"),
      Validator.isConfirmed(
              "#confirm-password",
              function () {
                return document.querySelector("#form-update-password #new-password").value;
              },
              "Mật khẩu nhập lại không chính xác"
      ),
    ]
  });

</script>
<script>
  $(document).ready(function () {

    const tabs = document.querySelectorAll(".tabs-item");
    const contents = document.querySelectorAll(".content__item");

    ((index = 0) => {
      tabs[index].classList.add("active");
      contents[index].classList.add("active");
    })(${requestScope.TYPE});

    const tabActive = document.querySelector(".tabs-item.active");
    const line = document.querySelector(".tabs .line");

    let i = 0, lengthTabs = tabs.length;

    line.style.left = tabActive.offsetLeft + "px";
    line.style.width = tabActive.offsetWidth + "px";

    tabs.forEach((tab, index) => {
      const content = contents[index];

      tab.onclick = function () {

        i = index;

        document.querySelector(".tabs-item.active").classList.remove("active");
        document.querySelector(".content__item.active").classList.remove("active");

        line.style.left = this.offsetLeft + "px";
        line.style.width = this.offsetWidth + "px";

        this.classList.add("active");
        content.classList.add("active");
      };
    });

    // Handle click update Btn
    const updateAccountBtn = document.getElementById("account__btn-update");
    updateAccountBtn.addEventListener("click", () => {
      tabs[1].click();
    });

    // Handle click cancel update btn
    $('#update-cancel-btn').click((e) => {
      e.preventDefault();
      tabs[0].click();
    });

    // Handle click cancel change password btn
    $('#change-psw-cancel-btn').click((e) => {
      e.preventDefault();
      tabs[0].click();
      $('#form-update-password').trigger("reset");
      $('#form-update-password .form-message').html("");
    });

    // Handle reset image
    let initImg = $('#update__img').attr('src');

    $('#update__reset-img').click(() => {
      $('#update__img').attr('src', initImg);
    });

    // Handle select image
    function readURL(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
          $('#update__img').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
      }
    }

    $("#update__input-img").change(function () {
      readURL(this);
    });

  });
</script>
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
