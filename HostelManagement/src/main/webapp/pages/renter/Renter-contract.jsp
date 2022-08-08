<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %><%--
  Created by IntelliJ IDEA.
  User: 84337
  Date: 6/18/2022
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hợp đồng</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-contract.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">


</head>

<body>
<%
    Account account = (Account) session.getAttribute("USER");
%>
<div>
    <!-- navbar -->

    <!-- content -->
    <div class="main-body row" style="padding: 0;margin: 0;">
        <%@include file="components/sidebar.jsp" %>
        <div class="content row">
        <%@include file="components/navbar.jsp" %>
            <div class="contract-content">
                <div class="contract-head"><h4>Hợp Đồng Thuê Phòng</h4></div>
                <div class="contract-body">
                    <div class="owner-infor">
                        <h5><strong>Chủ trọ: </strong>${OWNER_INFO.fullname}</h5><br>
                        <h5><strong>Ngày sinh: </strong>
                            <c:if test="${OWNER_INFO.birthday != null}">
                                <fmt:parseDate pattern="yyyy-MM-dd" value="${OWNER_INFO.birthday}" var="birthday"/>
                                <fmt:formatDate value="${birthday}" type="Date" pattern="dd-MM-yyyy"/>
                            </c:if>
                            <c:if test="${OWNER_INFO.birthday == null}">
                                Không có thông tin!
                            </c:if>
                        </h5><br>
                        <h5><strong>CCCD: </strong>${OWNER_INFO.cccd}</h5><br>
                        <h5><strong>SĐT: </strong>${OWNER_INFO.phone eq null ? "Không có thông tin!" : OWNER_INFO.phone}
                        </h5><br>
                    </div>
                    <div class="renter-infor">
                        <h5><strong>Người Thuê: </strong>${RENTER_INFO.fullname}</h5><br>
                        <h5><strong>Ngày sinh: </strong>
                            <c:if test="${RENTER_INFO.birthday != null}">
                                <fmt:parseDate pattern="yyyy-MM-dd" value=" ${RENTER_INFO.birthday}" var="birthday"/>
                                <fmt:formatDate value="${birthday}" type="Date" pattern="dd-MM-yyyy"/>
                            </c:if>
                            <c:if test="${RENTER_INFO.birthday == null}">
                                Không có thông tin!
                            </c:if>
                        </h5><br>
                        <h5>
                            <strong>CCCD: </strong>${RENTER_INFO.cccd eq null ? "Không có thông tin!" : RENTER_INFO.cccd}
                        </h5><br>
                        <h5>
                            <strong>SĐT: </strong>${RENTER_INFO.phone eq null ? "Không có thông tin!" : RENTER_INFO.phone}
                        </h5><br>
                    </div>
                </div>
                <div class="contract-result">
                    <h3><strong style="font-size: 22px; margin-bottom: 5px">Thông tin phòng</strong></h3>
                    <h5><strong>Địa chỉ: </strong>${HOSTEL.address}, ${HOSTEL.ward.split('-')[1]},
                        ${HOSTEL.district.split('-')[1]}, ${HOSTEL.city.split('-')[1]} </h5><br>
                    <h5><strong>Giá: </strong>
                        <fmt:setLocale value="vi_VN"/>
                        <fmt:formatNumber value="${CONTRACT.price}" type="currency" currencySymbol="VNĐ"/>
                    </h5><br>
                    <h5><strong>Tiền cọc: </strong>
                        <fmt:setLocale value="vi_VN"/>
                        <fmt:formatNumber value="${CONTRACT.deposit}" type="currency" currencySymbol="VNĐ"/>
                    </h5><br>
                    <h5><strong>Hợp đồng có giá trị từ ngày: </strong>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${CONTRACT.startDate}" var="startDate"/>
                        <fmt:formatDate value="${startDate}" type="Date" pattern="dd-MM-yyyy"/>
                        <strong>đến ngày: </strong>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${CONTRACT.expiration}" var="expiration"/>
                        <fmt:formatDate value="${expiration}" type="Date" pattern="dd-MM-yyyy"/>
                    </h5>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- footer -->

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
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
</body>

</html>