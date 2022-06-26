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
                <img src="./assets/images/avatars/user-avatar.jpg" alt=""
                     class="account__sub-img">
                <div class="account__sub-info">
                  <h2 class="account__sub-name">Sejima Kouga</h2>
                  <p class="account__sub-role">Chủ trọ</p>
                </div>
              </div>
              <div class="account__wrapper">
                <div class="account__group">
                  <p class="account__title">Họ và tên:</p>
                  <h3 class="account__content">Sejima Kouga</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Giới tính:</p>
                  <h3 class="account__content">Trống</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Ngày tháng năm sinh:</p>
                  <h3 class="account__content">12/12/2022</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Email:</p>
                  <h3 class="account__content">trống</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Số điện thoại:</p>
                  <h3 class="account__content">Trống</h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Địa chỉ:</p>
                  <h3 class="account__content">
                    999 Hoàng Hữu Nam, phường Long Thạnh Mỹ, thành phố Thủ Đức, thành phố Hồ Chí
                    Minh
                  </h3>
                </div>
                <div class="account__group">
                  <p class="account__title">Số CMND/CCCD:</p>
                  <h3 class="account__content">Trống</h3>
                </div>
              </div>
              <div class="account__actions">
                <a href="../../system/login.html" class="account__action account__logout">
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
                  <img id="update__img" src="./assets/images/avatars/user-avatar.jpg"
                       alt="" class="update__img">
                  <input id="update__input-img" type="file"
                         accept="image/x-png,image/gif,image/jpeg" class="update__input-img">
                  <button id="update__reset-img" class="update__reset-img">Đặt lại</button>
                </div>
                <form class="row mt-4">
                  <div class="form-group col-6">
                    <label for="fullname" class="form-label">Họ và tên:
                      <span>*</span></label>
                    <input id="fullname" name="fullname" value="" type="text"
                           class="form-control" placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="gender" class="form-label">Giới tính: <span>*</span></label>
                    <select id="gender" name="gender" class="form-control">
                      <option value="1">Nam</option>
                      <option value="0">Nữ</option>
                    </select>
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="birthday" class="form-label">Ngày sinh:
                      <span>*</span></label>
                    <input id="birthday" name="birthday" value="" type="text"
                           class="form-control" placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="cccd" class="form-label">Số CMND/CCCD:
                      <span>*</span></label>
                    <input id="cccd" name="cccd" value="" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="email" class="form-label">Email: <span>*</span></label>
                    <input id="email" name="email" value="" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-6">
                    <label for="phone" class="form-label">Số điện thoại:
                      <span>*</span></label>
                    <input id="phone" name="phone" value="" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-group col-12">
                    <label for="address" class="form-label">Địa chỉ: <span>*</span></label>
                    <input id="address" name="address" value="" type="text" class="form-control"
                           placeholder="Nhập ...">
                    <span class="form-message"></span>
                  </div>
                  <div class="form-actions">
                    <button class="update-btn update-submit-btn">
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
                <form class="row">
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
                      <input id="old-password" type="password" placeholder="Nhập ..."
                             class="form-control">
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="new-password" class="form-label">
                        Mật khẩu mới: <span>*</span>
                      </label>
                      <input id="new-password" type="password" placeholder="Nhập ..."
                             class="form-control">
                      <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                      <label for="confirm-password" class="form-label">
                        Xác nhận mật khẩu: <span>*</span>
                      </label>
                      <input id="confirm-password" type="password" placeholder="Nhập ..."
                             class="form-control">
                      <span class="form-message"></span>
                    </div>
                  </div>
                  <div class="form-actions d-flex justify-content-evenly">
                    <button id="change-psw-cancel-btn" class="update-btn update-cancel-btn">
                      Hủy bỏ
                    </button>
                    <button class="update-btn update-submit-btn">
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

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>

<script>
  $(document).ready(function () {

    const tabs = document.querySelectorAll(".tabs-item");
    const contents = document.querySelectorAll(".content__item");

    ((index = 0) => {
      tabs[index].classList.add("active");
      contents[index].classList.add("active");
    })(0);

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
<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
