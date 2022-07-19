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
    <title>Renter</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-home-page.css">

</head>

<body>
<%
    Account account = (Account)session.getAttribute("USER");
%>
<div>
    <nav class="navbar row">
        <div class="navbar-left">
            <div class="dropdown"  style="padding-left: 15px;">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"style="width:80px ;height: 35px;font-size: 14px;background-color: rgb(0, 0, 0);">
                    Menu
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="HostelRenterPage" style="font-size: 15px;">Thông tin phòng</a>
                    <a class="dropdown-item" href="get-roommate-infor" style="font-size: 15px;">Bạn cùng phòng</a>
                    <a class="dropdown-item" href="ContractPage" style="font-size: 15px;">Hợp đồng</a>
                    <a class="dropdown-item" href="renter-invoice"style="font-size: 15px;">Hóa đơn</a>
                    <a class="dropdown-item" href="Renter-report"style="font-size: 15px;">Báo cáo</a>
                    <a class="dropdown-item" id = "test" href="RenterNotificationPage"style="font-size: 15px;">Thông báo</a>
                    <a class="dropdown-item" href="Renter-add-roommate"style="font-size: 15px;">Thêm bạn</a>
                    <a class="dropdown-item" href="Get-report"style="font-size: 15px;">Xem báo cáo</a>
                    <a class="dropdown-item" href="HostelRenterProfilePage?<%= account.getAccId()%>"style="font-size: 15px;">Hồ sơ</a>
                    <a class="dropdown-item" href="logout"style="font-size: 15px;">Đăng xuất</a>
                </div>
            </div>
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="link">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#" style="text-decoration: none; color:white">Người thuê</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">Thông tin phòng</li>
                </ol>
            </nav>
        </div>
        <div class="navbar-center">
            <a href="" role="button"><img src="./assets/images/logos/logowhite.png" alt=""></a>
        </div>
        <div class="navbar-right">
            <a href="logout" role="button">Đăng xuất</a>
        </div>

    </nav>

    <div class="row main-body">
        <div class="dashboard">
            <div class="infor-top">
                <img src="./assets/images/avatars/user-avatar.jpg" alt="">
                <h3><%= account.getAccountInfo().getInformation().getFullname() %></h3>
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
                        <h3><a href="HostelRenterPage" style="color:rgb(4, 4, 255)">Thông tin phòng</a></h3>
                        <h3><a href="get-roommate-infor">Bạn cùng phòng</a></h3>
                        <h3><a href="ContractPage">Hợp đồng</a></h3>
                        <h3><a href="renter-invoice">Hóa đơn</a></h3>
                        <h3><a href="Renter-report">Gửi báo cáo</a></h3>
                        <h3><a href="RenterNotificationPage">Xem thông báo</a></h3>
                        <h3><a href="Renter-add-roommate">Thêm bạn</a></h3>
                        <h3><a href="Get-report">Xem báo cáo</a></h3>
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


        <div class="content">
            <div class="content-top">
                <div class="content-top-1 ">
                    <h3>Khu Trọ</h3>
                    <p><strong>Tên: </strong>${HOSTEL.hostelName}</p>
                    <p><strong>Địa chỉ: </strong>${HOSTEL.address},${HOSTEL.ward.split('-')[1]},${HOSTEL.district.split('-')[1]},${HOSTEL.city.split('-')[1]}</p>
                    <h3>Phòng</h3>
                    <p><strong>Phòng số: </strong>${ROOM_INFOR.roomNumber}</p>
                    <p><strong>Diện tích: </strong>${ROOM_INFOR.roomArea} m2</p>
                    <p><strong>Số thành viên: </strong>${NUM_OF_MEMBERS}/${ROOM_INFOR.capacity}</p>
                </div>
                <div class="content-top-2 ">
                    <h3>Chủ Trọ</h3>
                    <p><strong>Tên: </strong>${ACCOUNT_INFOR.fullname}</p>
                    <p><strong>Số Điện Thoại: </strong> ${ACCOUNT_INFOR.phone}</p>
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
                                    <fmt:formatNumber value="${s.servicePrice}" type="currency" currencySymbol="VNĐ"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <footer>
        <div>
            <div class="row">
                <div class="col-12">
                    <div class="copyright-wrapper d-flex justify-content-center">
<%--                         <div class="copyright-logo">--%>
<%--                        <img src="./assets/images/logos/logo-white.png" alt="Logo">--%>
<%--                        </div>--%>
                        <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>

        <textarea id="textAreaMessage" rows="10" cols="50"></textarea>

    </footer>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="./assets/js/receiveWebsocket.js"></script>
    <script type="text/javascript">
        receiveWebsocket('#textAreaMessage');
    </script>
</div>
</body>

</html>
