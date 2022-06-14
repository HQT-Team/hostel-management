<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="infrastructure-section">
    <div class="infrastructure-header">
        Cơ sở vật chất
    </div>
    <table class="table table-bordered mt-3 mb-4">
        <thead class="text-center">
        <th>Tên</th>
        <th>Đơn vị</th>
        <th style="width: 136px;">Trạng thái</th>
        </thead>
        <tbody class="text-center">
        <c:forEach var="infrastructures" items="${requestScope.infrastructures}">
            <tr>
                <td>${infrastructures.name}</td>
                <td>Cái</td>
                <c:choose>
                    <c:when test="${infrastructures.status eq 1}">
                        <td class="infrastructure-status">
                            Sử dụng tốt
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td class="infrastructure-status broken">
                            Hư hỏng
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="infrastructure-actions">
        <!-- Start update infrastructure button -->
        <button class="infrastructure-btn update-btn" data-bs-toggle="modal"
                data-bs-target="#update-infrastructure-modal">
            Cập nhật
        </button>
        <!-- Start update infrastructure modal -->
        <div class="modal fade" id="update-infrastructure-modal" tabindex="-1"
             aria-labelledby="update-infrastructure-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="update-infrastructure-modal-label">
                            Cập nhật cơ sở vật chất
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="updateInfrastructureStatus" method="POST" class="custom-form">
                        <div class="modal-body">
                            <!-- Label - Dont't update -->
                            <div class="form-group">
                                <div class="row text-center">
                                    <div class="col-7">
                                        <label class="form-label">Tên</label>
                                    </div>
                                    <div class="col-5">
                                        <label class="form-label">Trạng thái</label>
                                    </div>
                                </div>
                            </div>
                            <!-- Loop through infrastructure -->
                            <c:forEach var="infrastructures" items="${requestScope.infrastructures}">
                                <div class="row">
                                    <div class="col-7">
                                        <input type=hidden name="infrastructureId" value="${infrastructures.id}">
                                        <input type="text" value="${infrastructures.name}"
                                               class="form-control" disabled>
                                    </div>
                                    <div class="col-5">
                                        <div class="form-group">
                                            <select name="status" class="form-control">
                                                <c:choose>
                                                    <c:when test="${infrastructures.status eq 1}">
                                                        <option value="1" selected>Sử dụng tốt</option>
                                                        <option value="0">Hư hỏng</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="1">Sử dụng tốt</option>
                                                        <option value="0" selected>Hư hỏng</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <span class="form-message"></span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Đóng
                            </button>
                            <input type="hidden" name="roomID" value="${sessionScope.room.roomId}">
                            <button type="submit" class="btn btn-danger">
                                Cập nhật
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End update infrastructure modal -->
        <!-- End update infrastructure button -->

        <!-- Start add infrastructure button -->
        <button class="infrastructure-btn add-btn" data-bs-toggle="modal"
                data-bs-target="#add-new-infrastructure-modal">
            Thêm cở sở vật chất
        </button>
        <!-- Start update infrastructure modal -->
        <div class="modal fade" id="add-new-infrastructure-modal" tabindex="-1"
             aria-labelledby="add-new-infrastructure-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="add-new-infrastructure-modal-label">
                            Thêm cơ sở vật chất
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <form action="addInfrastructureServlet" method="post"
                          class="custom-form add-new-infrastructure-form">
                        <div class="modal-body">
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="add-new-infrastructure-type"
                                               class="form-label">Tên cơ sở vật
                                            chất <span>*</span></label>
                                    </div>
                                    <div id="add-new-infrastructure-type" class="col-6">
                                        <select class="form-control m-0" name="idNewInfrastructure">
                                            <option value="0">Chọn loại cơ sở vật chất</option>
                                            <c:forEach var="infrastructureItem"
                                                       items="${requestScope.infrastructureList}">
                                                <option value="${infrastructureItem.idInfrastructureItem}">${infrastructureItem.infrastructureName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <span class="form-message mt-3"></span>
                            </div>
                            <div class="form-group">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <label for="add-new-infrastructure-status"
                                               class="form-label">
                                            Trạng thái <span>*</span>
                                        </label>
                                    </div>
                                    <div id="add-new-infrastructure-status"
                                         class="col-6">
                                        <select class="form-control m-0" name="statusNewInfrastructure">
                                            <option value="1">Sử dụng tốt</option>
                                            <option value="2">Hư hỏng</option>
                                        </select>
                                    </div>
                                </div>
                                <span class="form-message mt-3"></span>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Quay về
                            </button>
                            <input type="hidden" name="roomID" value="${sessionScope.room.roomId}">
                            <button type="submit" class="btn btn-primary">
                                Thêm
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- End update infrastructure modal -->
        <!-- End add infrastructure button -->
    </div>
</div>
