<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="room-account mb-4 mb-md-0">
    <div class="account-header">
        Tài khoản truy cập
    </div>
    <c:choose>
        <c:when test="${requestScope.renterAccount.username ne null}">
            <div class="account-username">Tên tài khoản:
                <span>${requestScope.renterAccount.username}</span>
            </div>
<%--                <c:choose>--%>
<%--                    <c:when test="${requestScope.renterAccount.status eq 1}">--%>
<%--                        <div class="account-status">Trạng thái:--%>
<%--                            <span class="text-success">Đã kích hoạt</span>--%>
<%--                        </div>--%>
<%--                        <div class="account-action">--%>
<%--                            <!-- Start reset password btn -->--%>
<%--                            <button data-bs-toggle="modal" data-bs-target="#reset-password-modal"--%>
<%--                                    class="account-reset-password">Đặt lại mật khẩu</button>--%>
<%--                            <!-- End reset password btn -->--%>
<%--                            <!-- Reset password modal -->--%>
<%--                            <div class="modal fade" id="reset-password-modal" tabindex="-1"--%>
<%--                                 aria-labelledby="reset-password-modalLabel" aria-hidden="true">--%>
<%--                                <div class="modal-dialog modal-dialog-centered">--%>
<%--                                    <div class="modal-content">--%>
<%--                                        <div class="modal-header">--%>
<%--                                            <h5 class="modal-title" id="reset-password-modalLabel">--%>
<%--                                                Cập nhật mật khẩu--%>
<%--                                            </h5>--%>
<%--                                            <button type="button" class="btn-close" data-bs-dismiss="modal"--%>
<%--                                                    aria-label="Close"></button>--%>
<%--                                        </div>--%>
<%--                                        <form action="" class="custom-form reset-password-form"--%>
<%--                                              id="reset-password-form">--%>
<%--                                            <div class="modal-body">--%>
<%--                                                <div class="form-group text-start">--%>
<%--                                                    <label for="reset-password-input" class="form-label">--%>
<%--                                                        Mật khẩu mới <span>*</span>--%>
<%--                                                    </label>--%>
<%--                                                    <input id="reset-password-input" type="password"--%>
<%--                                                           class="form-control"--%>
<%--                                                           placeholder="Nhập mật khẩu mới">--%>
<%--                                                    <span class="form-message"></span>--%>
<%--                                                </div>--%>
<%--                                                <div class="form-group text-start">--%>
<%--                                                    <label for="reset-confirm-password-input"--%>
<%--                                                           class="form-label">--%>
<%--                                                        Xác nhận mật khẩu <span>*</span>--%>
<%--                                                    </label>--%>
<%--                                                    <input id="reset-confirm-password-input" type="password"--%>
<%--                                                           class="form-control"--%>
<%--                                                           placeholder="Xác nhận mật khẩu">--%>
<%--                                                    <span class="form-message"></span>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                            <div class="modal-footer">--%>
<%--                                                <button type="button" class="btn btn-secondary"--%>
<%--                                                        data-bs-dismiss="modal">Hủy bỏ</button>--%>
<%--                                                <button type="submit" class="btn btn-primary">--%>
<%--                                                    Cập nhật--%>
<%--                                                </button>--%>
<%--                                            </div>--%>
<%--                                        </form>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <!-- End Reset password modal -->--%>
<%--                        </div>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <div class="account-status">Trạng thái:--%>
<%--                            <span class="text-danger">Chưa kích hoạt</span>--%>
<%--                        </div>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
        </c:when>
        <c:otherwise>
            Trống
        </c:otherwise>
    </c:choose>
</div>
