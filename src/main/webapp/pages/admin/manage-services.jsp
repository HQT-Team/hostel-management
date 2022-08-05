<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Quản lý dịch vụ</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/manage-services/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

</head>

<body class="${ requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <div id="preloader">
        <div class="dots">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</c:if>

<div class="app">

    <!-- Navbar -->
    <%@include file="./components/navbar.jsp"%>

    <!-- Body -->
    <div class="container">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="./components/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- List Services -->
                <div class="row mt-5">
                    <div class="service col-12 col-xl-10 col-xxl-8 m-auto">
                        <div class="service-header__wrapper">
                            <div class="service__title">
                                Các dịch vụ hiện có ở hệ thống
                            </div>
                            <button class="btn btn-outline-dark fs-4" data-bs-toggle="modal"
                                    data-bs-target="#create-service-modal">
                                Tạo mới
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="create-service-modal" tabindex="-1"
                                 aria-labelledby="create-service-modal-label" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="create-service-modal-label">
                                                Tạo mới dịch vụ
                                            </h5>
                                            <button type="button" class="btn-close"
                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="admin-create-new-service" method="POST" id="create-service-form">
                                            <div class="modal-body mt-5 mb-5">
                                                <div class="row">
                                                    <div class="col-8">
                                                        <div class="form-group">
                                                            <label for="create-service-name" class="form-label">Tên dịch vụ: <span>*</span></label>
                                                            <input type="text" name="serviceName" id="create-service-name" class="form-control"
                                                                   placeholder="Nhập tên dịch vụ">
                                                            <div class="form-message" style="margin-bottom: 12px;"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-4">
                                                        <div class="form-group">
                                                            <label for="create-service-unit" class="form-label">Đơn vị <span>*</span></label>
                                                            <select name="serviceUnit" id="create-service-unit" class="form-control">
                                                                <option value="phòng">Phòng</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer justify-content-between">
                                                <button type="button" class="btn btn-danger fs-4"
                                                        data-bs-dismiss="modal">Hủy bỏ</button>
                                                <button type="submit" class="btn btn-success fs-4">
                                                    Tạo mới
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table id="service__table"
                               class="service__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark service__table-head">
                            <tr>
                                <th>STT</th>
                                <th>Tên dịch vụ</th>
                                <th>Đơn vị</th>
                                <th class="table-head__action">Hành động</th>
                            </tr>
                            </thead>
                            <tbody class="table-light service__table-body">
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="service" items="${requestScope.servicesList}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td>${count}</td>
                                    <td>${service.serviceName}</td>
                                    <td>${service.unit}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${service.serviceID eq 1 ||
                                                            service.serviceID eq 2 ||
                                                            service.serviceID eq 3 ||
                                                            service.serviceID eq 4 ||
                                                            service.serviceID eq 5 ||
                                                            service.serviceID eq 6 ||
                                                            service.serviceID eq 7}">
                                                -
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-primary fs-5" data-bs-toggle="modal"
                                                        data-bs-target="#service__modal-${service.serviceID}">Chỉnh sửa</button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="service__modal-${service.serviceID}" tabindex="-1"
                                                     aria-labelledby="service__modal-label-${service.serviceID}" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="service__modal-label-${service.serviceID}">
                                                                    Cập nhật dịch vụ
                                                                </h5>
                                                                <button type="button" class="btn-close"
                                                                        data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <form id="update-service-form-${service.serviceID}" action="admin-update-service" method="POST">
                                                                <input type="hidden" name="serviceId" value="${service.serviceID}" />
                                                                <div class="modal-body mt-5 mb-5">
                                                                    <div class="row">
                                                                        <div class="col-8">
                                                                            <div class="form-group">
                                                                                <label for="update-service-name-${service.serviceID}" class="form-label">Tên dịch vụ: <span>*</span></label>
                                                                                <input type="text" name="serviceName" id="update-service-name-${service.serviceID}" class="form-control"
                                                                                       placeholder="Nhập tên dịch vụ" value="${service.serviceName}">
                                                                                <div class="form-message" style="margin-bottom: 12px;"></div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-4">
                                                                            <div class="form-group">
                                                                                <label for="update-service-unit" class="form-label">Đơn vị <span>*</span></label>
                                                                                <select name="serviceUnit" id="update-service-unit" class="form-control">
                                                                                    <option value="phòng" ${service.unit eq "phòng" ? "selected" : ""}>Phòng</option>
                                                                                    <option value="Kwh" ${service.unit eq "Kwh" ? "selected" : ""}>Kwh</option>
                                                                                    <option value="m3" ${service.unit eq "m3" ? "selected" : ""}>m3</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer justify-content-between">
                                                                    <button type="button" class="btn btn-danger fs-4"
                                                                            data-bs-dismiss="modal">Hủy bỏ</button>
                                                                    <button type="submit" class="btn btn-success fs-4">
                                                                        Cập nhật
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./components/footer.jsp"%>

    <!-- Toast element -->
    <div id="toast">&nbsp;</div>

</div>

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
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>

<script>
    // Valid form
    Validator({
        form: "#create-service-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#create-service-name", "Vui lòng nhập tên của dịch vụ"),
        ],
    });

    // Valid form
    <c:forEach var="service" items="${requestScope.servicesList}">
    <c:choose>
        <c:when test="${service.serviceID eq 1 ||
                        service.serviceID eq 2 ||
                        service.serviceID eq 3 ||
                        service.serviceID eq 4 ||
                        service.serviceID eq 5 ||
                        service.serviceID eq 6 ||
                        service.serviceID eq 7}">
        </c:when>
        <c:otherwise>
            Validator({
                form: "#update-service-form-${service.serviceID}",
                formGroupSelector: ".form-group",
                errorSelector: ".form-message",
                rules: [
                    Validator.isRequired("#update-service-name-${service.serviceID}", "Vui lòng nhập tên của dịch vụ")
                ],
            });
        </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq true}">
            toast({
                title: 'Thành công',
                message: '${requestScope.RESPONSE_MSG.content}',
                type: 'success',
                duration: 5000
            });
        </c:when>
        <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq false}">
            toast({
                title: 'Lỗi',
                message: '${requestScope.RESPONSE_MSG.content}',
                type: 'error',
                duration: 5000
            });
        </c:when>
    </c:choose>

    $('#service__table').DataTable({
        "order": [],
    });
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>
