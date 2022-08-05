<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %><%--
  Created by IntelliJ IDEA.
  User: 84337
  Date: 6/18/2022
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-payment.css">
    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body>
<%
    Account account = (Account) session.getAttribute("USER");
%>
<div>

    <div class="row" style="padding: 0;margin: 0;">

        <%@include file="components/sidebar.jsp"%>
        <div class="content">
    <%@include file="components/navbar.jsp"%>
            <div class="hidden_notification" id="notifications">
                <p>${requestScope.RESPONSE_MSG.content}</p>
                <span class="progress"></span>
            </div>
            <h1>Hóa Đơn</h1>
            <input id="key" type="hidden" value="<%=request.getAttribute("RESPONSE_MSG")%>"/>
            <div id="invoice-cover">
                <fmt:parseDate pattern="yyyy-MM-dd" value="${BILL.createdDate}" var="createdDate"/>
                <fmt:parseDate pattern="yyyy-MM-dd" value="${BILL.expiredPaymentDate}" var="expiredPaymentDate"/>

                <h2>#B${BILL.billID}</h2>
                <h3><a>
                    <c:if test="${BILL.status == 1}">
                        <p class="payment-status" style="color: green">Đã thanh toán</p>
                    </c:if>
                    <c:if test="${BILL.status != 1}">
                        <p class="payment-status" style="color: red">Chưa thanh toán</p>
                    </c:if>
                </a></h3>
                <p></p>
                <p><strong>Phòng số: </strong>${requestScope.RoomInfor.roomNumber}</p>
                <c:set var="consumeBeginMonth" value="${requestScope.CONSUME_START}"/>
                <c:set var="consumeEndMonth" value="${requestScope.CONSUME_END}"/>
                <div id="water">
                    <c:set var="numberElectric"
                           value="${consumeEndMonth.numberElectric - consumeBeginMonth.numberElectric}"/>
                    <c:set var="numberWater" value="${consumeEndMonth.numberWater - consumeBeginMonth.numberWater}"/>

                    <p><strong>Số nước: </strong>${numberWater}</p>
                    <p><strong>Cũ: </strong>${consumeBeginMonth.numberWater}</p>
                    <p><strong>Mới: </strong>${consumeEndMonth.numberWater}</p>
                </div>
                <div id="electric">
                    <p><strong>Số điện: </strong>${numberElectric}</p>
                    <p><strong>Cũ: </strong>${consumeBeginMonth.numberElectric}</p>
                    <p><strong>Mới: </strong>${consumeEndMonth.numberElectric}</p>
                </div>
                <p><strong>Ngày tạo hóa đơn: </strong><fmt:formatDate value="${createdDate}" type="Date"
                                                                      pattern="dd-MM-yyyy"/></p>
                <p><strong>Hạn thanh toán: </strong><fmt:formatDate value="${expiredPaymentDate}" type="Date"
                                                                    pattern="dd-MM-yyyy"/></p>
                <p><strong>Tổng: </strong><fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${BILL.totalMoney}"/> vnđ</p>

                <div id="action1">
                    <form id="action0" action="renter-invoice">
                        <button type="submit">Quay Lại</button>
                    </form>
                    <form id="action" action="vnp-payment" method="post">
                        <input type="hidden" name="vnp_OrderId" value="${BILL.billID}">
                        <button type="submit" id="payment-button">Thanh Toán</button>
                    </form>
                </div>
            </div>
            <div id="table">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên</th>
                        <th>Đơn vị</th>
                        <th>Số lượng</th>
                        <th>Giá(vnđ)</th>
                        <th>Tổng(vnđ)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach var="s" items="${LIST_SERVICES}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td>${count}</td>
                            <td>${s.serviceName}</td>
                            <td>${s.unit}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${s.serviceName=='Điện'}">
                                        ${numberElectric}
                                    </c:when>
                                    <c:when test="${s.serviceName=='Nước'}">
                                        ${numberWater}
                                    </c:when>
                                    <c:otherwise>1</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:setLocale value="vi_VN"/>
                                <fmt:formatNumber value="${s.servicePrice}"/>
                            </td>
                            <td>
                                <fmt:setLocale value="vi_VN"/>
                                <c:set var="totalMoneyElectric" value="${s.servicePrice * numberElectric}"
                                       scope="page"/>
                                <c:set var="totalMoneyWater" value="${s.servicePrice * numberWater}" scope="page"/>
                                <c:choose>
                                    <c:when test="${s.serviceName=='Điện'}">
                                        <fmt:formatNumber value="${totalMoneyElectric}"/>
                                    </c:when>
                                    <c:when test="${s.serviceName=='Nước'}">
                                        <fmt:formatNumber value="${totalMoneyWater}" />
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber value="${s.servicePrice}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- Export Excel Btn -->
                <div class="export_bill_button">
                    <a href="export-excel?billID=${BILL.billID}">Xuất hoá đơn ra file Excel</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp"%>

<!-- Push notification element -->
<div id="push-noti"></div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="./assets/js/renter/Renter-payment.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/sendWebsocket.js"></script>
<script src="./assets/js/receiveWebsocket.js"></script>
<script>
    var notificationElement = document.getElementById("notifications")
    if (${requestScope.RESPONSE_MSG ne null}) {
        notificationElement.classList.add("display_notification")
        notificationElement.classList.remove("hidden_notification")
    }
</script>
<script type="text/javascript">
    // Send
    <c:if test="${requestScope.RESPONSE_MSG.status == true}">
    const params = new Object();
    params.sender = "hostel_renter";
    params.receiver = "hostel_owner";
    params.hostel_receiver_id = null;
    params.account_receiver_id = "${requestScope.HOSTEL_OWNER_ID}";
    params.messages = "${requestScope.SOCKET_MSG}";
    sendToWebSocket(params);
    </c:if>
    // Receive
    receiveWebsocket(alertPushNoti);
    // Close when leave
    window.onbeforeunload = function(){
        receiveWebsocket.disconnectWebSocket();
    };
</script>

</body>

</html>