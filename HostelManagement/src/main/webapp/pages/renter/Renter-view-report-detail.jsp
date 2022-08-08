<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <title>Xem báo cáo chi tiết</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-view-report-detail.css">

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
        <%@include file="components/sidebar.jsp"%>
        <div class="content row">
    <%@include file="components/navbar.jsp"%>
            <div class="report">
                <h2>Chi Tiết Báo Cáo</h2>
                <c:forEach var="rp" items="${REPORT_LIST}">
                    <c:if test="${id == rp.reportID}">
                        <p><span>Phân Loại: </span><c:forEach var="cate" items="${REPORT_CATE}">
                            <c:if test="${rp.cateID == cate.cateID}">
                                ${cate.cateTitle}
                            </c:if>
                        </c:forEach></p>
                        <p><span>Nội Dung: </span>${rp.content}</p>
                        <p><span>Ngày Gửi: </span> ${rp.sendDate}</p>
                        <c:choose>
                            <c:when test="${rp.reply!=null}">
                                <p><span>Nội Dung Phản Hồi: </span>${rp.reply}</p>
                            </c:when>
                            <c:when test="${rp.reply==null}">
                                <p><span>Nội Dung Phản Hồi: </span>Chưa Phản Hồi</p>
                            </c:when>
                        </c:choose>
                        <c:if test="${rp.status == 0}">
                            <p style="color: #b12ce0"><span style="color: black">Tình Trạng: </span>Chưa tiếp nhận</p>
                        </c:if>
                        <c:if test="${rp.status == 1}">
                            <p style="color: red"><span style="color: black">Tình Trạng: </span>Đang xử lí</p>
                        </c:if>
                        <c:if test="${rp.status == 2}">
                            <p style="color: green"><span style="color: black">Tình Trạng: </span>Đã phản hồi</p>
                        </c:if>
                        <c:choose>
                            <c:when test="${rp.completeDate!=null}">
                                <p><span>Ngày Trả Lời: </span>${rp.completeDate}</p>
                            </c:when>
                            <c:when test="${rp.completeDate==null}">
                                <p><span style="color: black">Ngày Trả Lời: </span>Chưa Phản Hồi</p>
                            </c:when>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- footer -->

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
<script src="./assets/js/renter/Renter-view-report.js"></script>
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
</body>

</html>