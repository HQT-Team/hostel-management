<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Contract</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-contract.css">

</head>

<body>
<%
    Account account = (Account)session.getAttribute("USER");
%>
<div>
    <!-- navbar -->
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
                    <a class="dropdown-item" href="Renter-invoice-page"style="font-size: 15px;">Hóa đơn</a>
                    <a class="dropdown-item" href="Renter-report"style="font-size: 15px;">Báo cáo</a>
                    <a class="dropdown-item" href="RenterNotificationPage"style="font-size: 15px;">Thông báo</a>
                    <a class="dropdown-item" href="Renter-add-roommate"style="font-size: 15px;">Thêm bạn</a>
                    <a class="dropdown-item" href="Get-report"style="font-size: 15px;">Xem báo cáo</a>
                    <a class="dropdown-item" href="HostelRenterProfilePage?<%= account.getAccId()%>"style="font-size: 15px;">Hồ sơ</a>
                    <a class="dropdown-item" href="logout"style="font-size: 15px;">Đăng xuất</a>
                </div>
            </div>
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="link">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#" style="text-decoration: none; color:blue">Người thuê</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">Thông tin phòng</li>
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
                <h3><%=account.getUsername()%></h3>
                <p>Renter</p>
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
                        <h3><a href="HostelRenterPage" >Thông tin phòng</a></h3>
                        <h3><a href="get-roommate-infor">Bạn cùng phòng</a></h3>
                        <h3><a href="ContractPage" style="color:rgb(4, 4, 255)">Hợp đồng</a></h3>
                        <h3><a href="Renter-bill">Hóa đơn</a></h3>
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
        <div class="content row">
            <div class="contract-content">
                <h2>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</h2>
                <h3>Độc lập - Tự do - Hạnh phúc</h3>
                <br>
                <h4>Hợp đồng thuê phòng</h4>
                <p>1. Bên cho thuê</p>
                <h5>Ông: ${OWNER_INFO.fullname}</h5><br>
                <h5>Ngày sinh: ${OWNER_INFO.birthday}</h5><br>
                <h5>CCCD: ${OWNER_INFO.cccd}</h5><br>
                <h5>SĐT: ${OWNER_INFO.phone}</h5><br>
                <p>2. Bên thuê</p>
                <h5>Ông: ${RENTER_INFO.fullname}</h5><br>
                <h5>Ngày sinh: ${RENTER_INFO.birthday}</h5><br>
                <h5>CCCD: ${RENTER_INFO.cccd}</h5><br>
                <h5>SĐT: ${RENTER_INFO.phone}</h5><br>
                <p>Sau khi bàn bạc :</p>
                <h5>Bên A xác nhận cho bên B thuê phòng trọ tại địa chỉ: ${HOSTEL.address}, ${HOSTEL.ward.split('-')[1]},
                    ${HOSTEL.district.split('-')[1]}, ${HOSTEL.city.split('-')[1]} </h5><br>
                <h5>Giá :
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${CONTRACT.price}" type="currency" currencySymbol="VNĐ"/>
                </h5><br>
                <h5>Tiền cọc:
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${CONTRACT.deposit}" type="currency" currencySymbol="VNĐ"/>
                </h5><br>
                <h5>Hợp đồng có giá trị từ ngày: ${CONTRACT.startDate} đến ngày : ${CONTRACT.expiration}</h5><br>
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
                    <div class="copyright-logo">
                        <!-- <img src="../../assets/images/logos/logo-white.png" alt="Logo"> -->
                    </div>
                    <div class="copyright-content" style="font-size: 18px;">© 2022 HQT Team. All rights
                        reserved.</div>
                </div>
            </div>
        </div>
    </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>

</html>