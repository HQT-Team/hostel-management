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
    <title>Chi tiết phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room_detail_style/style.css">

</head>

<body>
<!-- Navbar -->
<div class="main-nav bg-white">
    <div class="container">
        <div class="row main-nav-body">
            <div class="col-3 col-lg-3 col-xl-3 col-xxl-2">
                <div class="main-nav__logo">
                    <a href="" class="main-nav__logo-link">
                        <img class="main-nav__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9 col-lg-9 col-xl-9 col-xxl-10 wrapper">
                <div class="main-nav__label">
                    <h3 class="title">Phòng trọ</h3>
                </div>
                <div class="main-nav__action">
                    <div id="nav-notification-btn" class="notification">
                        <i class="notification__icon fa-solid fa-bell"></i>

                        <!-- Remove class "active" when don't have any new notification -->
                        <span class="notification__warning active"><i class="fa-solid fa-exclamation"></i></span>
                    </div>
                    <div id="nav-profile-btn" class="profile">
                        <div class="profile__infor">
                            <h3 class="infor__name">Nguyễn Lâm Thảo Tâm</h3>
                            <span class="infor__role">Chủ phòng trọ</span>
                        </div>
                        <div class="profile__avatar">
                            <img class="avatar__img" src="./assets/images/avatars/user-avatar.jpg"
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
                        <img src="./assets/images/avatars/user-avatar.jpg" alt="">
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
                <div class="group-option">
                    <a href="./hostel.html" class="group-option__link">
                        <i class="group-option__icon fa-solid fa-hotel"></i>
                        <div class="group-option__label">Khu trọ</div>
                    </a>
                </div>
                <div class="group-option active">
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
                <!-- History link bar -->
                <div class="content-history">
                    <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <a href="detailHostel?hostelID=${sessionScope.hostel.hostelID}"
                       class="history-link">${sessionScope.hostel.hostelName}</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <div class="current">Phòng ${requestScope.roomInformation.roomNumber}</div>
                </div>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9 m-auto">
                <div class="content-body">
                    <div class="room-header">
                        <h2 class="room-name">Phòng ${requestScope.roomInformation.roomNumber}</h2>
                        <!-- Add room button -->
                        <div class="room-actions">
                            <!-- Start update room information button -->
                            <button class="action-update-btn" data-bs-toggle="modal"
                                    data-bs-target="#update-room-infor-modal">Cập nhật
                            </button>
                            <!-- Start update room modal -->
                            <div class="modal fade" id="update-room-infor-modal" tabindex="-1"
                                 aria-labelledby="update-room-infor-modal-label" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="update-room-infor-modal-label">
                                                Cập nhật thông tin phòng
                                            </h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <!-- Form update room -->
                                        <form action="updateRoom" method="POST"
                                              class="custom-form update-room-infor-form">
                                            <div class="modal-body">
                                                <!-- Room number -->
                                                <div class="form-group">
                                                    <div class="row align-items-center">
                                                        <div class="col-6">
                                                            <label for="update-room-infor__room-number"
                                                                   class="form-label">
                                                                Số phòng
                                                            </label>
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="number" name="room-number"
                                                                   id="update-room-infor__room-number"
                                                                   value="${requestScope.roomInformation.roomNumber}"
                                                                   class="form-control m-0">
                                                        </div>
                                                    </div>
                                                    <span class="form-message"></span>
                                                </div>
                                                <!-- Room capacity -->
                                                <div class="form-group">
                                                    <div class="row align-items-center">
                                                        <div class="col-6">
                                                            <label for="update-room-infor__room-capacity"
                                                                   class="form-label">
                                                                Số lượng thành viên tối đa
                                                            </label>
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="number" name="room-capacity"
                                                                   id="update-room-infor__room-capacity"
                                                                   value="${requestScope.roomInformation.capacity}"
                                                                   class="form-control m-0">
                                                        </div>
                                                    </div>
                                                    <span class="form-message"></span>
                                                </div>
                                                <!-- Room area -->
                                                <div class="form-group">
                                                    <div class="row align-items-center">
                                                        <div class="col-6">
                                                            <label for="update-room-infor__room-area"
                                                                   class="form-label">
                                                                Diện tích
                                                            </label>
                                                        </div>
                                                        <div class="col-4">
                                                            <input type="number" name="room-area"
                                                                   id="update-room-infor__room-area"
                                                                   value="${requestScope.roomInformation.roomArea}"
                                                                   class="form-control m-0">
                                                        </div>
                                                        <div class="col-2 text-center">
                                                            m2
                                                        </div>
                                                    </div>
                                                    <span class="form-message"></span>
                                                </div>
                                                <!-- Room attic -->
                                                <div class="form-group">
                                                    <div class="row align-items-center">
                                                        <div class="col-6">
                                                            <label for="update-room-infor__room-attic"
                                                                   class="form-label">
                                                                Gác
                                                            </label>
                                                        </div>
                                                        <div class="col-4">
                                                            <select name="room-attic"
                                                                    id="update-room-infor__room-attic"
                                                                    class="form-control m-0">
                                                                <c:choose>
                                                                    <c:when test="${requestScope.roomInformation.hasAttic eq 1}">
                                                                        <option value="1" selected>Có</option>
                                                                        <option value="0">Không</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="1">Có</option>
                                                                        <option value="0" selected>Không</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer justify-content-between">
                                                <button type="button" class="btn btn-danger"
                                                        data-bs-dismiss="modal">Hủy bỏ
                                                </button>
                                                <input type="hidden" name="idRoom"
                                                       value="${requestScope.roomInformation.roomId}">
                                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End update room modal -->
                            <!-- End update room information button -->

                            <!-- Start calculate button button -->
                            <c:choose>
                                <c:when test="${requestScope.roomInformation.roomStatus eq 1 || requestScope.userNameRenterRoom eq null}">
                                    <a href="" class="action-create-account-link">Tạo tài khoản</a>
                                </c:when>
                                <c:when test="${requestScope.billRoom ne null}">
                                    <button class="action-calculate-btn">Tính tiền phòng</button>
                                </c:when>
                            </c:choose>
                            <!-- End calculate button button -->

                            <!-- Hide this button when room already rented -->
                            <!-- Start create account button -->

                            <!-- End create account button -->
                        </div>
                    </div>
                    <!-- Room information -->
                    <div class="room-information">
                        <div class="row">
                            <div class="col-12 col-md-7 room-information__left">
                                <div class="infor-group">Khu trọ trực thuộc:
                                    <span>${sessionScope.hostel.hostelName}</span></div>
                                <div class="infor-group">Địa chỉ:
                                    <span>${sessionScope.hostel.address}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
                                </div>
                                <div class="infor-group">Diện tích:
                                    <span>${requestScope.roomInformation.roomArea} m2</span></div>
                                <div class="infor-group">Gác: <span>
                                    <c:choose>
                                        <c:when test="${requestScope.roomInformation.hasAttic eq 1}">
                                            <c:out value="Có"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Không"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span></div>
                                <div class="infor-group">Trạng thái: <span>
                                    <c:choose>
                                        <c:when test="${requestScope.roomInformation.roomStatus eq 1}">
                                            <c:out value="Phòng sẵn sàng cho thuê"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Đã được thuê"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span></div>
                            </div>
                            <div class="col-12 col-md-5 room-information__right">
                                <div class="infor-group">Ngày bắt đầu hợp đồng:
                                    <span>${requestScope.contractRoom.startDate}</span></div>
                                <div class="infor-group">Ngày kết thúc hợp đồng:
                                    <span>${requestScope.contractRoom.expiration}</span></div>
                                <div class="infor-group">Tiền cọc: <span>${requestScope.contractRoom.deposit}</span>
                                </div>
                                <div class="infor-group">Tiền phòng: <span>${requestScope.contractRoom.price}</span>
                                </div>
                                <div class="infor-group">Số lượng thành viên:
                                    <span>${requestScope.quantityMember}/${requestScope.roomInformation.capacity}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="col-12 col-lg-5 col-xl-4">
                            <!-- Consume session -->
                            <div class="room-consume">
                                <div class="consume-header">Số điện/nước tiêu thụ</div>
                                <div class="consume-update-date">Ngày cập nhật gần nhất: <span
                                        class="date">${requestScope.consumeRoom.endConsumeDate}</span></div>
                                <div class="consume-group">
                                    <div class="consume-name">Điện:</div>
                                    <div class="consume-quantity">${requestScope.consumeRoom.numberElectric}</div>
                                </div>
                                <div class="consume-group">
                                    <div class="consume-name">Nước:</div>
                                    <div class="consume-quantity">${requestScope.consumeRoom.numberWater}</div>
                                </div>
                                <div class="consume-actions">
                                    <!-- Start consume update button -->
                                    <button class="consume-update-btn" data-bs-toggle="modal"
                                            data-bs-target="#update-consume-modal">Cập nhật
                                    </button>
                                    <!-- Start consume update modal -->
                                    <div class="modal fade" id="update-consume-modal" tabindex="-1"
                                         aria-labelledby="update-consume-modal-label" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="update-consume-modal-label">
                                                        Cập nhật số điện/nước tiêu thụ
                                                    </h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <form action="" method=""
                                                      class="custom-form update-consume-modal-form">
                                                    <div class="modal-body">
                                                        <!-- Label - Don't update -->
                                                        <div class="form-group">
                                                            <div class="row text-center">
                                                                <div class="col-2"></div>
                                                                <div class="col-5">
                                                                    <label class="form-label">Số cũ</label>
                                                                </div>
                                                                <div class="col-5">
                                                                    <label class="form-label">
                                                                        Số mới
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Input electric -->
                                                        <div class="form-group">
                                                            <div class="row align-items-center">
                                                                <div class="col-2 text-start">
                                                                    <label for="form-update-consume__electric"
                                                                           class="form-label">Điện</label>
                                                                </div>
                                                                <div class="col-5">
                                                                    <input type="number" value="2423" disabled
                                                                           class="form-control m-0">
                                                                </div>
                                                                <div class="col-5">
                                                                    <div class="form-group text-center">
                                                                        <input id="form-update-consume__electric"
                                                                               type="number" name="number-electric"
                                                                               value="" class="form-control m-0"
                                                                               placeholder="Nhập số điện mới">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <span class="form-message text-start mt-3 mb-3"></span>
                                                        </div>
                                                        <!-- Input water -->
                                                        <div class="form-group">
                                                            <div class="row align-items-center">
                                                                <div class="col-2 text-start">
                                                                    <label for="form-update-consume__water"
                                                                           class="form-label">Nước</label>
                                                                </div>
                                                                <div class="col-5">
                                                                    <input type="number" value="2423" disabled
                                                                           class="form-control m-0">
                                                                </div>
                                                                <div class="col-5">
                                                                    <div class="form-group text-center">
                                                                        <input id="form-update-consume__water"
                                                                               type="number" name="number-water"
                                                                               value="" class="form-control m-0"
                                                                               placeholder="Nhập số nước mới">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <span class="form-message text-start mt-3 mb-3"></span>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer justify-content-between">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Hủy bỏ
                                                        </button>
                                                        <button type="button" class="btn btn-danger">
                                                            Cập nhật
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End consume update modal -->
                                    <!-- End consume update button -->

                                    <!-- Start consume history list button -->
                                    <button class="consume-history-btn" data-bs-toggle="modal"
                                            data-bs-target="#history-update-consume-modal">
                                        Lịch sử cập nhật
                                    </button>
                                    <!-- Start consume history list modal -->
                                    <div class="modal fade" id="history-update-consume-modal" tabindex="-1"
                                         aria-labelledby="history-update-consume-modal-label" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="history-update-consume-modal-label">
                                                        Lịch sử cập nhật số điện/nước
                                                    </h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body text-center"
                                                     style="max-height: 70vh; overflow-y: auto;">
                                                    <!-- Label -->
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <div class="col-3">
                                                                <div class="form-label">Điện</div>
                                                            </div>
                                                            <div class="col-3">
                                                                <div class="form-label">Nước</div>
                                                            </div>
                                                            <div class="col-6">
                                                                <div class="form-label">Ngày cập nhật</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Items -->
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <div class="col-3">
                                                                <input type="text" value="12521"
                                                                       class="form-control" disabled>
                                                            </div>
                                                            <div class="col-3">
                                                                <input type="text" value="123213"
                                                                       class="form-control" disabled>
                                                            </div>
                                                            <div class="col-6">
                                                                <input type="text" value="20/10/2021"
                                                                       class="form-control" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <div class="col-3">
                                                                <input type="text" value="12521"
                                                                       class="form-control" disabled>
                                                            </div>
                                                            <div class="col-3">
                                                                <input type="text" value="123213"
                                                                       class="form-control" disabled>
                                                            </div>
                                                            <div class="col-6">
                                                                <input type="text" value="20/10/2021"
                                                                       class="form-control" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary"
                                                            data-bs-dismiss="modal">Xong
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Start consume history list modal -->
                                    <!-- End consume history list button -->
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-7 col-xl-8">
                            <!-- Invoice table -->
                            <div class="room-invoice">
                                <div class="invoice-header">
                                    <div class="invoice-title">Hóa đơn gần nhất</div>
                                    <div class="invoice-created-date">Ngày tạo:
                                        <span>${requestScope.billRoom.createdDate}</span></div>
                                </div>
                                <div class="invoice-body">
                                    <div class="invoice-group">
                                        <div class="invoice-label">Trạng thái:</div>
                                        <!-- Paid: success ~ Unpaid: fail -->
                                        <div class="invoice-content status success">
                                            <c:choose>
                                                <c:when test="${requestScope.billRoom.status eq 1}">Đã thanh toán</c:when>
                                                <c:when test="${requestScope.billRoom.status eq 0}">Chưa thanh toán</c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="invoice-group">
                                        <div class="invoice-label">Hình thức thanh toán:</div>
                                        <div class="invoice-content">
                                            ${requestScope.billRoom.payment.paymentName}
                                        </div>
                                    </div>
                                    <div class="invoice-group">
                                        <div class="invoice-label">Tổng tiền:</div>
                                        <c:choose>
                                            <c:when test="${requestScope.billRoom ne null}"><div class="invoice-content price">${requestScope.billRoom.totalMoney}
                                                <span>VNĐ</span></div></c:when>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="invoice-actions">
                                    <div class="left-actions">
                                        <button class="invoice-action-btn invoice-detail-btn">Chi tiết</button>
                                    </div>
                                    <div class="right-actions">
                                        <button class="invoice-action-btn list-invoices-btn">
                                            Danh sách hóa đơn
                                        </button>
                                        <!-- If this invoice has been paid, please hide this button -->
                                        <c:choose>
                                            <c:when test="${requestScope.billRoom.status eq 0 && requestScope.billRoom ne null}">
                                                <button class="invoice-action-btn invoice-confirm-paid-btn">Xác nhận đã
                                                    thanh toán
                                                </button>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- List members table -->
                    <div class="row">
                        <div class="col-12">
                            <div class="room-members">
                                <div class="members-header">
                                    <div class="members-title">Danh sách thành viên</div>
                                    <div class="members-actions">
                                        <c:choose>
                                            <c:when test="${requestScope.billRoom.status eq 1}">
                                                <button class="add-member-btn">Thêm thành viên</button>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <table id="members-table"
                                       class="mt-4 mb-4 table table-hover table-bordered table-striped members-table">
                                    <thead class="table-dark">
                                    <tr>
                                        <th class="mobile-display-none">STT</th>
                                        <th>Họ và tên</th>
                                        <th class="mobile-display-none">Giới tính</th>
                                        <th class="mobile-display-none">Địa chỉ</th>
                                        <th class="mobile-display-none">Số điện thoại</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-light">
                                    <c:set var="count" value="0"></c:set>
                                    <c:forEach var="roommateList" items="${requestScope.roommateInfo}">
                                        <tr>
                                            <c:set var="count" value="${count+1}"/>
                                            <td class="mobile-display-none">${count}</td>
                                            <td>${roommateList.information.fullname}</td>
                                            <td class="mobile-display-none">
                                                <c:choose>
                                                    <c:when test="${roommateList.information.sex eq 1}">Nam</c:when>
                                                    <c:otherwise>Nữ</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="mobile-display-none">${roommateList.information.address}</td>
                                            <td class="mobile-display-none">${roommateList.information.phone}</td>
                                            <td class="members-detail-actions">
                                                <!-- member detail link -->
                                                <div class="row">
                                                    <div class="col-12">
                                                        <button class="member-detail-btn">Chi tiết</button>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- Account table -->
                        <div class="col-12 col-md-5 col-xxl-4">
                            <div class="room-account mb-4 mb-md-0">
                                <div class="account-header">
                                    Tài khoản truy cập
                                </div>
                                <div class="account-username">Tên tài khoản:
                                    <span>${requestScope.userNameRenterRoom}</span></div>
                                <div class="account-action">
                                    <a href="" class="account-reset-password">Đặt lại mật khẩu</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-7 col-xxl-6">
                            <div class="infrastructure-section">
                                <div class="infrastructure-header">
                                    Cơ sở vật chất
                                </div>
                                <!-- Infrastructure title -->
                                <div class="infrastructure-title-group">
                                    <div class="infrastructure-title-name border-right">Tên</div>
                                    <div class="infrastructure-title-quantity border-right">Số lượng</div>
                                    <div class="infrastructure-title-unit border-right">Đơn vị</div>
                                    <div class="infrastructure-title-status">Trạng thái</div>
                                </div>
                                <!-- Toilet -->
                                <c:forEach var="infrastructures" items="${requestScope.infrastructures}">
                                    <div class="infrastructure-group">
                                        <div class="infrastructure-name border-right">${infrastructures.name}</div>
                                        <div class="infrastructure-quantity border-right">${infrastructures.quantity}</div>
                                        <div class="infrastructure-unit border-right">cái</div>
                                        <div class="infrastructure-status">
                                            <c:choose>
                                                <c:when test="${infrastructures.status eq 1}">
                                                    Còn sử dụng
                                                </c:when>
                                                <c:otherwise>
                                                    Hư hỏng
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="infrastructure-actions">
                                    <button class="infrastructure-btn update-btn">Cập nhật</button>
                                    <button class="infrastructure-btn add-btn">Thêm cở sở vật chất</button>
                                </div>
                            </div>
                        </div>
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

<!-- Waring modal -->
<div class="modal fade" id="updateServicesModel" tabindex="-1" aria-labelledby="updateServicesModelLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title updateServicesModel-label" id="updateServicesModelLabel">Cảnh báo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body updateServicesModel-content">
                Bạn nên gửi thông báo đến người thuê trước khi cập nhật giá dịch vụ mới đẻ giảm thiểu các trường hợp
                khiếu nại không mong muốn từ người thuê!
            </div>
            <div class="modal-footer updateServicesModel-action">
                <button type="button" class="btn btn-secondary back-btn" data-bs-dismiss="modal">Quay lại</button>
                <a href="" class="btn btn-primary continue-btn">Đã rõ và tiếp tục</a>
            </div>
        </div>
    </div>
</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
</body>

</html>