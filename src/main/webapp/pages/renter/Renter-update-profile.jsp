<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page import="com.hqt.happyhostel.dto.Information" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <title>Renter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-update-profile.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body>
<%
    Account account = (Account) session.getAttribute("USER");
    Information ACC_INFO = (Information) session.getAttribute("ACC_INFO");
%>
<div>
    <!-- navbar -->
    <!-- content -->
    <div class="main-body row" style="padding: 0;margin: 0;">
        <%@include file="components/sidebar.jsp"%>
        <div class="content row">
    <%@include file="components/navbar.jsp"%>
            <div class="profile-update">
                <form action="profile-update" method="post" id="myForm">
                    <h2>Chỉnh sửa thông tin</h2>
                    <hr>
                    <div class="form-content">
                        <label for="form-item-input-1">Họ tên</label>
                        <input id="form-item-input-1" type="text" name="new-name"
                               value="<%=ACC_INFO.getFullname()%>">
                        <span id="mes-1"></span>
                    </div>
                    <div class="form-content">
                        <label for="form-item-input-2">Email </label>
                        <input id="form-item-input-2" type="text" name="new-email"
                               value="<%=ACC_INFO.getEmail()%>">
                        <span id="mes-2"></span>

                    </div>
                    <div class="form-content">
                        <c:choose>
                            <c:when test="<%=ACC_INFO.getBirthday() == null%>">
                                <label for="form-item-input-3">Ngày sinh</label>
                                <input id="form-item-input-3" type="date" name="new-birthday" value="">
                            </c:when>
                            <c:otherwise>
                                <label for="form-item-input-3">Ngày sinh</label>
                                <input id="form-item-input-3" type="date" name="new-birthday"  value="<%=ACC_INFO.getBirthday()%>">
                            </c:otherwise>
                        </c:choose>

                        <span id="mes-3"></span>
                    </div>
                    <div class="form-content">
                        <label for="form-item-input-7">Giới tính</label>
                        <select name="new-sex" id="form-item-input-7">
                            <%
                                if (ACC_INFO.getSex() == 1) {
                            %>
                            <option value="1">Nam</option>
                            <option value="0">Nữ</option>
                            <%
                            } else {
                            %>
                            <option value="0">Nữ</option>
                            <option value="1">Nam</option>

                            <%
                                }
                            %>
                            />
                        </select>
                        <span id="mes-7"></span>
                    </div>
                    <div class="form-content">
                        <label for="form-item-input-4">Số điện thoại</label>
                        <input id="form-item-input-4" type="text" name="new-phone"
                               value="<%=ACC_INFO.getPhone() == null ? "" : ACC_INFO.getPhone()%>">
                        <span id="mes-4"></span>
                    </div>
                    <div class="form-content">
                        <label for="form-item-input-5">Địa chỉ</label>
                        <input id="form-item-input-5" type="text" name="new-address"
                               value="<%=ACC_INFO.getAddress() == null ? "" : ACC_INFO.getAddress()%>">
                        <span id="mes-5"></span>
                    </div>
                    <div class="form-content">
                        <label for="form-item-input-5">Số căn cước công dân</label>
                        <input id="form-item-input-6" type="text" name="new-cccd"
                               value="<%=ACC_INFO.getCccd() == null ? "" : ACC_INFO.getCccd()%>">
                        <span id="mes-6"></span>
                    </div>
                    <div id="submit-button">
                        <input id="submit-1" type="button" value="Cập nhật"/>
                    </div>
                </form>
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
<script src="./assets/js/renter/Renter-update-profile.js"></script>

<script>
    form_1.addEventListener("blur", () => {
        isRequire(form_1, "Vui lòng nhập trường này!")
    });
    form_2.addEventListener("blur", () => {
        isRequire(form_2, "Vui lòng nhập trường này!")
        isMail(form_2, "Email không hợp lệ!")
    });
    form_3.addEventListener("blur", () => {
        isRequire(form_3, "Vui lòng nhập trường này!")
    });
    form_4.addEventListener("blur", () => {
        isRequire(form_4, "Vui lòng nhập trường này!")
        isPhone(form_4, "Số điện thoại không hợp lệ!")
    });
    form_5.addEventListener("blur", () => {
        isRequire(form_5, "Vui lòng nhập trường này!")
    });
    form_6.addEventListener("blur", () => {
        isRequire(form_6, "Vui lòng nhập trường này!")
        isCccd(form_6, "Số cccd không khả dụng!")
    });
    document.getElementById("sidebaritem").classList.add("active");
</script>
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