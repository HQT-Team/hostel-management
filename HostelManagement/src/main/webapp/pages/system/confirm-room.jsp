<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value = "vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Xác nhận hợp đồng</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/system_style/confirm-room_style/style.css">
</head>

<body class="bg-light">

<!-- Navbar -->
<div class="main-nav bg-white">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="main-nav__logo">
                    <a href="HomePage" class="main-nav__logo-link">
                        <img class="main-nav__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9">
                <div class="main-nav__title">
                    Thông tin tổng quan
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Content -->
<div class="container mt-5 pt-5 mb-5 pb-5 content">
    <div class="row">
        <div class="col-12 col-lg-10 col-xl-8 col-xxl-7 m-auto content__wrapper">
            <h1 class="content__header">
                Chào mừng bạn đến với khu trọ <span>${sessionScope.CONTRACT_HOSTEL.hostelName}</span>
            </h1>
            <p class="content__subheader">
                <span>*</span> Sau đây là thông tin tổng quát về khu trọ và phòng mà bạn thuê,
                vui lòng đọc kỹ và bấm xác nhận để qua bước tiếp theo
            </p>
            <div class="content__spacer"></div>
            <div class="content__infor">
                <h2 class="content__infor-title">Thông tin khu trọ</h2>
                <div class="row">
                    <div class="col-12 col-md-6">
                        <p class="content__infor-item">Khu trọ: <span>${sessionScope.CONTRACT_HOSTEL.hostelName}</span></p>
                        <p class="content__infor-item">Địa chỉ:
                            <span>
                                ${sessionScope.CONTRACT_HOSTEL.address},
                                ${sessionScope.CONTRACT_HOSTEL.ward.split("-")[1]},
                                ${sessionScope.CONTRACT_HOSTEL.district.split("-")[1]},
                                ${sessionScope.CONTRACT_HOSTEL.city.split("-")[1]},
                            </span></p>
                    </div>
                    <div class="col-12 col-md-6">
                        <p class="content__infor-item">Chủ trọ: <span>${sessionScope.CONTRACT_OWNER.information.fullname}</span></p>
                        <p class="content__infor-item">Số Điện Thoại: <span>${sessionScope.CONTRACT_OWNER.information.phone eq null ? "N/a" : sessionScope.CONTRACT_OWNER.information.phone}</span></p>
                    </div>
                </div>
            </div>
            <div class="content__spacer"></div>
            <div class="content__infor">
                <h2 class="content__infor-title">Thông tin phòng trọ</h2>
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <p class="content__infor-item">Phòng số: <span>${sessionScope.CONTRACT_ROOM.roomNumber}</span></p>
                        <p class="content__infor-item">Diện tích: <span>${sessionScope.CONTRACT_ROOM.roomArea} m2</span></p>
                        <p class="content__infor-item">Gác: <span>${sessionScope.CONTRACT_ROOM.hasAttic eq 0 ? "Không" : "Có"}</span></p>
                        <p class="content__infor-item">Số lượng thành viên tối đa: <span>${sessionScope.CONTRACT_ROOM.capacity}</span></p>
                    </div>
                    <div class="col-12 col-sm-6">
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${sessionScope.CONTRACT.startDate}" var="startDate"/>
                        <p class="content__infor-item">Ngày bắt đầu thuê: <span><fmt:formatDate pattern="dd/MM/yyyy" value="${startDate}"/></span></p>
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${sessionScope.CONTRACT.expiration}" var="endDate"/>
                        <p class="content__infor-item">Ngày kết thúc thuê: <span><fmt:formatDate pattern="dd/MM/yyyy" value="${endDate}"/></span></p>
                        <p class="content__infor-item">Tiền cọc: <span><fmt:formatNumber value="${sessionScope.CONTRACT.deposit}" type="currency" currencySymbol="VNĐ"/></span></p>
                        <p class="content__infor-item">Tiền phòng: <span><fmt:formatNumber value="${sessionScope.CONTRACT.price}" type="currency" currencySymbol="VNĐ"/></span></p>
                    </div>
                </div>
            </div>
            <div class="content__spacer m-0"></div>
            <div class="row">
                <div class="col-12 col-md-6 content__table">
                    <h2 class="content__infor-title">Phí dịch vụ hàng tháng</h2>
                    <table class="table table-bordered content__infor-table">
                        <thead>
                        <tr class="text-center">
                            <th>Tên</th>
                            <th>Giá</th>
                            <th>Đơn vị tính</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="listServices" items="${sessionScope.CONTRACT_SERVICES_LIST}">
                            <tr>
                                <td>${listServices.serviceName}</td>
                                <td>
                                    <fmt:formatNumber value="${listServices.servicePrice}" type="currency" currencySymbol="VNĐ"/>
                                </td>
                                <td>1 ${listServices.unit}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-12 col-md-6 content__table">
                    <h2 class="content__infor-title">Cơ sở vật chất</h2>
                    <table class="table table-bordered content__infor-table">
                        <thead>
                        <tr class="text-center">
                            <th>Tên</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <c:forEach var="infrastructure" items="${sessionScope.CONTRACT_ROOM_INFRASTRUCTURE_LIST}">
                            <tbody>
                            <tr>
                                <td>${infrastructure.name}</td>
                                <c:choose>
                                    <c:when test="${infrastructure.status == 1}">
                                        <td class="good">Sử dụng tốt</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="break">Hư hỏng</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            </tbody>
                        </c:forEach>

                    </table>
                </div>
            </div>
            <div class="content__spacer"></div>
            <form action="handle-confirm-contract" method="post" id="content__form" class="content__form">
                <div class="form-group">
                    <div class="d-flex">
                        <input type="checkbox" id="content__form-confirm" class="content__form-confirm"
                               name="action" value="continue">
                        <label for="content__form-confirm" class="content__form-label">Tôi đã đọc kỹ và xác nhận mọi
                            thông tin trên đều đúng như đã
                            thỏa thuận</label>
                    </div>
                    <span class="form-message"></span>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary fs-3">Tiếp tục</button>
                    <a href="renter-register-page" class="btn btn-outline-danger fs-3"> Có sai sót, hủy bỏ </a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center">
                    <div class="copyright-content">© 2022 HQT Team. All rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- Script Bootstrap -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/valid-form.js"></script>
<script>
    Validator({
        form: "#content__form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#content__form-confirm", "Vui lòng đọc kỹ hợp đồng và tích vào ô đồng ý với mọi thỏa thuận!"),
        ],
    });
</script>
</body>

</html>
