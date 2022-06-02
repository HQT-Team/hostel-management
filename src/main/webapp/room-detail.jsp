<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/images/favicon.png" type="image/x-icon" />
    <title>Phòng</title>
    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Base CSS !important -->
    <link rel="stylesheet" href="assets/css/style.css">
    <!-- Link your CSS here -->
    <link rel="stylesheet" href="assets/css/hostel_owner_style/room_detail_style/style.css">
    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

</head>

<body>
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
                <%@include file="./Component/hostel-owner/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
                <div class="content-bar pt-5">
                    <!-- History link bar -->
                    <div class="content-history">
                        <a href="Layout/hostel-owner-layout/hostel.html" class="history-link">Danh sách khu trọ</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <a href="Layout/hostel-owner-layout/hostel-detail.html" class="history-link">NovaLand Sky</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <div class="current">Phòng 11</div>
                    </div>
                </div>
                <!-- Infor box -->
                <div class="col-xxl-11 m-auto">
                    <div class="content-body">
                        <div class="room-header">
                            <h2 class="room-name">Phòng 11</h2>
                            <!-- Add room button -->
                            <div class="room-actions">
                                <button class="action-calculate-btn">Tính tiền phòng</button>
                                <a href="" class="action-create-account-link">Tạo tài khoản</a>
                            </div>
                        </div>
                        <!-- Room information -->
                        <div class="room-information">
                            <div class="row">
                                <div class="col-12 col-md-7 room-information__left">
                                    <div class="infor-group">Khu trọ trực thuộc: <span>NovaLand Sky</span></div>
                                    <div class="infor-group">Địa chỉ: <span>256 Lê Văn Việt, TP. Thủ Đức, TP. Hồ Chí
                                            Minh</span></div>
                                    <div class="infor-group">Số lượng thành viên <span>3/4</span></div>
                                </div>
                                <div class="col-12 col-md-5 room-information__right">
                                    <div class="infor-group">Ngày bắt đầu hợp đồng: <span>19/12/2021</span></div>
                                    <div class="infor-group">Ngày kết thúc hợp đồng: <span>31/12/2022</span></div>
                                    <div class="infor-group">Tiền cọc: <span>2,500,000 vnđ</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <div class="col-12 col-lg-5 col-xl-4">
                                <!-- Consume table -->
                                <div class="room-consume">
                                    <div class="consume-header">Số điện/nước tiêu thụ</div>
                                    <div class="consume-update-date">Ngày cập nhật gần nhất: <span
                                            class="date">30/04/2022
                                            <span class="count-date">(21 ngày
                                                trước)</span></span></div>
                                    <div class="consume-group">
                                        <div class="consume-name">Điện:</div>
                                        <div class="consume-quantity">3290</div>
                                    </div>
                                    <div class="consume-group">
                                        <div class="consume-name">Nước:</div>
                                        <div class="consume-quantity">3290</div>
                                    </div>
                                    <div class="consume-actions">
                                        <button class="consume-history-btn">Lịch sử cập nhật</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-7 col-xl-8">
                                <!-- Invoice table -->
                                <div class="room-invoice">
                                    <div class="invoice-header">
                                        <div class="invoice-title">Hóa đơn gần nhất</div>
                                        <div class="invoice-created-date">Ngày tạo: <span>30/04/2022</span></div>
                                    </div>
                                    <div class="invoice-body">
                                        <div class="invoice-group">
                                            <div class="invoice-label">Trạng thái: </div>
                                            <!-- Paid: success ~ Unpaid: fail -->
                                            <div class="invoice-content status success">Đã thanh toán</div>
                                        </div>
                                        <div class="invoice-group">
                                            <div class="invoice-label">Hình thức thanh toán: </div>
                                            <div class="invoice-content">Tiền mặt</div>
                                        </div>
                                        <div class="invoice-group">
                                            <div class="invoice-label">Tổng tiền: </div>
                                            <div class="invoice-content price">2,230,000 <span>vnđ</span></div>
                                        </div>
                                    </div>
                                    <div class="invoice-actions">
                                        <div class="left-actions">
                                            <button class="invoice-action-btn invoice-detail-btn">Chi tiết</button>
                                        </div>
                                        <div class="right-actions">
                                            <button class="invoice-action-btn list-invoices-btn">Danh sách hóa
                                                đơn</button>
                                            <!-- If this invoice has been paid, please hide this button -->
                                            <button class="invoice-action-btn invoice-confirm-paid-btn">Xác nhận đã
                                                thanh
                                                toán</button>
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
                                            <button class="add-member-btn">Thêm thành viên</button>
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
                                            <tr>
                                                <td class="mobile-display-none">1</td>
                                                <td>Hoàng Đăng Khoa</td>
                                                <td class="mobile-display-none">Nam</td>
                                                <td class="mobile-display-none">999 Nguyễn Văn Linh, Quận 7, TP. Hồ Chí
                                                    Minh</td>
                                                <td class="mobile-display-none">05123575923</td>
                                                <td class="members-detail-actions">
                                                    <!-- member detail link -->
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <button class="member-detail-btn">Chi tiết</button>
                                                        </div>
                                                        <div class="col-12">
                                                            <button class="member-update-btn">Cập nhật</button>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- Account table -->
                        <div class="row">
                            <div class="col-8 col-md-5 col-xxl-4">
                                <div class="room-account">
                                    <div class="account-header">
                                        Tài khoản truy cập
                                    </div>
                                    <div class="account-username">Tên tài khoản: <span>Ahihi</span></div>
                                    <div class="account-action">
                                        <a href="" class="account-reset-password">Đặt lại mật khẩu</a>
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
                        <div class="copyright-logo">
                            <img src="assets/images/hql_logo_white_notext.svg" alt="Logo">
                        </div>
                        <div class="copyright-content">© 2022 HQT-Hostel. All rights reserved.</div>
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
    <script src="assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <!-- Link your script here -->
    <script src="assets/js/handle-main-navbar.js"></script>
    <!-- Simple Datatable JS -->
    <script src="assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            // Initial datatable
            $('#members-table').DataTable();
        });
    </script>
</body>

</html>