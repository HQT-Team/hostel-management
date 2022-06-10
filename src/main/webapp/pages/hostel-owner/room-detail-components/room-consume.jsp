<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="room-consume">
    <div class="consume-header">Số điện/nước tiêu thụ</div>
    <div class="consume-update-date">Ngày cập nhật gần nhất:
        <span
                class="date">${requestScope.consumeRoom.endConsumeDate.split("-")[2]}-${requestScope.consumeRoom.endConsumeDate.split("-")[1]}-${requestScope.consumeRoom.endConsumeDate.split("-")[0]}
        </span>
    </div>
    <div class="consume-group">
        <div class="consume-name">Điện:</div>
        <div class="consume-quantity">${requestScope.consumeRoom.numberElectric}</div>
    </div>
    <div class="consume-group">
        <div class="consume-name">Nước:</div>
        <div class="consume-quantity">${requestScope.consumeRoom.numberWater}</div>
    </div>
    <div class="consume-actions">
        <!-- Start consume update button -->
        <button class="consume-update-btn" data-bs-toggle="modal"
                data-bs-target="#update-consume-modal">Cập nhật
        </button>
        <!-- Start consume update modal -->
        <div class="modal fade" id="update-consume-modal" tabindex="-1"
             aria-labelledby="update-consume-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="update-consume-modal-label">
                            Cập nhật số điện/nước tiêu thụ
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="" method=""
                          class="custom-form update-consume-modal-form"
                          id="update-consume-modal-form">
                        <div class="modal-body">
                            <!-- Label - Don't update -->
                            <div class="form-group">
                                <div class="row text-center">
                                    <div class="col-2"></div>
                                    <div class="col-5">
                                        <label class="form-label">Số cũ</label>
                                    </div>
                                    <div class="col-5">
                                        <label class="form-label">
                                            Số mới
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <!-- Input electric -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-2 text-start">
                                        <label for="form-update-consume__electric"
                                               class="form-label">Điện</label>
                                    </div>
                                    <div class="col-5">
                                        <input type="number" value="2423" disabled
                                               class="form-control m-0"
                                               id="form-update-consume__electric-old">
                                    </div>
                                    <div class="col-5">
                                        <div class="text-center">
                                            <input id="form-update-consume__electric"
                                                   type="number" name="number-electric"
                                                   value="" class="form-control m-0"
                                                   placeholder="Nhập số điện mới">
                                        </div>
                                    </div>
                                </div>
                                <span class="form-message text-start mt-3 mb-3"></span>
                            </div>
                            <!-- Input water -->
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-2 text-start">
                                        <label for="form-update-consume__water"
                                               class="form-label">Nước</label>
                                    </div>
                                    <div class="col-5">
                                        <input type="number" value="2423" disabled
                                               class="form-control m-0"
                                               id="form-update-consume__water-old">
                                    </div>
                                    <div class="col-5">
                                        <div class="text-center">
                                            <input id="form-update-consume__water"
                                                   type="number" name="number-water"
                                                   value="" class="form-control m-0"
                                                   placeholder="Nhập số nước mới">
                                        </div>
                                    </div>
                                </div>
                                <span class="form-message text-start mt-3 mb-3"></span>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Hủy bỏ
                            </button>
                            <button type="submit" class="btn btn-danger">
                                Cập nhật
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End consume update modal -->
        <!-- End consume update button -->

        <!-- Start consume history list button -->
        <button class="consume-history-btn" data-bs-toggle="modal"
                data-bs-target="#history-update-consume-modal">
            Lịch sử tiêu thụ
        </button>
        <!-- Start consume history list modal -->
        <div class="modal fade" id="history-update-consume-modal" tabindex="-1"
             aria-labelledby="history-update-consume-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="history-update-consume-modal-label">
                            Lịch sử số điện/nước tiêu thụ
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-center"
                         style="max-height: 70vh; overflow-y: auto;">
                        <!-- Label -->
                        <div class="form-group">
                            <div class="row">
                                <div class="col-3">
                                    <div class="form-label">Điện</div>
                                </div>
                                <div class="col-3">
                                    <div class="form-label">Nước</div>
                                </div>
                                <div class="col-3">
                                    <div class="form-label">Ngày bắt đầu tiêu thụ</div>
                                </div>
                                <div class="col-3">
                                    <div class="form-label">Ngày kết thúc tiêu thụ</div>
                                </div>
                            </div>
                        </div>
                        <!-- Items -->
                        <div class="form-group">
                            <c:forEach var="consumeList" items="${requestScope.consumeList}">
                                <div class="row">
                                    <div class="col-3">
                                        <input type="text" value="${consumeList.numberElectric}"
                                               class="form-control" disabled>
                                    </div>
                                    <div class="col-3">
                                        <input type="text" value="${consumeList.numberWater}"
                                               class="form-control" disabled>
                                    </div>
                                    <div class="col-3">
                                        <input type="text"
                                               value="${consumeList.startConsumeDate.split("-")[2]}-${consumeList.startConsumeDate.split("-")[1]}-${consumeList.startConsumeDate.split("-")[0]}"
                                               class="form-control" disabled>
                                    </div>
                                    <div class="col-3">
                                        <input type="text"
                                               value="${consumeList.endConsumeDate.split("-")[2]}-${consumeList.endConsumeDate.split("-")[1]}-${consumeList.endConsumeDate.split("-")[0]}"
                                               class="form-control" disabled>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary"
                                data-bs-dismiss="modal">Xong
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Start consume history list modal -->
        <!-- End consume history list button -->
    </div>
</div>