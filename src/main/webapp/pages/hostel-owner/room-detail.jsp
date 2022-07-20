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

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">


</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <div id="preloader">
        <div class="dots">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</c:if>

<!-- Navbar -->
<%@include file="components/navbar.jsp" %>

<!-- Body -->
<div class="container">
    <div class="row position-relative">
        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="components/sidebar.jsp" %>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
            <div class="content-bar">
                <!-- History link bar -->
                <div class="content-history">
                    <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <a href="detailHostel?hostelID=${sessionScope.hostel.hostelID}"
                       class="history-link">${sessionScope.hostel.hostelName}</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <div class="current">Phòng ${sessionScope.room.roomNumber}</div>
                </div>
            </div>
            <!-- Info box -->
            <div class="col-xxl-9 m-auto">
                <div class="content-body">
                    <!-- Room header -->
                    <%@include file="./room-detail-components/room-header.jsp" %>
                    <!-- Room information -->
                    <div class="room-information">
                        <%@include file="./room-detail-components/room-information.jsp" %>
                    </div>
                    <div class="row mb-4">
                        <div class="col-12 col-lg-5 col-xl-4">
                            <!-- Consume session -->
                            <%@include file="./room-detail-components/room-consume.jsp" %>
                        </div>
                        <div class="col-12 col-lg-7 col-xl-8">
                            <!-- Invoice session -->
                            <%@include file="./room-detail-components/room-invoice.jsp" %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <!-- Member session -->
                            <%@include file="./room-detail-components/room-member.jsp" %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-5 col-xxl-4">
                            <!-- Account session -->
                            <%@include file="./room-detail-components/room-account.jsp" %>
                        </div>
                        <div class="col-12 col-md-7 col-xxl-6">
                            <!-- Infrastructure session -->
                            <%@include file="./room-detail-components/room-infrastructure.jsp" %>
                        </div>
                    </div>
                    <c:if test="${sessionScope.room.roomStatus == 0}">
                        <div class="row">
                            <div class="col-12 col-md4">
                                <button class="btn btn-outline-dark" data-bs-toggle="modal"
                                        data-bs-target="#change-room-status-modal"
                                        style="font-size: 1.6rem; font-weight: 600; padding: 8px 12px;">
                                    Kết thúc cho thuê phòng
                                </button>
                                <!-- Modal -->
                                <div class="modal fade" id="change-room-status-modal" tabindex="-1"
                                     aria-labelledby="change-room-status-modalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title text-warning" id="change-room-status-modalLabel">
                                                    Cảnh báo
                                                </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body mt-5 mb-5"
                                                 style="font-size: 1.8rem; line-height: 2.8rem;">
                                                Phòng đang có người thuê, bạn có chắc chắn là muốn kết thúc hợp đồng và cập nhật trạng thái về
                                                "<span style="font-weight: 600;">Sẵn sàng cho thuê</span>" hay không?
                                            </div>
                                            <div class="modal-footer justify-content-between">
                                                <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Hủy bỏ
                                                </button>
                                                <form action="end-rental-contract" method="POST">
                                                    <input type="hidden" name="room-id" value="${sessionScope.room.roomId}" />
                                                    <input type="hidden" name="renter-account-id" value="${requestScope.renterAccount.accId}" />
                                                    <button type="submit" class="btn btn-danger">Đồng ý</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.room.roomStatus == -1}">
                        <div class="row">
                            <div class="col-12 col-md4">
                                <button class="btn btn-outline-dark" data-bs-toggle="modal"
                                        data-bs-target="#change-room-status-modal"
                                        style="font-size: 1.6rem; font-weight: 600; padding: 8px 12px;">
                                    Hủy thao tác tạo hợp đồng cho thuê
                                </button>
                                <!-- Modal -->
                                <div class="modal fade" id="change-room-status-modal" tabindex="-1"
                                     aria-labelledby="change-room-status-modalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title text-warning" id="change-room-status-modalLabel">
                                                    Cảnh báo
                                                </h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body mt-5 mb-5"
                                                 style="font-size: 1.8rem; line-height: 2.8rem;">
                                                Phòng này đang được tạo hợp đồng cho thuê, bạn có chắc là muốn cập nhật trạng thái về
                                                "<span style="font-weight: 600;">Sẵn sàng cho thuê</span>" không?
                                            </div>
                                            <div class="modal-footer justify-content-between">
                                                <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Hủy bỏ
                                                </button>
                                                <form action="end-rental-contract" method="POST">
                                                    <input type="hidden" name="room-id" value="${sessionScope.room.roomId}" />
                                                    <input type="hidden" name="renter-account-id" value="${requestScope.renterAccount.accId}" />
                                                    <button type="submit" class="btn btn-danger">Đồng ý</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="components/footer.jsp" %>

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
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<script src="./assets/js/valid-form.js"></script>
<script src="./assets/js/owner/room-detail/validate-input.js"></script>
<script src="./assets/js/toast-alert.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/sendWebsocket.js"></script>
<script src="./assets/js/receiveWebsocket.js"></script>

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

<script type="text/javascript">
    // Send
    <c:if test="${requestScope.CREATE_BILL_MSG.status == true}">
    const params = new Object();
    params.sender = "hostel_owner";
    params.receiver = "hostel_renter";
    params.hostel_receiver_id = null;
    params.account_receiver_id = "${requestScope.RENTER_ID}";
    params.messages = "Chủ trọ đã gửi một hóa đơn mới. Vui lòng kiểm tra!";
    sendToWebSocket(params);
    </c:if>

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