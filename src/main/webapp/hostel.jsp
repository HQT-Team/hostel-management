<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/images/favicon.png" type="image/x-icon" />
    <title>Trang chủ</title>
    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Base CSS !important -->
    <link rel="stylesheet" href="assets/css/style.css">
    <!-- Link your CSS here -->
    <link rel="stylesheet" href="assets/css/hostel_owner_style/hostel_list/style.css">
    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div class="app">

        <!-- Navbar -->
        <div class="main-nav bg-white">
            <div class="container">
                <div class="row main-nav-body">
                    <div class="col-3 col-lg-3 col-xl-3 col-xxl-2">
                        <div class="main-nav__logo">
                            <a href="" class="main-nav__logo-link">
                                <img class="main-nav__logo-img" src="assets/images/hql_logo.png" alt="Logo">
                            </a>
                        </div>
                    </div>
                    <div class="col-9 col-lg-9 col-xl-9 col-xxl-10 wrapper">
                        <div class="main-nav__label">
                            <h3 class="title">Khu trọ</h3>
                        </div>
                        <div class="main-nav__action">
                            <div id="nav-notification-btn" class="notification">
                                <i class="notification__icon fa-solid fa-bell"></i>

                                <!-- Remove class "active" when don't have any new notification -->
                                <span class="notification__warning active"><i
                                        class="fa-solid fa-exclamation"></i></span>
                            </div>
                            <div id="nav-profile-btn" class="profile">
                                <div class="profile__infor">
                                    <h3 class="infor__name">Nguyễn Lâm Thảo Tâm</h3>
                                    <span class="infor__role">Chủ phòng trọ</span>
                                </div>
                                <div class="profile__avatar">
                                    <img class="avatar__img" src="assets/images/user-avatar.jpg"
                                        alt="User avatar image">
                                </div>
                            </div>
                            <div id="menu-sidebar-btn" class="menu-sidebar-btn">
                                <i class="fa-solid fa-bars"></i>
                            </div>
                        </div>
                    </div>

                    <!-- List notification -->
                    <div id="notification-list" class="notification__list">
                        <div class="notification__list-header">
                            <h3>Thông báo</h3>
                            <a href="">Xem tất cả</a>
                        </div>
                        <ul class="notification__list-items">
                            <!-- If this notification has been read, please add more class "readed" -->
                            <li class="notification__item">
                                <a href="">
                                    <div class="marker"></div>
                                    <div class="notification__item-img">
                                        <i class="notification__item-icon fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <div class="notification__item-info">
                                        <div class="notification__item-name">
                                            <div class="room">
                                                Phòng số 11
                                            </div>
                                            <div class="hostel">
                                                <span>Khu trọ:</span> Nova land
                                            </div>
                                        </div>
                                        <div class="notification__item-content">
                                            Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                            qua sửa chữa giúp em với ạ!
                                        </div>
                                        <div class="notification__item-time">
                                            18 tiếng trước
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="notification__item">
                                <a href="">
                                    <div class="marker"></div>
                                    <div class="notification__item-img">
                                        <i class="notification__item-icon report fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <div class="notification__item-info">
                                        <div class="notification__item-name">
                                            <div class="room">
                                                Phòng số 11
                                            </div>
                                            <div class="hostel">
                                                <span>Khu trọ:</span> Nova land
                                            </div>
                                        </div>
                                        <div class="notification__item-content">
                                            Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                            qua sửa chữa giúp em với ạ!
                                        </div>
                                        <div class="notification__item-time">
                                            18 tiếng trước
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="notification__item readed">
                                <a href="">
                                    <div class="marker"></div>
                                    <div class="notification__item-img">
                                        <i class="notification__item-icon success fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <div class="notification__item-info">
                                        <div class="notification__item-name">
                                            <div class="room">
                                                Phòng số 11
                                            </div>
                                            <div class="hostel">
                                                <span>Khu trọ:</span> Nova land
                                            </div>
                                        </div>
                                        <div class="notification__item-content">
                                            Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                            qua sửa chữa giúp em với ạ!
                                        </div>
                                        <div class="notification__item-time">
                                            18 tiếng trước
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="notification__item">
                                <a href="">
                                    <div class="marker"></div>
                                    <div class="notification__item-img">
                                        <i class="notification__item-icon fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <div class="notification__item-info">
                                        <div class="notification__item-name">
                                            <div class="room">
                                                Phòng số 11
                                            </div>
                                            <div class="hostel">
                                                <span>Khu trọ:</span> Nova land
                                            </div>
                                        </div>
                                        <div class="notification__item-content">
                                            Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                            qua sửa chữa giúp em với ạ!
                                        </div>
                                        <div class="notification__item-time">
                                            18 tiếng trước
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="notification__item">
                                <a href="">
                                    <div class="marker"></div>
                                    <div class="notification__item-img">
                                        <i class="notification__item-icon fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <div class="notification__item-info">
                                        <div class="notification__item-name">
                                            <div class="room">
                                                Phòng số 11
                                            </div>
                                            <div class="hostel">
                                                <span>Khu trọ:</span> Nova land
                                            </div>
                                        </div>
                                        <div class="notification__item-content">
                                            Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                            qua sửa chữa giúp em với ạ!
                                        </div>
                                        <div class="notification__item-time">
                                            18 tiếng trước
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>

                    <!-- Profile dropdown -->
                    <div id="nav-profile-dropdown" class="profile__actions">
                        <a href="" class="action__view-profile-link">
                            <div class="action__image">
                                <img src="assets/images/user-avatar.jpg" alt="">
                            </div>
                            <div class="action__content">
                                <div class="title">Nguyễn Lâm Thảo Tâm</div>
                                <span class="subtitle">Xem trang cá nhân của bạn</span>
                            </div>
                        </a>
                        <div class="spacer"></div>
                        <a href="" class="action__logout">
                            <div class="action__image">
                                <i class="fa-solid fa-arrow-right-from-bracket"></i>
                            </div>
                            <div class="action__content">
                                <div class="title">Đăng xuất</div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Body -->
        <div class="container">
            <div class="row position-relative">

                <!-- Side bar -->
                <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                    <div id="main-side-bar" class="side-bar pt-5">
                        <div class="group-option">
                            <a href="" class="group-option__link">
                                <i class="group-option__icon fa-solid fa-gauge-high"></i>
                                <div class="group-option__label">Tổng quan</div>
                            </a>
                        </div>
                        <div class="group-option active">
                            <a href="" class="group-option__link">
                                <i class="group-option__icon fa-solid fa-hotel"></i>
                                <div class="group-option__label">Khu trọ</div>
                            </a>
                        </div>
                        <div class="group-option">
                            <a href="" class="group-option__link">
                                <i class="group-option__icon fa-solid fa-door-open"></i>
                                <div class="group-option__label">Phòng trọ</div>
                            </a>
                        </div>
                        <div class="group-option">
                            <a href="" class="group-option__link">
                                <i class="group-option__icon fa-solid fa-envelope-open-text"></i>
                                <div class="group-option__label">Thông báo</div>
                            </a>
                        </div>
                        <div class="group-option">
                            <a href="" class="group-option__link">
                                <i class="group-option__icon fa-solid fa-triangle-exclamation"></i>
                                <div class="group-option__label">Báo cáo</div>
                            </a>
                        </div>
                    </div>
                </div>

                <!-- Content -->
                <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
                    <div class="content-bar pt-5">
                        <div class="content-history">
                            <!-- <a href="" class="history-link">Hello</a> -->
                            <!-- <i class="fa-solid fa-chevron-right"></i> -->
                            <div class="current">
                                Danh sách khu trọ</div>
                        </div>
                        <div class="content-actions">
                            <a href="Layout/hostel-owner-layout/add-new-hostel.html" class="add-hostel-btn">
                                Thêm khu trọ mới
                            </a>
                        </div>
                    </div>
                    <div class="content-body">
                        <div class="hostel-list">
                            <div class="hostel-list__header">
                                Các khu trọ hiện tại
                            </div>
                            <div class="hostel-list__items mt-4">
                                <table id="hostel-table"
                                    class="mt-4 mb-4 table table-hover table-bordered table-striped hostel-table">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên</th>
                                            <th>Địa chỉ</th>
                                            <th>Hành động</th>
                                            <th>Hành động</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-light hostel-table__body">
                                        <tr>
                                            <td>1</td>
                                            <td>Nova Land</td>
                                            <td>226 Điện Biên Phủ, quận Bình Thạnh, TP. Hồ Chí Minh</td>
                                            <td>
                                                <a href="Layout/hostel-owner-layout/hostel-detail.html"
                                                    class="hostel-table__body-link-detail">Chi tiết</a>
                                            </td>
                                            <td>
                                                <button class="hostel-table__body-btn-edit" data-bs-toggle="modal"
                                                    data-bs-target="#edit-hostel-model-1">Chỉnh sửa</button>
                                                <!-- Change "data-bs-target" duplicate with "id" of modal for mapping, and each of couple of them must be unique -->
                                                <!-- Modal -->
                                                <div class="modal fade edit-hostel-model" id="edit-hostel-model-1"
                                                    tabindex="-1" aria-labelledby="edit-hostel-model-label"
                                                    aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="edit-hostel-model-label">
                                                                    Chỉnh sửa thông tin khu trọ</h5>
                                                                <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <form action="" method="post" class="custom-form">
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label for="hostel-name" class="form-label">Tên
                                                                            <span>*</span></label>
                                                                        <input id="hostel-name" name="hostel-name"
                                                                            type="text" value="" class="form-control"
                                                                            required>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="hostel-address"
                                                                            class="form-label">Địa chỉ
                                                                            <span>*</span></label>
                                                                        <input id="hostel-address" name="hostel-address"
                                                                            type="text" value="" class="form-control"
                                                                            required>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary"
                                                                        data-bs-dismiss="modal">Hủy bỏ</button>
                                                                    <button type="submit" class="btn btn-primary">Cập
                                                                        nhật</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="" class="hostel-table__body-link-room-list">Danh sách phòng</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
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
                            <div class="copyright-logo">
                                <img src="assets/images/hql_logo_white_notext.svg" alt="Logo">
                            </div>
                            <div class="copyright-content">© 2022 HQT-Hostel. All rights reserved.</div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

    </div>

    <!-- Script Bootstrap !important -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <!-- JQuery -->
    <script src="assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <!-- Link your script here -->
    <script src="assets/js/handle-main-navbar.js"></script>
    <!-- Simple Datatable JS -->
    <script src="assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            // Initial datatable
            $('#hostel-table').DataTable();
        });
    </script>
</body>

</html>