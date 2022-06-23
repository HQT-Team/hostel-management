<%@ page import="com.hqt.happyhostel.dto.Account" %><%--
  Created by IntelliJ IDEA.
  User: 84337
  Date: 6/18/2022
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-add-roommate.css">

</head>

<body>
<%
    Account account = (Account)session.getAttribute("USER");
%>
<div>
    <nav class="navbar row">
        <div class="navbar-left">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    Menu
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="HostelRenterPage">Thông tin phòng</a>
                    <a class="dropdown-item" href="get-roommate-infor">Bạn cùng phòng</a>
                    <a class="dropdown-item" href="ContractPage">Hợp đồng</a>
                    <a class="dropdown-item" href="Renter-bill">Hóa đơn</a>
                    <a class="dropdown-item" href="Renter-report">Báo cáo</a>
                    <a class="dropdown-item" href="RenterNotificationPage">Thông báo</a>
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
                    <li class="breadcrumb-item active" aria-current="page">Thông tin phòng</li>
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

    <div class="row">
        <div class="dashboard hidden" id="dashboard">
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
                            <h3><a href="HostelRenterPage" style="color:rgb(4, 4, 255)">Thông tin phòng</a></h3>
                            <h3><a href="get-roommate-infor">Bạn cùng phòng</a></h3>
                            <h3><a href="ContractPage">Hợp đồng</a></h3>
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


        <div class="content">
            <div class="div-controll-form" id="div-controll-form">
                <form action="AddRenterRoommatePage" method="post" class="form" id="form">
                    <h1>Thêm Thành Viên</h1>
                    <h3 style="color: red">${ERROR}</h3>
                    <h3 style="color: green">${SUCCESS}</h3>
                    <div class="form-item" id="form-item">
                        <input id="form-item-input-1" name="full-name" type="text" placeholder="Tên đây đủ">
                        <p class="border-bottom"></p>
                        <span id="mes-1"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-2" placeholder="Email" type="email" name="email" multiple>
                        <p class="border-bottom"></p>
                        <span id="mes-2"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-3" type="text" placeholder="Ngày sinh" name="dob">
                        <p class="border-bottom"></p>
                        <span id="mes-3"></span>
                    </div>
                    <div class="form-item">
                        <select name="gender" id="form-item-input-4">
                            <option value="1">Nam</option>
                            <option value="0">Nữ</option>
                            <option value="0">Khác</option>
                        </select>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-5" type="text" placeholder="Số điện thoại" name="phone-number">
                        <p class="border-bottom"></p>
                        <span id="mes-5"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-6" type="text" placeholder="Địa chỉ" name="address">
                        <p class="border-bottom"></p>
                        <span id="mes-6"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-7" type="text" placeholder="Số CCCD" name="cccd">
                        <p class="border-bottom"></p>
                        <span id="mes-7"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-8" type="text" placeholder="Tên đầy đủ của cha/mẹ" name="parent-name">
                        <p class="border-bottom"></p>
                        <span id="mes-8"></span>
                    </div>
                    <div class="form-item">
                        <input id="form-item-input-9" type="text" placeholder="Số điện thoại của cha/mẹ" name="parent-phone">
                        <p class="border-bottom"></p>
                        <span id="mes-9"></span>
                    </div>
                    <input id="form-item-submit" type="button" value="Tạo Mới">
                </form>
            </div>
        </div>
    </div>
</div>
<footer>
    <div>
        <div class="row">
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center">
                    <!-- <div class="copyright-logo">
                        <img src="../../assets/images/logos/logo-white.png" alt="Logo">
                    </div> -->
                    <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
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
<script src="./assets/js/renter/Renter-add-roommate.js"></script>

</body>

</html>
