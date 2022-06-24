<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>

<div class="room-header">
    <h2 class="room-name">Phòng ${sessionScope.room.roomNumber}</h2>
    <div class="room-actions">
        <!-- Start update room information button -->
        <button class="action-update-btn" data-bs-toggle="modal"
                data-bs-target="#update-room-infor-modal">Cập nhật
        </button>
        <!-- Start update room modal -->
        <div class="modal fade" id="update-room-infor-modal" tabindex="-1"
             aria-labelledby="update-room-infor-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="update-room-infor-modal-label">
                            Cập nhật thông tin phòng
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <!-- Form update room -->
                    <form action="updateRoom" method="POST"
                          id="update-room-infor-form"
                          class="custom-form update-room-infor-form">
                        <div class="modal-body">
                            <!-- Room number -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="update-room-infor__room-number"
                                               class="form-label">
                                            Số phòng
                                        </label>
                                    </div>
                                    <div class="col-6">
                                        <input type="number" name="room-number"
                                               id="update-room-infor__room-number"
                                               value="${sessionScope.room.roomNumber}"
                                               class="form-control m-0">
                                    </div>
                                </div>
                                <span class="form-message mt-3"></span>
                            </div>
                            <!-- Room capacity -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="update-room-infor__room-capacity"
                                               class="form-label">
                                            Số lượng thành viên tối đa
                                        </label>
                                    </div>
                                    <div class="col-6">
                                        <input type="number" name="room-capacity"
                                               id="update-room-infor__room-capacity"
                                               value="${sessionScope.room.capacity}"
                                               class="form-control m-0">
                                    </div>
                                </div>
                                <span class="form-message mt-3"></span>
                            </div>
                            <!-- Room area -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="update-room-infor__room-area"
                                               class="form-label">
                                            Diện tích
                                        </label>
                                    </div>
                                    <div class="col-4">
                                        <input type="number" name="room-area"
                                               id="update-room-infor__room-area"
                                               value="${sessionScope.room.roomArea}"
                                               class="form-control m-0">
                                    </div>
                                    <div class="col-2 text-center">
                                        m2
                                    </div>
                                </div>
                                <span class="form-message mt-3"></span>
                            </div>
                            <!-- Room attic -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="update-room-infor__room-attic"
                                               class="form-label">
                                            Gác
                                        </label>
                                    </div>
                                    <div class="col-4">
                                        <select name="room-attic"
                                                id="update-room-infor__room-attic"
                                                class="form-control m-0">
                                            <option value="1" ${sessionScope.room.hasAttic eq 1 ? "selected" : ""}>Có
                                            </option>
                                            <option value="0" ${sessionScope.room.hasAttic eq 0 ? "selected" : ""}>
                                                Không
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-danger"
                                    data-bs-dismiss="modal">Hủy bỏ
                            </button>
                            <input type="hidden" name="roomID"
                                   value="${sessionScope.room.roomId}">
                            <button type="submit" class="btn btn-primary">Cập nhật</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End update room modal -->
        <!-- End update room information button -->

        <c:choose>
            <c:when test="${sessionScope.room.roomStatus eq 1}">
                <!-- Start create account button -->
                <a href="create-room-account-page" class="action-create-account-link">Tạo tài khoản</a>
                <!-- End create account button -->
            </c:when>
            <c:when test="${requestScope.renterAccount.status eq 1}">
                <!-- Start calculate button button -->
                <form action="calculateTotalCost" method="get">
                    <input type="hidden" name="roomID" value="${sessionScope.room.roomId}">
                    <button class="action-calculate-btn">Tính tiền phòng</button>
                </form>
                <!-- End calculate button button -->
            </c:when>
            <c:when test="${sessionScope.room.roomStatus eq 0 && requestScope.renterAccount.status eq 0}">
                <!-- Start view QR Code button -->
                <form action="get-invite-code" method="post">
                    <button type="submit" class="action-create-account-link">Xem mã tham gia</button>
                </form>
                <!-- End view QR Code button -->
            </c:when>
        </c:choose>
    </div>
</div>
