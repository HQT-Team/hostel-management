<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-home-page.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
</head>

<body>
<%
    Account account = (Account) session.getAttribute("USER");
%>
<div>


    <div class="row main-body" style="padding: 0;margin: 0;">

        <%@include file="components/sidebar.jsp" %>
        <div class="content">
            <%@include file="components/navbar.jsp" %>
            <h1 class="title">Tổng Quan Thông Tin Về Phòng Trọ</h1>
            <div class="main-information">
                <h2 style="text-align: center;font-size: 26px; margin: 10px 0 20px 0">Thông tin phòng trọ</h2>
                <div class="content-top">
                    <div class="content-top-1">
                        <p><span>Phòng số: </span>${ROOM_INFOR.roomNumber}</p>
                        <p><span>Diện tích phòng: </span>${ROOM_INFOR.roomArea} m2</p>
                        <p><span>Tên khu trọ: </span>${HOSTEL.hostelName}</p>
                        <p>
                            <span>Địa chỉ: </span>${HOSTEL.address},${HOSTEL.ward.split('-')[1]},${HOSTEL.district.split('-')[1]},${HOSTEL.city.split('-')[1]}
                        </p>
                    </div>
                    <div class="content-top-2">
                        <p><span>Tên chủ phòng: </span>${ACCOUNT_INFOR.fullname}</p>
                        <p><span>Số điện thoại: </span> ${ACCOUNT_INFOR.phone eq null ? "Không có thông tin!" : ACCOUNT_INFOR.phone}</p>
                        <p><span>Số thành viên hiện tại: </span>${NUM_OF_MEMBERS}</p>
                        <p><span>Số thành viên tối đa: </span>${ROOM_INFOR.capacity}</p>
                    </div>
                </div>
                <div class="content-bottom">
                    <div class="content-bottom-1">
                        <h3>Thiết Bị</h3>
                        <table class="table">
                            <tr>
                                <th>Tên</th>
                                <th>Số lượng</th>
                            </tr>
                            <c:forEach var="infras" items="${INFRASTRUCTURES}">
                                <tr>
                                    <td>${infras.name}</td>
                                    <td>${infras.quantity}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="content-bottom-2">
                        <h3>Dịch vụ</h3>
                        <table class="table">
                            <tr>
                                <th>Tên</th>
                                <th>Giá</th>
                            </tr>
                            <c:forEach var="s" items="${SERVICES}">
                                <tr>

                                    <td>${s.serviceName}</td>
                                    <td>
                                        <fmt:setLocale value="vi_VN"/>
                                        <fmt:formatNumber value="${s.servicePrice}" type="currency"
                                                          currencySymbol="VNĐ"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <%@include file="components/footer.jsp" %>

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
    <!-- Push notification -->
    <script src="./assets/js/push-notification-alert.js"></script>
    <!-- Web socket -->
    <script src="./assets/js/receiveWebsocket.js"></script>

    <script type="text/javascript">
        // Receive
        receiveWebsocket(alertPushNoti);
        // Close when leave
        window.onbeforeunload = function () {
            receiveWebsocket.disconnectWebSocket();
        };
    </script>
</div>
</body>

</html>