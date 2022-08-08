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
    <title>Quản lý tài khoản</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/admin-list-accounts/style.css">

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
                <!-- Inactive Account -->
                <div class="row mt-5">
                    <div class="uncheck-acc col-12 col-xl-10 col-xxl-8 m-auto">
                        <div class="uncheck-acc__title">
                            Tài khoản chờ phê duyệt
                        </div>
                        <table id="uncheck-acc__table"
                               class="uncheck-acc__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark uncheck-acc__table-head">
                            <tr>
                                <th>STT</th>
                                <th>Tên tài khoản</th>
                                <th>Chi tiết</th>
                                <th>Trạng thái</th>
                                <th class="table-head__action">Hành động</th>
                            </tr>
                            </thead>
                            <tbody class="table-light uncheck-acc__table-body">
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="UncheckAccount" items="${requestScope.OWNER_LIST}">
                                <c:if test="${UncheckAccount.status eq 0}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <tr>
                                        <td>${count}</td>
                                        <td>${UncheckAccount.username}</td>
                                        <td>
                                            <button class="btn btn-outline-info fs-5"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#uncheck-acc__modal-detail-${UncheckAccount.accId}"
                                            >
                                                Chi tiết
                                            </button>
                                            <!-- Modal detail -->
                                            <div class="modal fade" id="uncheck-acc__modal-detail-${UncheckAccount.accId}" tabindex="-1"
                                                 aria-labelledby="#uncheck-acc__modal-detail-label" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="#uncheck-acc__modal-detail-label-${UncheckAccount.accId}">
                                                                Thông tin chi tiết
                                                            </h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body ps-5 pe-5">
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label-${UncheckAccount.accId}">
                                                                        Họ và tên:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.fullname eq null ? "Trống" : UncheckAccount.accountInfo.information.fullname}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Ngày sinh:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.birthday eq null ? "Trống" : UncheckAccount.accountInfo.information.birthday}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Giới tính:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.sex eq 1 ? "Nam" : "Nữ"}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Số điện thoại:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.phone eq null ? "Trống" : UncheckAccount.accountInfo.information.phone}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Email:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.email eq null ? "Trống" : UncheckAccount.accountInfo.information.email}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        CCCD/CMND:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.cccd eq null ? "Trống" : UncheckAccount.accountInfo.information.cccd}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Địa chỉ thường trú:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${UncheckAccount.accountInfo.information.address eq null ? "Trống" : UncheckAccount.accountInfo.information.address}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đồng ý</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-danger">
                                            Chưa kích hoạt
                                        </td>
                                        <td>
                                            <button class="btn btn-primary fs-5" data-bs-toggle="modal"
                                                    data-bs-target="#uncheck-acc__modal-${UncheckAccount.accId}">Kích hoạt</button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="uncheck-acc__modal-${UncheckAccount.accId}" tabindex="-1"
                                                 aria-labelledby="uncheck-acc__modal-label" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="uncheck-acc__modal-label">
                                                                Cảnh báo
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body mt-5 mb-5">
                                                            Bạn đang thực hiện kích hoạt cho tài khoản "${UncheckAccount.username}"! Hãy
                                                            đảm bảo
                                                            rằng bạn đã thực hiện đủ các thao các kiểm tra!
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-danger fs-4"
                                                                    data-bs-dismiss="modal">Hủy bỏ</button>
                                                            <form action="updateAccountStatus" method="POST">
                                                                <input type="hidden" name="owner_id" value="${UncheckAccount.accId}" />
                                                                <input type="hidden" name="status" value="${UncheckAccount.status}" />
                                                                <button type="submit" class="btn btn-success fs-4">
                                                                    Kích hoạt
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Active Account -->
                <div class="row mt-5">
                    <div class="checked-acc col-12 col-xl-10 col-xxl-8 m-auto">
                        <div class="checked-acc__title">
                            Danh sách tài khoản đang hoạt động
                        </div>
                        <table id="checked-acc__table"
                               class="checked-acc__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark checked-acc__table-head">
                            <tr>
                                <th>STT</th>
                                <th>Tên tài khoản</th>
                                <th>Chi tiết</th>
                                <th>Trạng thái</th>
                                <th class="table-head__action">Hành động</th>
                            </tr>
                            </thead>
                            <tbody class="table-light checked-acc__table-body">
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="CheckedAccount" items="${requestScope.OWNER_LIST}">
                                <c:if test="${CheckedAccount.status eq 1}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <tr>
                                        <td>${count}</td>
                                        <td>${CheckedAccount.username}</td>
                                        <td>
                                            <button
                                                    class="btn btn-outline-info fs-5"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#checked-acc__modal-detail-${CheckedAccount.accId}"
                                            >
                                                Chi tiết
                                            </button>
                                            <!-- Modal detail -->
                                            <div class="modal fade" id="checked-acc__modal-detail-${CheckedAccount.accId}" tabindex="-1"
                                                 aria-labelledby="#checked-acc__modal-detail-label-${CheckedAccount.accId}" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="#checked-acc__modal-detail-label-${CheckedAccount.accId}">
                                                                Thông tin chi tiết
                                                            </h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body ps-5 pe-5">
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Họ và tên:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.fullname eq null ? "Trống" : CheckedAccount.accountInfo.information.fullname}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Ngày sinh:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.birthday eq null ? "Trống" : CheckedAccount.accountInfo.information.birthday}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Giới tính:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.sex eq 1 ? "Nam" : "Nữ"}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Số điện thoại:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.phone eq null ? "Trống" : CheckedAccount.accountInfo.information.phone}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Email:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.email eq null ? "Trống" : CheckedAccount.accountInfo.information.email}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        CCCD/CMND:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.cccd eq null ? "Trống" : CheckedAccount.accountInfo.information.cccd}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Địa chỉ thường trú:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${CheckedAccount.accountInfo.information.address eq null ? "Trống" : CheckedAccount.accountInfo.information.address}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đồng ý</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-success">
                                            Đã kích hoạt
                                        </td>
                                        <td>
                                            <button class="btn btn-danger fs-5" data-bs-toggle="modal"
                                                    data-bs-target="#checked-acc__modal-${CheckedAccount.accId}">Khóa tài khoản</button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="checked-acc__modal-${CheckedAccount.accId}" tabindex="-1"
                                                 aria-labelledby="checked-acc__modal-label" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="checked-acc__modal-label">
                                                                Cảnh báo
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body mt-5 mb-5">
                                                            Bạn đang thực hiện khóa tài khoản "${CheckedAccount.username}"! Hãy
                                                            đảm bảo bạn khóa tài khoản này với lý do rõ ràng!
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-secondary fs-4"
                                                                    data-bs-dismiss="modal">Hủy bỏ</button>
                                                            <form action="updateAccountStatus" method="POST">
                                                                <input type="hidden" name="owner_id" value="${CheckedAccount.accId}" />
                                                                <input type="hidden" name="status" value="${CheckedAccount.status}" />
                                                                <button type="submit" class="btn btn-danger fs-4">
                                                                    Khóa tài khoản
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Banned Account -->
                <div class="row mt-5">
                    <div class="banned-acc col-12 col-xl-10 col-xxl-8 m-auto">
                        <div class="banned-acc__title">
                            Danh sách tài khoản bị khóa
                        </div>
                        <table id="banned-acc__table"
                               class="banned-acc__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark banned-acc__table-head">
                            <tr>
                                <th>STT</th>
                                <th>Tên tài khoản</th>
                                <th>Chi tiết</th>
                                <th>Trạng thái</th>
                                <th class="table-head__action">Hành động</th>
                            </tr>
                            </thead>
                            <tbody class="table-light banned-acc__table-body">
                            <c:set var="count" value="0" scope="page"/>
                            <c:forEach var="BannedAccount" items="${requestScope.OWNER_LIST}">
                                <c:if test="${BannedAccount.status eq -1}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <tr>
                                        <td>${count}</td>
                                        <td>${BannedAccount.username}</td>
                                        <td>
                                            <button
                                                    class="btn btn-outline-info fs-5"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#banned-acc__modal-detail-${BannedAccount.accId}"
                                            >
                                                Chi tiết
                                            </button>
                                            <!-- Modal detail -->
                                            <div class="modal fade" id="banned-acc__modal-detail-${BannedAccount.accId}" tabindex="-1"
                                                 aria-labelledby="#banned-acc__modal-detail-label-${BannedAccount.accId}" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="#banned-acc__modal-detail-label-${BannedAccount.accId}">
                                                                Thông tin chi tiết
                                                            </h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body ps-5 pe-5">
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Họ và tên:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.fullname eq null ? "Trống" : BannedAccount.accountInfo.information.fullname}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Ngày sinh:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.birthday eq null ? "Trống" : BannedAccount.accountInfo.information.birthday}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Giới tính:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.sex eq 1 ? "Nam" : "Nữ"}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Số điện thoại:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.phone eq null ? "Trống" : BannedAccount.accountInfo.information.phone}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Email:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.email eq null ? "Trống" : BannedAccount.accountInfo.information.email}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        CCCD/CMND:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.cccd eq null ? "Trống" : BannedAccount.accountInfo.information.cccd}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row pt-3 pb-3">
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-label">
                                                                        Địa chỉ thường trú:
                                                                    </div>
                                                                </div>
                                                                <div class="col-6">
                                                                    <div class="member-detail-modal-content">
                                                                            ${BannedAccount.accountInfo.information.address eq null ? "Trống" : BannedAccount.accountInfo.information.address}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đồng ý</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-warning">
                                            Đã bị khóa
                                        </td>
                                        <td>
                                            <button class="btn btn-success fs-5" data-bs-toggle="modal"
                                                    data-bs-target="#banned-acc__modal-${BannedAccount.accId}">Mở khóa tài khoản</button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="banned-acc__modal-${BannedAccount.accId}" tabindex="-1"
                                                 aria-labelledby="banned-acc__modal-label" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="banned-acc__modal-label">
                                                                Cảnh báo
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body mt-5 mb-5">
                                                            Bạn đang thực hiện mở khóa tài khoản "${BannedAccount.username}"! Hãy
                                                            đảm bảo bạn mở khóa tài khoản này với lý do rõ ràng!
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-danger fs-4"
                                                                    data-bs-dismiss="modal">Hủy bỏ</button>
                                                            <form action="updateAccountStatus" method="POST">
                                                                <input type="hidden" name="owner_id" value="${BannedAccount.accId}" />
                                                                <input type="hidden" name="status" value="${BannedAccount.status}" />
                                                                <button type="submit" class="btn btn-success fs-4">
                                                                    Mở khóa tài khoản
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
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
<script src="./assets/js/toast-alert.js"></script>
<script>
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
</script>
<script>
    $('#uncheck-acc__table').DataTable();
    $('#checked-acc__table').DataTable();
    $('#banned-acc__table').DataTable();
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>
