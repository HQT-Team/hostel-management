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

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-invite-code-style/style.css">

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">


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
                <div class="current">Mã tham gia</div>
            </div>
            <div class="row mb-5">
                <div class="content-body col-12 col-lg-12 col-xxl-10 m-auto">
                    <div class="invite-header">
                        <div class="invite-countdown">
                            Thời gian hiệu lực còn lại <span id="count-down">00:00</span> phút <br>
                            Trạng thái: <span id="invite-status" class="invite-countdown-success">Còn hiệu
                                    lực</span>
                        </div>
                        <div id="invite-recreate" class="invite-recreate d-none">
                            <form action="createInvite" method="post">
                                <input type="hidden" name="room_id" value="${sessionScope.room.roomId}">
                                <button class="invite-recreate-btn" type="submit">Tạo mới mã tham gia</button>
                            </form>
                        </div>
                    </div>
                    <div class="invite-group">
                        <div class="row">
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">QR code</div>
                                <div id="invite-content__image" class="invite-content">
                                    <!-- Image here -->
                                </div>
                                <div class="invite-note">Đưa mã này cho người thuê để họ quét và gia nhập hệ thống
                                    ngay lập tức</div>
                            </div>
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">Mã tham gia</div>
                                <div id="invite-content__code"
                                     class="invite-content code-fragment invite-content__code">
                                    <code id="invite-code" class="invite-code">
                                        ${requestScope.ROOM_INVITE.inviteCode}
                                    </code>
                                    <button id="invite-copy__code" class="invite-copy invite-copy__code">Sao
                                        chép</button>
                                </div>
                                <div class="invite-note">Đưa mã này cho người thuê để họ nhập khi đăng
                                    ký tài khoản
                                    trở thành thành viên của ứng dụng</div>
                            </div>
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">Liên kết trực tiếp</div>
                                <div id="invite-content__link"
                                     class="invite-content code-fragment invite-content__link">
                                    <code id="invite-link" class="invite-url">
                                        ${requestScope.URL_INVITE}
                                    </code>
                                    <button id="invite-copy__link" class="invite-copy invite-copy__link">Sao
                                        chép</button>
                                </div>
                                <div class="invite-note">
                                    Đưa liên kết này cho người thuê để nhanh chóng tham gia ứng dụng
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="invite-notices">* Chú ý:</div>
                    <ul class="invite-notices-list">
                        <li class="invite-notices-items">
                            Khi hết thời gian hiệu lực thì QR Code, mã tham gia và liên kết sẽ không thể truy cập
                        </li>
                        <li class="invite-notices-items">
                            Trong một ngày chỉ có thể tạo tối đa 3 lần mã tham gia
                        </li>
                    </ul>
                    <a href="roomDetail?roomID=${sessionScope.room.roomId}" class="invite-action"><i class="fa-solid fa-arrow-left"></i> Quay về</a>
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
<script src="./assets/js/owner/room-invite-code/handle-copy.js"></script>
<script src="./assets/js/owner/room-invite-code/handle-countdown.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>

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
    // Handle encode and render QR image
    const imgBase64Code ='${requestScope.ROOM_INVITE.QRCode}';
    const image = new Image();
    image.src = 'data:image/png;base64,'+imgBase64Code;
    image.classList.add("invite-qr-img");
    $('#invite-content__image').append(image);

    // Handle Countdown time
    handleCountDown({
        targetDate: "${requestScope.ROOM_INVITE.expiredTimeCode}"
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
