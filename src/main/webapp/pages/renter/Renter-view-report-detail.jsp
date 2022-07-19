<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
    <nav class="navbar row">
        <div class="navbar-left">
            <div class="dropdown" style="padding-left: 15px;">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"
                        style="width:80px ;height: 35px;font-size: 14px;background-color: rgb(0, 0, 0);">
                    Menu
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="HostelRenterPage" style="font-size: 15px;">Thông tin phòng</a>
                    <a class="dropdown-item" href="get-roommate-infor" style="font-size: 15px;">Bạn cùng phòng</a>
                    <a class="dropdown-item" href="ContractPage" style="font-size: 15px;">Hợp đồng</a>
                    <a class="dropdown-item" href="Renter-bill" style="font-size: 15px;">Hoá Đơn</a>
                    <a class="dropdown-item" href="Renter-report" style="font-size: 15px;">Báo cáo</a>
                    <a class="dropdown-item" href="RenterNotificationPage" style="font-size: 15px;">Thông báo</a>
                    <a class="dropdown-item" href="Renter-add-roommate" style="font-size: 15px;">Thêm bạn</a>
                    <a class="dropdown-item" href="Get-report" style="font-size: 15px;">Xem báo cáo</a>
                    <a class="dropdown-item" href="HostelRenterProfilePage?<%= account.getAccId()%>"
                       style="font-size: 15px;">Hồ sơ</a>
                    <a class="dropdown-item" href="logout" style="font-size: 15px;">Đăng xuất</a>
                </div>
            </div>
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="link">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#" style="text-decoration: none; color:#FFFFFF">Người thuê</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">Chi tiết báo cáo</li>
                </ol>
            </nav>
        </div>
        <div class="navbar-center">
            <a href="" role="button"><img src="./assets/images/logos/logowhite.png" alt=""></a>
        </div>
        <div class="navbar-right">
            <a href="logout" role="button">Đăng xuất <img src="./assets/images/logos/logout.png" alt=""></a>
        </div>

    </nav>

    <!-- content -->
    <div class="main-body row">
        <div class="dashboard">
            <div class="infor-top">
                <img src="./assets/images/avatars/user-avatar.jpg" alt="">
                <h3>Trần Hoài Nam</h3>
                <p>Người Thuê</p>
            </div>
            <div class="card">
                <div class="card-header" id="headingOne">
                    <button class="collapsed show" data-toggle="collapse" data-target="#collapseOne"
                            aria-expanded="true" aria-controls="collapseOne">
                        <img src="./assets/images/logos/homeicon.webp">
                        Phòng trọ
                    </button>
                </div>

                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        <h3><a href="HostelRenterPage">Thông tin phòng</a></h3>
                        <h3><a href="get-roommate-infor">Bạn cùng phòng</a></h3>
                        <h3><a href="ContractPage">Hợp đồng</a></h3>
                        <h3><a href="Renter-invoice-page">Hóa đơn</a></h3>
                        <h3><a href="Renter-report">Gửi báo cáo</a></h3>
                        <h3><a href="RenterNotificationPage">Xem thông báo</a></h3>
                        <h3><a href="Renter-add-roommate">Thêm bạn</a></h3>
                        <h3><a href="Get-report" style="color:rgb(4, 4, 255)">Xem báo cáo</a></h3>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingTwo">
                    <button class="collapsed" data-toggle="collapse" data-target="#collapseTwo"
                            aria-expanded="false" aria-controls="collapseTwo">
                        <img src="./assets/images/logos/account.png">
                        Tài khoản
                    </button>
                </div>
                <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        <h3><a href="HostelRenterProfilePage?<%=account.getAccId()%>">Hồ sơ</a></h3>
                        <h3><a href="logout">Đăng xuất</a></h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="content row">
            <div class="report">
                <h2>Chi Tiết Báo Cáo</h2>
                <c:forEach var="rp" items="${REPORT_LIST}">
                    <c:if test="${id == rp.reportID}">
                        <p><strong>Phân Loại: </strong><c:forEach var="cate" items="${REPORT_CATE}">
                            <c:if test="${rp.cateID == cate.cateID}">
                                ${cate.cateTitle}
                            </c:if>
                        </c:forEach></p>
                        <p><strong>Nội Dung: </strong>${rp.content}</p>
                        <p><strong>Ngày Gửi: </strong> ${rp.sendDate}</p>
                        <c:choose>
                            <c:when test="${rp.reply!=null}">
                                <p><strong>Nội Dung Phản Hồi: </strong>${rp.reply}</p>
                            </c:when>
                            <c:when test="${rp.reply==null}">
                                <p><strong>Nội Dung Phản Hồi: </strong>Chưa Phản Hồi</p>
                            </c:when>
                        </c:choose>
                        <c:if test="${rp.status == 0}">
                            <p style="color: lightsalmon"><strong style="color: black">Tình Trạng: </strong>Đang chờ</p>
                        </c:if>
                        <c:if test="${rp.status == 1}">
                            <p style="color: green"><strong style="color: black">Tình Trạng: </strong>Đã phản hồi</p>
                        </c:if>
                       <c:choose>
                           <c:when test="${rp.completeDate!=null}">
                               <p><strong>Ngày Trả Lời: </strong>${rp.completeDate}</p>
                           </c:when>
                           <c:when test="${rp.completeDate==null}">
                               <p><strong style="color: black">Ngày Trả Lời: </strong> Chưa Phản Hồi</p>
                           </c:when>
                       </c:choose>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- footer -->

<footer>
    <div>
        <div class="row">
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center">
                    <div class="copyright-content" style="font-size: 18px;">© 2022 HQT Team. All rights
                        reserved.
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

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
