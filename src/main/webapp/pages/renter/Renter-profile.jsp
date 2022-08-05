<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thông tin cá nhân</title>
  <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />
  <title>Renter</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="./assets/css/core_style/core.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="./assets/css/renter_page/Renter-profile.css">
  <!-- CSS Push Notification -->
  <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
</head>

<body>
<%
  Account account = (Account)session.getAttribute("USER");
%>
<div>
  <!-- navbar -->
  <!-- content -->
  <div class="main-body row" style="padding: 0;margin: 0;">
    <%@include file="components/sidebar.jsp"%>
    <div class="content row">
  <%@include file="components/navbar.jsp"%>
      <div class="hidden_notification" id="notification">
        <p>${requestScope.MES}</p>
        <span class="progress"></span>
      </div>
      <div class="profile">
        <h2>Thông Tin Cá Nhân</h2>
        <h3><span>Tên: </span>${ACC_INFO.fullname}</h3>
        <h3><span>Email: </span>${ACC_INFO.email}</h3>
        <c:choose>
          <c:when test="${ACC_INFO.birthday eq null}">
            <h3><span>Ngày sinh: </span>Không có thông tin!</h3>
          </c:when>
          <c:otherwise>
            <h3><span>Ngày sinh: </span><fmt:parseDate pattern="yyyy-MM-dd" value=" ${ACC_INFO.birthday}" var="birthday" /><fmt:formatDate value="${birthday}" type="Date" pattern="dd-MM-yyyy"/></h3>
          </c:otherwise>
        </c:choose>

        <h3><span>Giới tính: </span>
          <c:if test="${ACC_INFO.sex == 1}">
            Nam
          </c:if>
          <c:if test="${ACC_INFO.sex == 0}">
            Nữ
          </c:if>
        </h3>
        <h3><span>Số điện thoại: </span>${ACC_INFO.phone eq null ? "Không có thông tin!" : ACC_INFO.phone}</h3>
        <h3><span>Địa chỉ: </span>${ACC_INFO.address eq null ? "Không có thông tin!" : ACC_INFO.address}</h3>
        <h3><span>CCCD: </span>${ACC_INFO.cccd eq null ? "Không có thông tin!" : ACC_INFO.cccd}</h3>
        <% session.setAttribute("ACC_INFO", request.getAttribute("ACC_INFO")); %>
        <a href="Renter-update-profile">Chỉnh Sửa</a>
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
<script>
  var notification = document.getElementById("notification")
  if (${requestScope.MES != null}){
    notification.classList.add("display_notification")
    notification.classList.remove("hidden_notification")
  }
</script>
</body>

</html>