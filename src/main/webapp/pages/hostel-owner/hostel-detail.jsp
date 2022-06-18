<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Chi tiết khu trọ</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/hostel_detail_style/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

</head>

<body>
<!-- Navbar -->
<%@include file="components/navbar.jsp"%>

<!-- Body -->
<div class="container">
    <div class="row position-relative">
        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="components/sidebar.jsp"%>
        </div>

        <!-- Content -->
        <c:set var="hostelInformation" value="${requestScope.hostel}"/>
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
            <!-- History link -->
            <div class="content-history">
                <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">${hostelInformation.hostelName}</div>
            </div>
            <div class="content-body">
                <div class="hostel-header">
                    <!-- Hostel information -->
                    <div class="hostel-infor">
                        <h2 class="hostel-name">${hostelInformation.hostelName}</h2>
                        <div class="hostel-address">${hostelInformation.address}, ${hostelInformation.district.split('-')[1]}, ${hostelInformation.city.split('-')[1]}</div>
                    </div>
                    <div class="hostel-actions">
                        <!-- Add infrastructure button -->
                        <button data-bs-toggle="modal" data-bs-target="#addServiceModal"
                                class="hostel-actions__btn hostel-actions__btn-infrastructure">
                            Thêm dịch vụ
                        </button>
                        <!-- Add room button -->
                        <a href="addRoom?hostelID=${hostelInformation.hostelID}" class="hostel-actions__btn hostel-actions__btn-room">
                            Thêm phòng
                        </a>
                    </div>
                </div>
                <div class="hostel-detail">
                    <div class="row">
                        <!-- List rooms table -->
                        <div class="col-12 col-md-12 col-xl-8">
                            <div class="hostel-rooms">
                                <div class="rooms-header">
                                    <div class="rooms-title">Danh sách phòng</div>
                                    <div class="rooms-total">Tổng: <span>${requestScope.roomQuantity}</span></div>
                                </div>
                                <table id="rooms-table"
                                       class="mt-4 mb-4 table table-hover table-bordered table-striped rooms-table">
                                    <thead class="table-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên</th>
                                        <th>Số lượng thành viên</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-light">
                                    <c:set var="count" value="0"/>
                                    <c:forEach var="room" items="${requestScope.roomList}">
                                        <c:set var="count" value="${count+1}"/>
                                        <tr>
                                            <td>${count}</td>
                                            <td>${room.roomNumber}</td>
                                            <td>${requestScope.quantityMembers.get(count-1)}/${room.capacity}</td>
                                            <td>
                                                <!-- Room detail link -->
                                                <a href="roomDetail?roomID=${room.roomId}" class="room-detail-link">Chi tiết</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- Services table -->
                        <div class="col-12 col-md-6 col-xl-4 mt-4 mt-xl-0">
                            <div class="hostel-services">
                                <div class="services-header">
                                    <div class="service-title">Giá dịch vụ</div>
                                    <button class="service-update-btn" data-bs-toggle="modal"
                                            data-bs-target="#updateServicesModel">Cập nhật
                                    </button>
                                </div>
                                <div class="services-date">
                                    Áp dụng từ:
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.serviceInfo[0].validDate}" var="validDate" />
                                    <span><fmt:formatDate pattern = "dd/MM/yyyy" value="${validDate}" /></span>
                                </div>
                                <c:forEach var="serviceList" items="${requestScope.serviceInfo}">
                                    <div class="service-group">
                                        <div class="service-name">${serviceList.serviceName}</div>
                                        <div class="service-price">
                                            <span>
                                                <fmt:formatNumber value="${serviceList.servicePrice}" type="currency" />
                                            </span>/${serviceList.unit}</div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="components/footer.jsp"%>


<!-- Add service modal -->
<div class="modal fade" id="addServiceModal" tabindex="-1" aria-labelledby="addServiceModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title addServiceModal-label" id="addServiceModalLabel">Thêm dịch vụ</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="add-new-service" method="post" class="custom-form">
                <input type="hidden" name="hostel-id" value="${sessionScope.hostel.hostelID}"/>
                <div class="modal-body addServiceModal-content">
                    <div class="form-group">
                        <label for="service-id" class="form-label">Tên dịch vụ</label>
                        <select name="service-id" id="service-id" class="form-control">
                            <option value="">Chọn loại dịch vụ</option>
                            <c:forEach var="services" items="${requestScope.services}">
                                <option value="${services.serviceID}">${services.serviceName}</option>
                            </c:forEach>
                        </select>
                        <span class="form-message"></span>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <div class="form-group">
                                <label for="service-price" class="form-label">Giá</label>
                                <input type="number" name="service-price" id="service-price" class="form-control"
                                       placeholder="Nhập giá. VD: 1000">
                                <div class="form-message"></div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label for="service-unit" class="form-label">Đơn vị tính</label>
                                <input type="text" disabled name="service-unit" id="service-unit"
                                       class="form-control" value="phòng">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer addServiceModal-action">
                    <button type="button" class="btn back-btn" data-bs-dismiss="modal">
                        Hủy bỏ
                    </button>
                    <button type="submit" class="btn create-btn">
                        Tạo mới
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Waring update price of services modal! Don't update this! -->
<div class="modal fade" id="updateServicesModel" tabindex="-1" aria-labelledby="updateServicesModelLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title updateServicesModel-label" id="updateServicesModelLabel">Cảnh báo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body updateServicesModel-content">
                Bạn nên gửi thông báo đến người thuê trước khi cập nhật giá dịch vụ mới để giảm thiểu các trường hợp
                khiếu nại không mong muốn từ người thuê!
            </div>
            <div class="modal-footer updateServicesModel-action">
                <button type="button" class="btn btn-secondary back-btn" data-bs-dismiss="modal">Quay lại</button>
                <button data-bs-toggle="modal" data-bs-target="#updateServiceInputModal"
                        class="btn btn-primary continue-btn">Đã rõ và tiếp tục
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Update price of services modal -->
<div class="modal fade" id="updateServiceInputModal" tabindex="-1" aria-labelledby="updateServiceInputModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title updateServiceInputModal-label" id="updateServiceInputModalLabel">
                    Cập nhật dịch vụ
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="update-services" method="post" class="custom-form">
                <input type="hidden" name="hostel-id" value="${requestScope.hostel.hostelID}" />
                <div class="modal-body updateServiceInputModal-content" style="max-height: 60vh; overflow-y: auto;">
                    <div class="container">
                        <!-- Label - Dont't update this! -->
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group">
                                    <label for="service-name" class="form-label">Tên dịch vụ</label>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="form-label">Giá</label>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="form-label">Đơn vị tính</label>
                                </div>
                            </div>
                        </div>
                        <!-- Each service -->
                        <c:forEach var="serviceList" items="${requestScope.serviceInfo}">
                        <input type="hidden" name="update-service-id" value="${serviceList.serviceID}" />
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group">
                                    <input type="text" id="service-name" name="service-name" value="${serviceList.serviceName}" disabled
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <input type="number" id="update-service-price" name="update-service-price" class="form-control" value="${serviceList.servicePrice}"
                                           placeholder="Nhập giá. VD: 1000">
                                    <div class="form-message"></div>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <input type="text" disabled class="form-control"
                                           value="đ/${serviceList.unit}">
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="modal-footer updateServiceInputModal-action">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Hủy bỏ
                    </button>
                    <button type="submit" class="btn btn-primary">
                        Cập nhật
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Toast element -->
<div id="toast">&nbsp;</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Simple Datatable JS -->
<script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        // Initial datatable
        $('#rooms-table').DataTable();
    });
</script>
<script src="./assets/js/toast-alert.js"></script>
<script>
    <c:choose>
    <c:when test="${requestScope.RESPONSE_MSG.status eq true}">
    toast({
        title: 'Thành công',
        message: '${requestScope.RESPONSE_MSG.content}',
        type: 'success',
        duration: 10000
    });
    </c:when>
    <c:when test="${requestScope.ERROR eq false}">
    toast({
        title: 'Lỗi',
        message: '${requestScope.RESPONSE_MSG.content}',
        type: 'error',
        duration: 10000
    });
    </c:when>
    </c:choose>
</script>
</body>

</html>