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
    <title>Thêm khu trọ</title>

    <!-- Date picker -->
    <link rel="stylesheet" href="./assets/scss/datepicker/rome.css">

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-create-account-style/style.css">

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

    <!-- Datepicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css">

</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">
<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

<!-- Preload -->
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
<div class="container min-height">
    <div class="row position-relative">

        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp"%>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
            <div class="content-history">
                <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a href="detailHostel?hostelID=${sessionScope.hostel.hostelID}" class="history-link">${sessionScope.hostel.hostelName}</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a href="roomDetail?roomID=${sessionScope.room.roomId}" class="history-link">Phòng ${sessionScope.room.roomNumber}</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Tạo tài khoản</div>
            </div>
            <div class="row mb-5">
                <div class="content-body col-12 col-md-10 col-lg-9 col-xl-7 col-xxl-6 m-auto">
                    <form action="createRenter" method="post" class="custom-form create-room-account-form"
                          id="create-room-account-form">
                        <input type="hidden" name="room_id" value="${sessionScope.room.roomId}">
                        <div class="form-header">
                            <div class="form-title main-title">Tạo tài khoản</div>
                        </div>
                        <div class="spacer"></div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-username" class="form-label">Tên tài khoản:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-username" name="room-username" type="text" value="${requestScope.username}"
                                           class="form-control m-0" placeholder="Nhập tên tài khoản truy cập phòng">
                                </div>
                            </div>
                            <span class="form-message mt-4">
                                ${requestScope.RESPONSE_MSG.status eq false && requestScope.errorType eq "username" ? requestScope.RESPONSE_MSG.content : ""}
                            </span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-email" class="form-label">Email:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-email" name="room-email" type="text" value="${requestScope.email}"
                                           class="form-control m-0" placeholder="Nhập email của tài khoản">
                                </div>
                            </div>
                            <span class="form-message mt-4">
                                ${requestScope.RESPONSE_MSG.status eq false && requestScope.errorType eq "email" ? requestScope.RESPONSE_MSG.content : ""}
                            </span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="input_from" class="form-label">Ngày bắt đầu hợp đồng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input type="text" class="form-control m-0" id="input_from" placeholder="Ngày bắt đầu"
                                           name="room-startdate" value="${requestScope.startDate}">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="input_to" class="form-label">Ngày kết thúc hợp đồng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input type="text" class="form-control m-0" id="input_to" placeholder="Ngày kết thúc"
                                           name="room-enddate" value="${requestScope.endDate}">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-electric" class="form-label">Số điện hiện tại:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-electric" name="room-electric" type="number" value="${requestScope.roomElectric}"
                                           class="form-control m-0" placeholder="Nhập số điện mới nhất của phòng">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-water" class="form-label">Số nước hiện tại:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-water" name="room-water" type="number" value="${requestScope.roomWater}"
                                           class="form-control m-0" placeholder="Nhập số nước mới nhất của phòng">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-fee" class="form-label">Tiền phòng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-fee" name="room-fee" type="number" class="form-control m-0"
                                           placeholder="Nhập số tiền phòng" value="${requestScope.price}">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-deposit" class="form-label">Tiền cọc:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-deposit" name="room-deposit" type="number" value="${requestScope.deposit}"
                                           class="form-control m-0" placeholder="Nhập số tiền cọc cho phòng">
                                </div>
                            </div>
                            <span class="form-message mt-4"></span>
                        </div>
                        <div class="spacer"></div>
                        <div class="create-room-account-actions">
                            <a href="roomDetail?roomID=${sessionScope.room.roomId}" class="form-submit">Hủy bỏ</a>
                            <button type="submit" class="form-submit">Xác nhận và tạo mã tham gia</button>
                        </div>
                    </form>
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

<!-- Script Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- Date picker -->
<script src="./assets/js/datepicker/popper.min.js"></script>
<script src="./assets/js/datepicker/rome.js"></script>

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

<script>
    $(function () {
        rome(input_from, {
            dateValidator: rome.val.beforeEq(input_to),
            time: false,
        });

        rome(input_to, {
            dateValidator: rome.val.afterEq(input_from),
            time: false,
        });
    });

</script>

<script>
    Validator({
        form: '#create-room-account-form',
        formGroupSelector: '.form-group',
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired('#room-username', 'Vui lòng nhập trường này'),
            Validator.isUsername('#room-username'),
            Validator.maxLength('#room-username', 64, 'Tên tài khoản dài tối đa 64 kí tự'),
            Validator.isRequired('#room-email', 'Vui lòng nhập trường này'),
            Validator.isEmail('#room-email', 'Vui lòng nhập đúng định dạng email'),
            Validator.isRequired('#room-electric', 'Vui lòng nhập trường này'),
            Validator.minNumber('#room-electric', 0, 'Vui lòng nhập tối thiểu 0'),
            Validator.isRequired('#room-water', 'Vui lòng nhập trường này'),
            Validator.minNumber('#room-water', 0, 'Vui lòng nhập tối thiểu 0'),
            Validator.isRequired('#room-fee', 'Vui lòng nhập trường này'),
            Validator.minNumber('#room-fee', 1, 'Vui lòng nhập tối thiểu 1'),
            Validator.maxNumber('#room-fee', 100000000, 'Vui lòng nhập tối đa 100000000'),
            Validator.isRequired('#room-deposit', 'Vui lòng nhập trường này'),
            Validator.minNumber('#room-deposit', 1, 'Vui lòng nhập tối thiểu 1'),
            Validator.maxNumber('#room-deposit', 100000000, 'Vui lòng nhập tối đa 100000000'),
            Validator.isRequired('#input_from', 'Vui lòng nhập trường này'),
            Validator.isRequired('#input_to', 'Vui lòng nhập trường này'),
            Validator.isGreaterDate('#input_to', function () {
                return document.querySelector('#input_from').value;
            })
        ]
    });
</script>

<script type="text/javascript">
    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function(){
        receiveWebsocket.disconnectWebSocket();
    };
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>

</body>

</html>
