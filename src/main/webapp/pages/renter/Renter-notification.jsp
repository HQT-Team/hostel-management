<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />
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
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-notification.css">
</head>

<body>
<%
    Account account = (Account)session.getAttribute("USER");
%>
<div>
    <!-- navbar -->
    <nav class="navbar row">
        <div class="navbar-left">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="HostelRenterPage">Thông tin phòng</a>
                    <a class="dropdown-item" href="get-roommate-infor">Bạn cùng phòng</a>
                    <a class="dropdown-item" href="ContractPage">Hợp đồng</a>
                    <a class="dropdown-item" href="Renter-bill">Hóa đơn</a>
                    <a class="dropdown-item" href="Renter-report">Báo cáo</a>
                    <a class="dropdown-item" href="RenterNotificationPage" >Thông báo</a>
                    <a class="dropdown-item" href="HostelRenterProfilePage?<%= account.getAccId()%>">Hồ sơ</a>
                    <a class="dropdown-item" href="Renter-add-roommate">Thêm bạn</a>
                    <a class="dropdown-item" href="Get-report">Xem báo cáo</a>
                    <a class="dropdown-item" href="logout">Đăng xuất</a>

                </div>
            </div>
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="link">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#" style="text-decoration: none; color:blue">Người thuê</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">Thông báo</li>
                </ol>
            </nav>
        </div>
        <div class="navbar-center">
            <a href="" role="button"><img src="./assets/images/logos/logo.png" alt=""></a>
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
                        <h3><a href="HostelRenterPage">Thông tin phòng</a></h3>
                        <h3><a href="get-roommate-infor">Bạn cùng phòng</a></h3>
                        <h3><a href="ContractPage">Hợp đồng</a></h3>
                        <h3><a href="Renter-bill">Hóa đơn</a></h3>
                        <h3><a href="Renter-report">Gửi báo cáo</a></h3>
                        <h3><a href="RenterNotificationPage" style="color:rgb(4, 4, 255)">Xem thông báo</a></h3>
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
                        <h3><a href="HostelRenterProfilePage">Hồ sơ</a></h3>
                        <h3><a href="logout">Đăng xuất</a></h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="content row">
            <div class="notification">
                <table class="table table-striped">
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
                            <td>${n.createDate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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