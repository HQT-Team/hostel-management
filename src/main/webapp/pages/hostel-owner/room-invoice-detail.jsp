<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>Danh sách hóa đơn</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-invoice-detail_style/style.css">

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
<%@include file="./components/navbar.jsp" %>

<!-- Body -->
<div class="container">
    <div class="row position-relative">
        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp" %>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
            <!-- History link bar -->
            <div class="content-history">
                <c:choose>
                    <c:when test="${sessionScope.CURRENT_PAGE eq 'invoice'}">
                        <a href="getInvoiceList" class="history-link">Danh sách hóa đơn</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <div class="current">#B${requestScope.billRoom.billID}</div>
                    </c:when>
                    <c:otherwise>
                        <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <a href="detailHostel?hostelID=${sessionScope.hostel.hostelID}"
                           class="history-link">${sessionScope.hostel.hostelName}</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <a href="roomDetail?roomID=${sessionScope.room.roomId}"
                           class="history-link">Phòng ${sessionScope.room.roomNumber}</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <a href="roomInvoiceList?roomId=${sessionScope.room.roomId}" class="history-link">Danh sách hóa đơn</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <div class="current">#B${requestScope.billRoom.billID}</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9 m-auto">
                <div class="content__body mb-5">
                    <div class="bill">
                        <h1 class="bill__title">Hóa đơn tháng ${requestScope.billRoom.billTitle}</h1>
                        <div class="row">
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Khu trọ: <span>${sessionScope.hostel.hostelName}</span></p>
                                <p class="bill__item">Phòng số: <span>${sessionScope.room.roomNumber}</span></p>
                                <p class="bill__item">Địa chỉ:
                                    <span>${sessionScope.hostel.address}, ${sessionScope.hostel.ward.split('-')[1]}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
                                </p>
                                <c:set var="consumeBeginMonth" value="${requestScope.consumeStart}"/>
                                <c:set var="consumeEndMonth" value="${requestScope.consumeEnd}"/>
                                <div class="bill__consume">
                                    <c:set var="consumeNumberElectric"
                                           value="${consumeEndMonth.numberElectric - consumeBeginMonth.numberElectric}"/>
                                    <div class="bill__consume-name">Điện</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>${consumeBeginMonth.numberElectric}</span>, Số mới:
                                        <span>${consumeEndMonth.numberElectric}</span>, Tiêu thụ:
                                        <span>${consumeNumberElectric}</span>
                                    </div>
                                </div>
                                <div class="bill__consume">
                                    <c:set var="consumeNumberWater"
                                           value="${consumeEndMonth.numberWater - consumeBeginMonth.numberWater}"/>
                                    <div class="bill__consume-name">Nước</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>${consumeBeginMonth.numberWater}</span>, Số mới:
                                        <span>${consumeEndMonth.numberWater}</span>, Tiêu thụ:
                                        <span>${consumeNumberWater}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Ngày tạo hóa đơn: <span>
                                    <fmt:parseDate pattern="yyyy-MM-dd"
                                                   value="${requestScope.billRoom.createdDate}"
                                                   var="createdDate"/>
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${createdDate}"/>
                                </span></p>
                                <p class="bill__item">Hạn chót thanh toán: <span>
                                    <fmt:parseDate pattern="yyyy-MM-dd"
                                                   value="${requestScope.billRoom.expiredPaymentDate}"
                                                   var="expiredPaymentDate"/>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${expiredPaymentDate}"/>
                                </span></p>

                                <c:choose>
                                    <c:when test="${requestScope.billRoom.status eq 1}">
                                        <p class="bill__item">Ngày thanh toán: <span>
                                            <fmt:parseDate pattern="yyyy-MM-dd"
                                                           value="${requestScope.billRoom.paymentDate == null ? '2022/10/10' : requestScope.billRoom.paymentDate}"
                                                           var="paymentDate"/>
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${paymentDate}"/>
                                        </span></p>
                                        <p class="bill__item">
                                            Phương thức thanh toán:
                                            <span>${requestScope.paymentName == null ? "Trống" : requestScope.paymentName}</span>
                                        </p>
                                    </c:when>
                                </c:choose>
                                <p class="bill__item">Trạng thái:
                                    <c:choose>
                                        <c:when test="${requestScope.billRoom.status == 0}">
                                            <span class="status--no">Chưa thanh toán</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status--yes">Đã thanh toán</span>
                                        </c:otherwise>
                                    </c:choose>
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
                                <c:set var="count" value="0"/>
                                <c:set var="totalCost" value="0"/>
                                <c:forEach var="service" items="${requestScope.serviceInfo}">
                                    <c:set var="count" value="${count+1}"/>
                                    <c:set var="quantity" value="1"/>
                                    <tr>
                                        <td>${count}</td>
                                        <td>${service.serviceName}</td>
                                        <td>${service.unit}</td>
                                        <c:choose>
                                            <c:when test="${service.serviceName eq 'Điện'}">
                                                <c:set var="quantity" value="${consumeNumberElectric}"/>
                                                <td>${consumeNumberElectric}</td>
                                            </c:when>
                                            <c:when test="${service.serviceName eq 'Nước'}">
                                                <c:set var="quantity" value="${consumeNumberWater}"/>
                                                <td>${consumeNumberWater}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="quantity" value="1"/>
                                                <td>1</td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td>
                                            <fmt:formatNumber value="${service.servicePrice}" type="currency"
                                                              currencySymbol="VNĐ"/>
                                        </td>

                                        <c:set var="totalCost"
                                               value="${totalCost + service.servicePrice * quantity}"/>
                                        <td><fmt:formatNumber value="${service.servicePrice * quantity}"
                                                              type="currency" currencySymbol="VNĐ"/></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>${count+1}</td>
                                    <td>Tiền phòng</td>
                                    <td>phòng</td>
                                    <td>1</td>
                                    <td>
                                        <fmt:formatNumber value="${requestScope.contractRoom.price}" type="currency"
                                                          currencySymbol="VNĐ"/>
                                    </td>
                                    <c:set var="totalCost"
                                           value="${totalCost + requestScope.contractRoom.price}"/>
                                    <td><fmt:formatNumber value="${requestScope.contractRoom.price}"
                                                          type="currency" currencySymbol="VNĐ"/></td>
                                </tr>
                                <!-- Total -->
                                <td colspan="5" class="text-end total">Tổng
                                    tiền:
                                </td>
                                <td><fmt:formatNumber value="${requestScope.billRoom.totalMoney}" type="currency"
                                                      currencySymbol="VNĐ"/></td>
                                </tbody>
                            </table>
                        </div>
                        <div class="bill__sign">
                            <div class="row">
                                <div class="col-6">
                                    <div class="bill__sign-label">Người lập hóa đơn</div>
                                    <div class="bill__sign-name">${requestScope.billMakerFullName}</div>
                                </div>
                                <div class="col-6">
                                    <div class="bill__sign-label">Người thanh toán</div>
                                    <div class="bill__sign-name">${requestScope.billPaymenterFullName}</div>
                                </div>
                            </div>
                        </div>
                        <div class="bill__spacer"></div>
                        <div class="bill__action">
                            <c:choose>
                                <c:when test="${sessionScope.CURRENT_PAGE eq 'invoice'}">
                                    <a href="getInvoiceList" class="bill__action-link">
                                        <i class="fa-solid fa-circle-arrow-left"></i> Quay lại
                                    </a>
                                </c:when>
                                <c:when test="${sessionScope.CURRENT_PAGE eq 'room'}">
                                    <a href="roomInvoiceList?roomId=${sessionScope.room.roomId}"
                                       class="bill__action-link">
                                        <i class="fa-solid fa-circle-arrow-left"></i> Quay lại
                                    </a>
                                </c:when>
                            </c:choose>
                            <a class="btn btn-secondary fs-4" href="export-excel?billID=${requestScope.billRoom.billID}&hostelID=${sessionScope.hostel.hostelID}&roomID=${sessionScope.room.roomId}">
                                Xuất Excel
                            </a>
                            <form action="updateBilLStatus" method="post" class="bill__form d-flex justify-content-end">
                                <c:choose>
                                    <c:when test="${requestScope.billRoom.status eq 0}">
                                        <input type="hidden" name="billID" value="${requestScope.billRoom.billID}">
                                        <input type="hidden" name="navigateTo"
                                               value="roomInvoiceList?roomId=${sessionScope.room.roomId}">
                                        <button type="submit" class="bill__action-btn btn btn-outline-primary">
                                            Xác nhận đã thanh toán
                                        </button>
                                    </c:when>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp" %>

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
