<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<div class="room-invoice">
    <div class="invoice-header">
        <div class="invoice-title">Hóa đơn gần nhất</div>
        <div class="invoice-created-date">
            <c:choose>
                <c:when test="${requestScope.billRoom ne null}">
                    Ngày tạo: <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.billRoom.createdDate}"
                                             var="createdDate"/>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${createdDate}"/>
                </c:when>
                <c:otherwise>
                    <span>Hiện chưa có hóa đơn</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:choose>
        <c:when test="${requestScope.billRoom ne null}">
            <div class="invoice-body">
                <div class="invoice-group">
                    <div class="invoice-label">Trạng thái:</div>
                    <!-- Paid: success ~ Unpaid: fail -->
                    <c:choose>
                        <c:when test="${requestScope.billRoom.status eq 1}">
                            <div class="invoice-content status success">
                                Đã thanh toán
                            </div>
                        </c:when>
                        <c:when test="${requestScope.billRoom.status eq 0}">
                            <div class="invoice-content status fail">
                                Chưa thanh toán
                            </div>
                        </c:when>
                    </c:choose>
                </div>
                <div class="invoice-group">
                    <div class="invoice-label">Phương thức thanh toán:</div>
                    <div class="invoice-content">
                            ${requestScope.paymentName eq null ? "Trống" : requestScope.paymentName}
                    </div>
                </div>
                <div class="invoice-group">
                    <div class="invoice-label">Tổng tiền:</div>
                    <c:choose>
                        <c:when test="${requestScope.billRoom ne null}">
                            <div class="invoice-content price">
                                <fmt:formatNumber value="${requestScope.billRoom.totalMoney}" type="currency" currencySymbol="VNĐ"/>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:when>
    </c:choose>
    <div class="invoice-actions">
        <div class="left-actions">
            <!-- Start view invoice detail button -->
            <c:choose>
                <c:when test="${requestScope.billRoom ne null}">
                    <button class="invoice-action-btn invoice-detail-btn" data-bs-toggle="modal"
                            data-bs-target="#view-invoice-detail-modal">
                        Chi tiết
                    </button>
                </c:when>
            </c:choose>

            <!-- Start view invoice detail modal -->
            <div class="modal fade" id="view-invoice-detail-modal" tabindex="-1"
                 aria-labelledby="view-invoice-detail-modal-label" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"
                                id="view-invoice-detail-modal-label">
                                Chi tiết hóa đơn
                            </h5>
                            <button type="button" class="btn-close"
                                    data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="invoice-detail__wrapper">
                                <div class="invoice-detail__title">Hóa Đơn Tháng
                                    <span>${requestScope.billRoom.billTitle}</span>
                                </div>
                                <div class="invoice-detail__infor">
                                    <div class="row">
                                        <div class="col-6 invoice-detail__infor-left">
                                            <div class="invoice-detail__infor-detail">
                                                Phòng số: <span>${sessionScope.room.roomNumber}</span>
                                            </div>
                                            <div class="invoice-detail__infor-detail">
                                                Khu trọ: <span>${sessionScope.hostel.hostelName}</span>
                                            </div>
                                            <div class="invoice-detail__infor-detail">
                                                Địa chỉ:
                                                <span>${sessionScope.hostel.address}, ${sessionScope.hostel.ward.split('-')[1]}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
                                            </div>
                                        </div>
                                        <div class="col-6 invoice-detail__infor-right">
                                            <div class="invoice-detail__infor-detail">
                                                Ngày tạo hóa đơn:
                                                <span>
                                                    <fmt:parseDate pattern="yyyy-MM-dd"
                                                                   value="${requestScope.billRoom.createdDate}"
                                                                   var="createdDate"/>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${createdDate}"/>
                                                </span>
                                            </div>
                                            <div class="invoice-detail__infor-detail">
                                                Ngày tới hạn thanh toán:
                                                <span>
                                                    <fmt:parseDate pattern="yyyy-MM-dd"
                                                                   value="${requestScope.billRoom.expiredPaymentDate}"
                                                                   var="expiredPaymentDate"/>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${expiredPaymentDate}"/>
                                                </span>
                                            </div>

                                            <c:choose>
                                                <c:when test="${requestScope.billRoom.status eq 1}">
                                                    <div class="invoice-detail__infor-detail">
                                                        Ngày thanh toán:
                                                        <span>
                                                <fmt:parseDate pattern="yyyy-MM-dd"
                                                               value="${requestScope.billRoom.paymentDate == null ? '2022/10/10' : requestScope.billRoom.paymentDate}"
                                                               var="paymentDate"/>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${paymentDate}"/>
<%--                                                ${requestScope.billRoom.paymentDate == null ? "Chưa thanh toán" : requestScope.billRoom.paymentDate}--%>
                                                </span>
                                                    </div>
                                                    <div class="invoice-detail__infor-detail">
                                                        Phương thức thanh toán:
                                                        <span>${requestScope.paymentName == null ? "Trống" : requestScope.paymentName}</span>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                            <div class="invoice-detail__infor-detail">
                                                Trạng thái:
                                                <span>${requestScope.billRoom.status == 0 ? "Chưa thanh toán" : "Đã thanh toán"}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:set var="consumeBeginMonth" value="${requestScope.consumeStart}"/>
                                <c:set var="consumeEndMonth" value="${requestScope.consumeEnd}"/>
                                <div class="invoice-detail__consumed-group">
                                    Điện: <span>Số cũ: <span>${consumeBeginMonth.numberElectric}</span></span>
                                    <span>Số mới: <span>${consumeEndMonth.numberElectric}</span></span>
                                    <c:set var="consumeNumberElectric"
                                           value="${consumeEndMonth.numberElectric - consumeBeginMonth.numberElectric}"/>
                                    <span>Tiêu thụ: <span>${consumeNumberElectric}</span></span>
                                </div>
                                <div class="invoice-detail__consumed-group">
                                    Nước: <span>Số cũ: <span>${consumeBeginMonth.numberWater}</span></span>
                                    <span>Số mới: <span>${consumeEndMonth.numberWater}</span></span>
                                    <c:set var="consumeNumberWater"
                                           value="${consumeEndMonth.numberWater - consumeBeginMonth.numberWater}"/>
                                    <span>Tiêu thụ: <span>${consumeNumberWater}</span></span>
                                </div>
                                <div class="invoice-detail__table">
                                    <table
                                            class="table table-secondary table-striped table-bordered">
                                        <thead>
                                        <tr class="text-center">
                                            <th>STT</th>
                                            <th>Tên</th>
                                            <th>Đơn vị tính</th>
                                            <th>Số lượng</th>
                                            <th>Đơn giá</th>
                                            <th>Thành tiền</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:set var="count" value="0"/>
                                        <c:set var="totalCost" value="0"/>
                                        <c:forEach var="service" items="${requestScope.serviceInfo}">
                                            <c:set var="count" value="${count+1}"/>
                                            <c:set var="quantity" value="1"/>
                                            <tr>
                                                <td>${count}</td>
                                                <td>${service.serviceName}</td>
                                                <td>${service.unit}</td>
                                                <c:choose>
                                                    <c:when test="${service.serviceName eq 'Điện'}">
                                                        <c:set var="quantity" value="${consumeNumberElectric}"/>
                                                        <td>${consumeNumberElectric}</td>
                                                    </c:when>
                                                    <c:when test="${service.serviceName eq 'Nước'}">
                                                        <c:set var="quantity" value="${consumeNumberWater}"/>
                                                        <td>${consumeNumberWater}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="quantity" value="1"/>
                                                        <td>1</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <td>
                                                    <fmt:formatNumber value="${service.servicePrice}" type="currency" currencySymbol="VNĐ"/>
                                                </td>

                                                <c:set var="totalCost"
                                                       value="${totalCost + service.servicePrice * quantity}"/>
                                                <td><fmt:formatNumber value="${service.servicePrice * quantity}"
                                                                      type="currency" currencySymbol="VNĐ"/></td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td>${count+1}</td>
                                            <td>Tiền phòng</td>
                                            <td>phòng</td>
                                            <td>1</td>
                                            <td>
                                                <fmt:formatNumber value="${requestScope.contractRoom.price}" type="currency" currencySymbol="VNĐ"/>
                                            </td>
                                            <c:set var="totalCost"
                                                   value="${totalCost + requestScope.contractRoom.price}"/>
                                            <td><fmt:formatNumber value="${requestScope.contractRoom.price}"
                                                                  type="currency" currencySymbol="VNĐ"/></td>
                                        </tr>
                                        <!-- Total -->
                                        <td colspan="5" class="text-end total">Tổng
                                            tiền:
                                        </td>
                                        <td><fmt:formatNumber value="${totalCost}" type="currency" currencySymbol="VNĐ"/></td>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="invoice-detail__signature">
                                    <div class="row">
                                        <div class="col-6">
                                            <div
                                                    class="invoice-detail__signature-title">
                                                Người lập hóa đơn
                                            </div>
                                            <div class="invoice-detail__signature-name">
                                                ${requestScope.billMakerFullName}
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div
                                                    class="invoice-detail__signature-title">
                                                Người thanh toán
                                            </div>
                                            <div class="invoice-detail__signature-name">
                                                ${requestScope.billPaymenterFullName}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <a class="btn btn-secondary" href="export-excel?billID=${requestScope.billRoom.billID}">
                                Xuất Excel
                            </a>
                            <button type="button" class="btn btn-primary"
                                    data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End view invoice detail modal -->
            <!-- End view invoice detail button -->
        </div>
        <div class="right-actions">

            <c:choose>
                <c:when test="${requestScope.billRoom ne null}">
                    <form action="roomInvoiceList" method="post">
                        <button class="invoice-action-btn list-invoices-btn">
                            Danh sách hóa đơn
                        </button>
                    </form>
                </c:when>
            </c:choose>

            <c:if test="${requestScope.billRoom.status eq 0 && requestScope.billRoom ne null}">
                <!-- If this invoice has been paid, please hide this button -->
                <!-- Start invoice confirm paid button -->
                <button class="invoice-action-btn invoice-confirm-paid-btn"
                        data-bs-toggle="modal" data-bs-target="#invoice-confirm-paid-modal">
                    Xác nhận đã thanh toán
                </button>
                <div class="modal fade" id="invoice-confirm-paid-modal" tabindex="-1"
                     aria-labelledby="invoice-confirm-paid-modal-label" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title text-warning"
                                    id="invoice-confirm-paid-modal-label">Cảnh
                                    báo</h5>
                                <button type="button" class="btn-close"
                                        data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn là người thuê đã thanh toán tiền phòng?
                            </div>
                            <form action="updateBilLStatus" method="POST">
                                <div class="modal-footer justify-content-between">
                                    <button type="button" class="btn btn-danger"
                                            data-bs-dismiss="modal">Hủy bỏ
                                    </button>
                                    <input type="hidden" name="billID" value="${requestScope.billRoom.billID}">
                                    <button type="submit" class="btn btn-success">
                                        Xác nhận
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- End invoice confirm paid button -->
            </c:if>
        </div>
    </div>
</div>