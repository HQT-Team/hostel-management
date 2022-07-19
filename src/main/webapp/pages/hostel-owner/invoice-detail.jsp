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
    <title>Chi tiết hóa đơn</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/invoice-detail_style/style.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

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
                <a href="./invoices.html" class="history-link">Danh sách hóa đơn chưa thanh toán</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Hóa đơn #VA123</div>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9 m-auto">
                <div class="content__body">
                    <div class="bill">
                        <h1 class="bill__title">Hóa đơn tháng 05/2022</h1>
                        <div class="row">
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Khu trọ: <span>Nova land</span></p>
                                <p class="bill__item">Phòng số: <span>11</span></p>
                                <p class="bill__item">Địa chỉ: <span>999 Hoàng Hữu Nam, phường Long Thạnh Mỹ, thành
                                            phố Thủ Đức, thành phố Hồ Chí Minh</span></p>
                                <div class="bill__consume">
                                    <div class="bill__consume-name">Điện</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>20</span>, Số mới: <span>40</span>, Tiêu thụ: <span>20</span>
                                    </div>
                                </div>
                                <div class="bill__consume">
                                    <div class="bill__consume-name">Nước</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>20</span>, Số mới: <span>40</span>, Tiêu thụ: <span>20</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Ngày tạo hóa đơn: <span>22/02/2022</span></p>
                                <p class="bill__item">Hạn chót thanh toán: <span>Trống</span></p>
                                <p class="bill__item">Ngày thanh toán: <span>Trống</span></p>
                                <p class="bill__item">Trạng thái: <span class="status--no">Chưa thanh toán</span>
                                </p>
                            </div>
                        </div>
                        <div class="bill__table">
                            <table class="table table-secondary table-striped table-bordered">
                                <thead>
                                <tr class="text-center">
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Đơn vị tính</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Điện</td>
                                    <td>1 Kwh</td>
                                    <td>20</td>
                                    <td>3.500 đ</td>
                                    <td>70.000 đ</td>
                                </tr>

                                <!-- Total money -->
                                <tr>
                                    <td colspan="5" class="total">Tổng tiền</td>
                                    <td class="total-money">70.000 đ</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="bill__sign">
                            <div class="row">
                                <div class="col-6">
                                    <div class="bill__sign-label">Người lập hóa đơn</div>
                                    <div class="bill__sign-name">Nguyễn Văn A</div>
                                </div>
                                <div class="col-6">
                                    <div class="bill__sign-label">Người thanh toán</div>
                                    <div class="bill__sign-name">Trống</div>
                                </div>
                            </div>
                        </div>
                        <div class="bill__spacer"></div>
                        <div class="bill__action">
                            <a href="./invoices.html" class="bill__action-link">
                                <i class="fa-solid fa-circle-arrow-left"></i> Quay lại
                            </a>
                            <form action="" method="POST" class="bill__form d-flex justify-content-end">
                                <input type="hidden" name="roomID" value="" />
                                <!-- Change type="submit" of tag button below when implement -->
                                <button class="bill__action-btn btn btn-outline-primary" type="button">
                                    Xác nhận đã thanh toán
                                </button>
                            </form>
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


<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Link your script here -->
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
<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
