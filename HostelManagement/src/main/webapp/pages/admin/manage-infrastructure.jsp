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
    <title>Quản lý cơ sở hạ tầng</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/manage-infrastructures/style.css">

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
                <!-- List Infrastructure -->
                <div class="row mt-5">
                    <div class="infrastructure col-12 col-xl-10 col-xxl-8 m-auto">
                        <div class="infrastructure-header__wrapper">
                            <div class="infrastructure__title">
                                Các cơ sở vật chất hiện có trong hệ thống
                            </div>
                            <button class="btn btn-outline-dark fs-4" data-bs-toggle="modal"
                                    data-bs-target="#create-infrastructure-modal">
                                Tạo mới
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="create-infrastructure-modal" tabindex="-1"
                                 aria-labelledby="create-infrastructure-modal-label" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="create-infrastructure-modal-label">
                                                Tạo mới
                                            </h5>
                                            <button type="button" class="btn-close"
                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="admin-create-infrastructure" method="POST" id="create-infrastructure-form">
                                            <div class="modal-body mt-5 mb-5">
                                                <div class="form-group">
                                                    <label for="create-infrastructure-name" class="form-label">Tên cơ sở hạ tầng: <span>*</span></label>
                                                    <input type="text" name="infrastructureName" id="create-infrastructure-name" class="form-control"
                                                           placeholder="Nhập tên cơ sở hạ tầng">
                                                    <div class="form-message"></div>
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
                        <table id="infrastructure__table"
                               class="infrastructure__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark infrastructure__table-head">
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th class="table-head__action">Hành động</th>
                            </tr>
                            </thead>
                            <tbody class="table-light infrastructure__table-body">
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="infrastructure" items="${requestScope.infrastructuresList}">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td>${count}</td>
                                    <td>${infrastructure.infrastructureName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${infrastructure.idInfrastructureItem eq 1 ||
                                                            infrastructure.idInfrastructureItem eq 2 ||
                                                            infrastructure.idInfrastructureItem eq 3 ||
                                                            infrastructure.idInfrastructureItem eq 4}">
                                                -
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-primary fs-5" data-bs-toggle="modal"
                                                        data-bs-target="#infrastructure__modal-${infrastructure.idInfrastructureItem}">Chỉnh sửa</button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="infrastructure__modal-${infrastructure.idInfrastructureItem}" tabindex="-1"
                                                     aria-labelledby="infrastructure__modal-label-${infrastructure.idInfrastructureItem}" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="infrastructure__modal-label-${infrastructure.idInfrastructureItem}">
                                                                    Cập nhật dịch vụ
                                                                </h5>
                                                                <button type="button" class="btn-close"
                                                                        data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <form id="update-infrastructure-form-${infrastructure.idInfrastructureItem}" action="admin-update-infrastructure" method="POST">
                                                                <input type="hidden" name="infrastructureId" value="${infrastructure.idInfrastructureItem}" />
                                                                <div class="modal-body mt-5 mb-5">
                                                                    <div class="form-group">
                                                                        <label for="update-infrastructure-name-${infrastructure.idInfrastructureItem}" class="form-label">Tên cơ sở hạ tầng: <span>*</span></label>
                                                                        <input type="text" name="infrastructureName" id="update-infrastructure-name-${infrastructure.idInfrastructureItem}" class="form-control"
                                                                               placeholder="Nhập tên cơ sở hạ tầng" value="${infrastructure.infrastructureName}">
                                                                        <div class="form-message"></div>
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
        form: "#create-infrastructure-form",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#create-infrastructure-name", "Vui lòng nhập tên cơ sở hạ tầng"),
        ],
    });

    // Valid form
    <c:forEach var="infrastructure" items="${requestScope.infrastructuresList}">
    <c:choose>
        <c:when test="${infrastructure.idInfrastructureItem eq 1 ||
                        infrastructure.idInfrastructureItem eq 2 ||
                        infrastructure.idInfrastructureItem eq 3 ||
                        infrastructure.idInfrastructureItem eq 4}">
        </c:when>
        <c:otherwise>
            Validator({
                form: "#update-infrastructure-form-${infrastructure.idInfrastructureItem}",
                formGroupSelector: ".form-group",
                errorSelector: ".form-message",
                rules: [
                    Validator.isRequired("#update-infrastructure-name-${infrastructure.idInfrastructureItem}", "Vui lòng nhập tên cơ sở hạ tầng")
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

    $('#infrastructure__table').DataTable({
        "order": [],
    });
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>
