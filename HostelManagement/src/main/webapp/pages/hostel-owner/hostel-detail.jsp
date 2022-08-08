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

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <div id="preloader">
        <div class="dots">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</c:if>

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
        <c:set var="hostelInformation" value="${sessionScope.hostel}"/>
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
            <!-- History link -->
            <div class="content-history">
                <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">${hostelInformation.hostelName}</div>
            </div>
            <div class="content-body mb-5">
                <div class="hostel-header">
                    <!-- Hostel information -->
                    <div class="hostel-infor">
                        <h2 class="hostel-name">${hostelInformation.hostelName}</h2>
                        <div class="hostel-address">${hostelInformation.address}, ${hostelInformation.district.split('-')[1]}, ${hostelInformation.city.split('-')[1]}</div>
                    </div>
                    <div class="hostel-actions">
                        <!-- Add service button -->
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
                                        <th>Phòng số</th>
                                        <th>Trạng thái</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-light">
                                    <c:set var="count" value="0"/>
                                    <c:forEach var="room" items="${requestScope.roomList}">
                                        <c:set var="count" value="${count+1}"/>
                                        <tr>
                                            <td>${count}</td>
                                            <td>${room.roomNumber}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${room.roomStatus eq 0}">
                                                        <span class="text-danger">Đã có người thuê</span>
                                                    </c:when>
                                                    <c:when test="${room.roomStatus eq 1}">
                                                        <span class="text-success">Sẵn sàng cho thuê</span>
                                                    </c:when>
                                                    <c:when test="${room.roomStatus eq -1}">
                                                        <span class="text-warning">Đang làm hợp đồng</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <!-- Room detail link -->
                                                <a href="roomDetail?roomID=${room.roomId}" class="room-detail-link">Xem chi tiết</a>
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
                                                <fmt:formatNumber value="${serviceList.servicePrice}" type="currency" currencySymbol="VNĐ"/>/${serviceList.unit}</div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-5">
                <div class="upload-file-section col-12 col-md-8 col-lg-7 col-xl-6">
                    <div class="upload-wrapper">
                        <h1>Thêm phòng trọ bằng file Excel</h1>
                        <c:choose>
                            <c:when test="${requestScope.SUCCESS ne null}">
                                <h3 class="success mb-4">${requestScope.SUCCESS}</h3>
                            </c:when>
                            <c:when test="${requestScope.ERROR ne null}">
                                <h3 class="failed mb-4">${requestScope.ERROR}</h3>
                            </c:when>
                        </c:choose>
                        <c:if test="${requestScope.SUCCESS eq null}">
                            <form action="RoomFileUpLoadServlet" method="POST" enctype="multipart/form-data">
                                <input type="hidden" value="${hostelInformation.hostelID}" name="hostelID">
                                <input class="form-control" type="file" name="file" value="${requestScope.file}"
                                       accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                <button class="btn btn-primary fs-3" type="submit">Tải file lên hệ thống</button>
                            </form>
                        </c:if>
                        <c:if test="${requestScope.SUCCESS ne null}">
                            <form action="import-room" method="GET">
                                <input type="hidden" value="${hostelInformation.hostelID}" name="hostelID">
                                <input type="hidden" value="${requestScope.file}" name="fileName">
                                <button class="btn btn-primary fs-3" type="submit">
                                    Thêm phòng trọ từ tệp excel đã tải lên
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <c:if test="${requestScope.SUCCESS_IMPORT ne null || requestScope.ERROR_IMPORT ne null}">
                    <div class="upload-file-section col-12 col-md-4 col-lg-5 col-xl-6">
                        <div class="upload-wrapper">
                            <h1>Kết quả thêm phòng</h1>
                            <c:forEach var="e" items="${requestScope.SUCCESS_IMPORT}">
                                <h3 class="success mb-4 fw-normal">${e.content}</h3>
                            </c:forEach>
                            <c:forEach var="e" items="${requestScope.ERROR_IMPORT}">
                                <h3 class="failed mb-4 fw-normal">${e.content}</h3>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="components/footer.jsp"%>

<!-- Push notification element -->
<div id="push-noti"></div>

<!-- Add service modal -->
<div class="modal fade" id="addServiceModal" tabindex="-1" aria-labelledby="addServiceModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title addServiceModal-label" id="addServiceModalLabel">Thêm dịch vụ</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="add-new-service" method="post" class="custom-form" id="add-new-service-form">
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
                        <span class="form-message" style="margin-bottom: 12px;"></span>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <div class="form-group">
                                <label for="service-price" class="form-label">Giá</label>
                                <input type="number" name="service-price" id="service-price" class="form-control"
                                       placeholder="Nhập giá. VD: 1000">
                                <div class="form-message" style="margin-bottom: 12px;"></div>
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
            <form action="update-services" method="post" class="custom-form" id="update-services-form">
                <input type="hidden" name="hostel-id" value="${requestScope.hostel.hostelID}" />
                <div class="modal-body updateServiceInputModal-content" style="max-height: 60vh; overflow-y: auto;">
                    <div class="container">
                        <!-- Label - Dont't update this! -->
                        <div class="row mb-3">
                            <div class="col-4">
                                <div class="form-group">
                                    <label for="service-name" class="form-label">Tên dịch vụ</label>
                                </div>
                            </div>
                            <div class="col-5">
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
                        <div class="row form-group mb-4">
                            <div class="col-4">
                                    <input type="text" id="service-name" name="service-name" value="${serviceList.serviceName}" disabled
                                           class="form-control">
                            </div>
                            <div class="col-5">
                                    <input type="number" id="update-service-price-${serviceList.serviceID}" name="update-service-price" class="form-control" value="${serviceList.servicePrice}"
                                           placeholder="Nhập giá. VD: 1000">
                            </div>
                            <div class="col-3">
                                    <input type="text" disabled class="form-control"
                                           value="VNĐ/${serviceList.unit}">
                            </div>
                            <span class="form-message"></span>
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
<script src="./assets/js/valid-form.js"></script>
<!-- Simple Datatable JS -->
<script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/sendWebsocket.js"></script>
<script src="./assets/js/receiveWebsocket.js"></script>
<script>
    $(document).ready(function () {
        // Initial datatable
        $('#rooms-table').DataTable({
            columnDefs: [
                { orderable: false, targets: 0 },
                { orderable: false, targets: 3 },
            ],
            "order": [],
        });
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
                duration: 5000
            });
        </c:when>
        <c:when test="${requestScope.ERROR eq false}">
            toast({
                title: 'Lỗi',
                message: '${requestScope.RESPONSE_MSG.content}',
                type: 'error',
                duration: 5000
            });
        </c:when>
    </c:choose>
</script>
<script>
    Validator({
        form: "#add-new-service-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#service-id", "Vui lòng chọn loại dịch vụ cần thêm mới vào khu trọ!"),
            Validator.isRequired("#service-price", "Vui lòng nhập giá tiền của dịch vụ!"),
            Validator.minNumber("#service-price", 1000, "Vui lòng nhập giá tối thiểu là 1000"),
            Validator.isInteger("#service-price", "Vui lòng nhập đúng giá trị số nguyên"),
        ]
    });

    Validator({
        form: "#update-services-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            <c:forEach var="serviceList" items="${requestScope.serviceInfo}">
                Validator.isRequired("#update-service-price-${serviceList.serviceID}", "Vui lòng nhập giá dịch vụ!"),
                Validator.minNumber("#update-service-price-${serviceList.serviceID}", 0, "Vui lòng nhập giá tối thiểu là 0!"),
                Validator.isInteger("#update-service-price-${serviceList.serviceID}", "Vui lòng nhập đúng giá trị số nguyên"),
            </c:forEach>
        ]
    });
</script>

<script type="text/javascript">
    // Send
    <c:if test="${requestScope.RESPONSE_MSG.status == true &&  'Cập nhật dịch vụ thành công!' eq requestScope.RESPONSE_MSG.content }">
    const params = new Object();
    params.sender = "hostel_owner";
    params.receiver = "hostel";
    params.hostel_receiver_id = "${requestScope.HOSTEL_ID}";
    params.account_receiver_id = null;
    params.messages = "Chủ trọ đã gửi một thông báo mới. Vui lòng kiểm tra!";
    sendToWebSocket(params);
    </c:if>

    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function(){
        receiveWebsocket.disconnectWebSocket();
    };
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>