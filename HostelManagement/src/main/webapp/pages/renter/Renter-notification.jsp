<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông báo</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <title>Renter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-notification.css">


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
            <c:if test="${NOTIFY == null}">
                <h1 style="font-size: 28px; color: red; text-align: center;line-height: 28px;margin-top: 20px">Bạn chưa có thông báo
                    nào!</h1>
            </c:if>
            <c:if test="${NOTIFY != null}">
                <div class="notification">
                    <h1>Danh Sách Thông Báo Của Khu Trọ</h1>
                    <table id="example" class="display table table-striped" width="100%" data-page-length="25"
                           data-order="[[ 1, &quot;asc&quot; ]]">
                        <thead>
                        <tr>
                            <th scope="col">Stt</th>
                            <th scope="col">Chủ đề</th>
                            <th scope="col">Nội dung</th>
                            <th scope="col">Ngày tạo</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="0" scope="page"/>
                        <c:forEach var="n" items="${NOTIFY}">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <th scope="row">${count}</th>
                                <td>${n.title}</td>
                                <td>${n.content}</td>
                                <td>
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${n.createDate}" var="createDate"/>
                                    <fmt:formatDate value="${createDate}" type="Date" pattern="dd-MM-yyyy"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>


<!-- footer -->

<%@include file="components/footer.jsp" %>

<!-- Push notification element -->
<div id="push-noti"></div>


<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--        crossorigin="anonymous"></script>--%>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
<script src="./assets/js/renter/renter-notification.js"></script>
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