<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>TÍnh tiền phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-calculate-fee_style/style.css">

</head>

<body>
<!-- Navbar -->
<%@include file="./components/navbar.jsp" %>

<!-- Body -->
<div class="container min-height">
    <div class="row position-relative">

        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp" %>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
            <div class="content-history pt-5 pb-5">
                <a href="./hostel.html" class="history-link">Danh sách khu trọ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a href="./room-detail.html" class="history-link">NovaLand Sky</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a href="./room-detail.html" class="history-link">Phòng 11</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Tính tiền phòng</div>
            </div>
            <div class="row mb-5">
                <div class="content-body col-12 col-lg-12 col-xxl-9 m-auto">
                    <div class="bill">
                        <h1 class="bill__title">Hóa đơn tháng 05/2022</h1>
                        <div class="row">
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Khu trọ: <span>Nova land</span></p>
                                <p class="bill__item">Phòng số: <span>11</span></p>
                                <p class="bill__item">Địa chỉ: <span>999 Hoàng Hữu Nam, phường Long Thạnh Mỹ, thành
                                            phố Thủ Đức, thành phố Hồ Chí Minh</span></p>
                                <div class="bill__consume">
                                    <div class="bill__consume-name">Điện</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>20</span>, Số mới: <span>40</span>, Tiêu thụ: <span>20</span>
                                    </div>
                                </div>
                                <div class="bill__consume">
                                    <div class="bill__consume-name">Nước</div>
                                    <div class="bill__consume-number">
                                        Số cũ: <span>20</span>, Số mới: <span>40</span>, Tiêu thụ: <span>20</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-6">
                                <p class="bill__item">Ngày tạo hóa đơn:
                                    <jsp:useBean id="now" class="java.util.Date"/>
                                    <fmt:formatDate var="dateCreate" value="${now}"
                                                    pattern="dd/MM/yyyy"/>
                                    <span>${dateCreate}</span> </p>
                                <p class="bill__item">Ngày tới hạn thanh toán:
                                    <span>
                                        <select name="expiredDate">
                                        <option value="${dateCreate}">${dateCreate}</option>
                                        <c:forEach begin="1" end="10" varStatus="loop">
                                            <c:set target="${now}" property="time" value="${now.time + 86400000}"/>
                                            <fmt:formatDate var="expiredDateOption" value="${now}"
                                                            pattern="dd/MM/yyyy"/>
                                            <option value="${expiredDateOption}">${expiredDateOption}</option>
                                        </c:forEach>
                                    </select>
                                    </span></p>
<%--                                <p class="bill__item">Trạng thái: <span class="status--no">Chưa thanh toán</span>--%>
                            </div>
                        </div>
                        <div class="bill__table">
                            <table class="table table-success table-striped table-bordered">
                                <thead>
                                <tr class="text-center">
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Đơn vị tính</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Điện</td>
                                    <td>1 Kwh</td>
                                    <td>20</td>
                                    <td>3.500 đ</td>
                                    <td>70.000 đ</td>
                                </tr>

                                <!-- Total money -->
                                <tr>
                                    <td colspan="5" class="total">Tổng tiền</td>
                                    <td class="total-money">70.000 đ</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="bill__sign">
                            <div class="row">
                                <div class="col-6">
                                    <div class="bill__sign-label">Người lập hóa đơn</div>
                                    <div class="bill__sign-name">${sessionScope.USER.accountInfo.information.fullname}</div>
                                </div>
<%--                                <div class="col-6">--%>
<%--                                    <div class="bill__sign-label">Người thanh toán</div>--%>
<%--                                    <div class="bill__sign-name"></div>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                        <div class="bill__spacer"></div>
                        <!-- Direct to room detail -->
                        <form action="calculateTotalCost" method="POST" class="bill__form d-flex justify-content-end">
                            <input type="hidden" name="roomID" value=""/>
                            <button class="btn btn-primary fs-2" type="submit">Xác nhận</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp" %>

<!-- Script Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>


</body>

</html>