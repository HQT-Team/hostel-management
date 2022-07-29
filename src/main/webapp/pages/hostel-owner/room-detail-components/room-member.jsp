<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="room-members">
        <div class="members-header">
            <div class="members-title">Danh sách thành viên</div>
            <c:if test="${(sessionScope.room.roomStatus == 0) && (requestScope.listRoommatesInfo.size() < sessionScope.room.capacity)}">
                <div class="members-actions">
                    <button class="add-member-btn" data-bs-toggle="modal"
                            data-bs-target="#add-new-member-modal">Thêm thành viên
                    </button>
                    <!-- Start Add new member modal -->
                    <div class="modal fade" id="add-new-member-modal" tabindex="-1"
                         aria-labelledby="add-new-member-modal-label" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="add-new-member-modal-label">
                                        Cập nhật thông tin thành viên
                                    </h5>
                                    <button type="button" class="btn-close"
                                            data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form action="ow-add-new-roommate" method="POST" id="add-new-member-form"
                                      class="custom-form add-new-member-form">
                                    <input type="hidden" name="roomID" value="${sessionScope.room.roomId}" />
                                    <input type="hidden" name="room-capacity" value="${sessionScope.room.capacity}" />
                                    <input type="hidden" name="account-renter-id" value="${requestScope.renterAccount.accId}" />
                                    <div class="modal-body ps-5 pe-5">
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__name" class="form-label">
                                                        Họ và tên: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <!-- FullName -->
                                                    <input type="text" name="full-name" id="add-new-member__name" class="form-control m-0"
                                                           placeholder="Nhập tên thành viên">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__dob" class="form-label">
                                                        Ngày sinh: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <!-- Date of Birth -->
                                                    <input type="date" name="dob" id="add-new-member__dob"
                                                           class="form-control m-0" placeholder="Nhập ngày sinh">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__gender" class="form-label">
                                                        Giới tính: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <select name="gender" class="form-control m-0" id="add-new-member__gender">
                                                        <option value="1">Nam</option>
                                                        <option value="0">Nữ</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__phone" class="form-label">
                                                        Số điện thoại: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <!-- Phone number -->
                                                    <input type="text" name="phone-number" class="form-control m-0"
                                                           id="add-new-member__phone" placeholder="Nhập số điện thoại">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__email" class="form-label">
                                                        Email: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <!-- Email -->
                                                    <input type="text" name="email" class="form-control m-0"
                                                           id="add-new-member__email" placeholder="Nhập email">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__cccd" class="form-label">
                                                        CCCD/CMND: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <input type="text" name="cccd" id="add-new-member__cccd"
                                                           class="form-control m-0" placeholder="Nhập số CMND/CCCD">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__address" class="form-label">
                                                        Địa chỉ thường trú: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <input type="text" name="address" id="add-new-member__address"
                                                           class="form-control m-0" placeholder="Nhập địa chỉ thường trú">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__parent-name" class="form-label">
                                                        Họ và tên cha/mẹ: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <input type="text" name="parent-name" id="add-new-member__parent-name"
                                                           class="form-control m-0" placeholder="Nhập họ/tên cha hoặc mẹ">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="row align-items-center">
                                                <div class="col-6">
                                                    <label for="add-new-member__parent-phone" class="form-label">
                                                        Số điện thoại cha/mẹ: <span>*</span>
                                                    </label>
                                                </div>
                                                <div class="col-6">
                                                    <input type="text" name="parent-phone" id="add-new-member__parent-phone"
                                                           class="form-control m-0" placeholder="Nhập số điện thoại cha/mẹ">
                                                </div>
                                            </div>
                                            <span class="form-message mt-3"></span>
                                        </div>
                                    </div>
                                    <div class="modal-footer justify-content-between">
                                        <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary">
                                            Thêm thành viên
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- End Add new member modal -->
                </div>
            </c:if>
        </div>
        <c:choose>
            <c:when test="${(sessionScope.room.roomStatus ne 1) && (requestScope.listRoommatesInfo.size() > 0)}">
                <table id="members-table"
                       class="mt-4 mb-4 table table-hover table-bordered table-striped members-table">
                    <thead class="table-dark">
                    <tr class="text-center">
                        <th class="mobile-display-none">STT</th>
                        <th>Họ và tên</th>
                        <th class="mobile-display-none" style="width: 76px;">Giới tính</th>
                        <th class="mobile-display-none tablet-display-none">Địa chỉ</th>
                        <th class="mobile-display-none">Số điện thoại</th>
                        <th class="table-col-action"></th>
                    </tr>
                    </thead>
                    <tbody class="table-light">
                    <c:set var="count" value="0" />
                    <c:forEach var="roommateList" items="${requestScope.listRoommatesInfo}">
                        <c:set var="count" value="${count + 1}"/>
                        <tr>
                            <td class="mobile-display-none">${count}</td>
                            <td>${roommateList.information.fullname}</td>
                            <td class="mobile-display-none">
                                    ${roommateList.information.sex eq 1 ? "Nam" : "Nữ"}
                            </td>
                            <td class="mobile-display-none tablet-display-none">${roommateList.information.address}</td>
                            <td class="mobile-display-none">${roommateList.information.phone}</td>
                            <td class="members-detail-actions">
                                <!-- member detail link -->
                                <div class="row">
                                    <div class="col-12">
                                        <!-- Start member detail button -->
                                        <button class="member-detail-btn" data-bs-toggle="modal"
                                                data-bs-target="#member-detail-modal-${roommateList.roommateID}">
                                            Chi tiết
                                        </button>
                                        <!-- Start modal member detail -->
                                        <div class="modal fade" id="member-detail-modal-${roommateList.roommateID}"
                                             tabindex="-1"
                                             aria-labelledby="member-detail-modal-label"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="member-detail-modal-label">
                                                            Thông tin chi tiết
                                                        </h5>
                                                        <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
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
                                                                    ${roommateList.information.fullname}
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
                                                                    ${roommateList.information.birthday}
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
                                                                        ${roommateList.information.sex eq 1 ? "Nam" : "Nữ"}
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
                                                                        ${roommateList.information.phone}
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
                                                                        ${roommateList.information.email}
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
                                                                        ${roommateList.information.cccd}
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
                                                                        ${roommateList.information.address}
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row pt-3 pb-3">
                                                            <div class="col-6">
                                                                <div class="member-detail-modal-label">
                                                                    Họ tên cha hoặc mẹ:
                                                                </div>
                                                            </div>
                                                            <div class="col-6">
                                                                <div class="member-detail-modal-content">
                                                                        ${roommateList.parentName}
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
                                                                        ${roommateList.parentPhone}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer justify-content-between">
                                                        <div>
                                                            <!-- Start update member information button -->
                                                            <button type="button" class="btn btn-danger"
                                                                    data-bs-toggle="modal"
                                                                    data-bs-target="#update-member-infor-modal-${roommateList.roommateID}">
                                                                Cập nhật
                                                            </button>
                                                            <!-- End update member information button -->
                                                            <!-- Start delete member button -->
                                                            <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#remove-member-modal">
                                                                Xóa thành viên
                                                            </button>
                                                            <!-- End delete member button -->
                                                        </div>

                                                        <button type="button"
                                                                class="btn btn-primary"
                                                                data-bs-dismiss="modal">
                                                            Đồng ý
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End modal member detail -->
                                        <!-- Start Remove member modal -->
                                        <div class="modal fade" id="remove-member-modal" tabindex="-1" aria-labelledby="remove-member-modal-label" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="remove-member-modal-label">
                                                            Cảnh báo
                                                        </h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body pt-5 pb-5">
                                                        Bạn có chắc chắn là muốn xóa thành viên này ra khỏi phòng?
                                                    </div>
                                                    <div class="modal-footer justify-content-between">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</button>
                                                        <form action="delete-roommate" method="POST">
                                                            <input type="hidden" name="renter-account-id" value="${requestScope.renterAccount.accId}">
                                                            <input type="hidden" name="roommate-id" value="${roommateList.roommateID}">
                                                            <button type="submit" class="btn btn-danger">
                                                                Đồng ý
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Remove member modal -->
                                        <!-- Start Update member information modal -->
                                        <div class="modal fade" id="update-member-infor-modal-${roommateList.roommateID}"
                                             tabindex="-1"
                                             aria-labelledby="update-member-infor-modal-label"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="update-member-infor-modal-label">
                                                            Cập nhật thông tin thành viên
                                                        </h5>
                                                        <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <form action="ow-update-roommate" method="POST"
                                                          id="update-member-infor-form"
                                                          class="custom-form update-member-infor-form">
                                                        <input type="hidden" name="roomID" value="${sessionScope.room.roomId}" />
                                                        <input type="hidden" name="roommate-id" value="${roommateList.roommateID}" />
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__name" class="form-label">
                                                                            Họ và tên: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="full-name" value="${roommateList.information.fullname}"
                                                                               id="update-member-infor__name" class="form-control m-0"
                                                                               placeholder="Nhập tên thành viên">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__dob" class="form-label">
                                                                            Ngày sinh: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="date" name="dob" value="${roommateList.information.birthday}"
                                                                               id="update-member-infor__dob" class="form-control m-0"
                                                                               placeholder="Nhập ngày sinh">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__gender" class="form-label">
                                                                            Giới tính: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <select name="gender" class="form-control m-0" id="update-member-infor__gender">
                                                                            <option value="1" ${roommateList.information.sex eq 1 ? "selected" : ""}>Nam</option>
                                                                            <option value="0" ${roommateList.information.sex eq 0 ? "selected" : ""}>Nữ</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__phone" class="form-label">
                                                                            Số điện thoại: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="phone-number" value="${roommateList.information.phone}"
                                                                               id="update-member-infor__phone" class="form-control m-0"
                                                                               placeholder="Nhập số điện thoại">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__email" class="form-label">
                                                                            Email: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="email" value="${roommateList.information.email}"
                                                                               id="update-member-infor__email" class="form-control m-0"
                                                                               placeholder="Nhập email">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__cccd" class="form-label">
                                                                            CCCD/CMND: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="cccd" value="${roommateList.information.cccd}"
                                                                               id="update-member-infor__cccd" class="form-control m-0"
                                                                               placeholder="Nhậ số CMND/CCCD">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__address" class="form-label">
                                                                            Địa chỉ thường trú: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="address" value="${roommateList.information.address}"
                                                                               id="update-member-infor__address" class="form-control m-0"
                                                                               placeholder="Nhập địa chỉ thường trú">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__parent-name"
                                                                               class="form-label">
                                                                            Họ và tên cha/mẹ: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="parent-name" value="${roommateList.parentName}"
                                                                               id="update-member-infor__parent-name" class="form-control m-0"
                                                                               placeholder="Nhập họ/tên cha hoặc mẹ">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="row align-items-center">
                                                                    <div class="col-6">
                                                                        <label for="update-member-infor__parent-phone" class="form-label">
                                                                            Số điện thoại cha/mẹ: <span>*</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <input type="text" name="parent-phone" value="${roommateList.parentPhone}"
                                                                               id="update-member-infor__parent-phone" class="form-control m-0"
                                                                               placeholder="Nhập số điện thoại cha/mẹ">
                                                                    </div>
                                                                </div>
                                                                <span class="form-message mt-3"></span>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-bs-dismiss="modal">Đóng</button>
                                                            <button type="submit" class="btn btn-primary">
                                                                Cập nhật
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Update member information modal -->
                                        <!-- End member detail button -->
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                Trống
            </c:otherwise>
        </c:choose>
</div>
