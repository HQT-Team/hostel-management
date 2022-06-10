<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="room-invoice">
  <div class="invoice-header">
    <div class="invoice-title">Hóa đơn gần nhất</div>
    <div class="invoice-created-date">Ngày tạo:
      <span>${requestScope.billRoom.createdDate.split("-")[2]}-${requestScope.billRoom.createdDate.split("-")[1]}-${requestScope.billRoom.createdDate.split("-")[0]}</span></div>
  </div>
  <div class="invoice-body">
    <div class="invoice-group">
      <div class="invoice-label">Trạng thái:</div>
      <!-- Paid: success ~ Unpaid: fail -->
      <div class="invoice-content status success">
        <c:choose>
          <c:when test="${requestScope.billRoom.status eq 1}">Đã thanh toán</c:when>
          <c:when test="${requestScope.billRoom.status eq 0}">Chưa thanh toán</c:when>
        </c:choose>
      </div>
    </div>
    <div class="invoice-group">
      <div class="invoice-label">Hình thức thanh toán:</div>
      <div class="invoice-content">
        ${requestScope.billRoom.payment.paymentName}
      </div>
    </div>
    <div class="invoice-group">
      <div class="invoice-label">Tổng tiền:</div>
      <c:choose>
        <c:when test="${requestScope.billRoom ne null}"><div class="invoice-content price">${String.format("%,d",requestScope.billRoom.totalMoney)}
          <span>VNĐ</span></div></c:when>
      </c:choose>
    </div>
  </div>
  <div class="invoice-actions">
    <div class="left-actions">
      <!-- Start view invoice detail button -->
      <button class="invoice-action-btn invoice-detail-btn" data-bs-toggle="modal"
              data-bs-target="#view-invoice-detail-modal">
        Chi tiết
      </button>
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
                  <span>05</span>/<span>2021</span>
                </div>
                <div class="invoice-detail__infor">
                  <div class="row">
                    <div class="col-6 invoice-detail__infor-left">
                      <div class="invoice-detail__infor-detail">
                        Phòng số: <span>11</span>
                      </div>
                      <div class="invoice-detail__infor-detail">
                        Khu trọ: <span>NovaLand Sky</span>
                      </div>
                      <div class="invoice-detail__infor-detail">
                        Địa chỉ: <span>999 Hoàng Hữu Nam, phường
                                                                                    Long Thạnh Mỹ, TP. Thủ Đức, TP. Hồ
                                                                                    Chí Minh</span>
                      </div>
                    </div>
                    <div class="col-6 invoice-detail__infor-right">
                      <div class="invoice-detail__infor-detail">
                        Ngày tạo hóa đơn:
                        <span>29/05/2022</span>
                      </div>
                      <div class="invoice-detail__infor-detail">
                        Ngày thanh toán: <span>02/06/2022</span>
                      </div>
                      <div class="invoice-detail__infor-detail">
                        Trạng thái: <span>Đã thanh toán</span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="invoice-detail__consumed-group">
                  Điện: <span>Số cũ: <span>300</span></span>
                  <span>Số mới: <span>400</span></span>
                  <span>Tiêu thụ: <span>100</span></span>
                </div>
                <div class="invoice-detail__consumed-group">
                  Nước: <span>Số cũ: <span>28</span></span>
                  <span>Số mới: <span>33</span></span>
                  <span>Tiêu thụ: <span>5</span></span>
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
                    <tr>
                      <td>1</td>
                      <td>Điện</td>
                      <td>Kwh</td>
                      <td>100</td>
                      <td>3500</td>
                      <td>350000 <span>vnđ</span></td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>Nước</td>
                      <td>m3</td>
                      <td>5</td>
                      <td>15000</td>
                      <td>75000 <span>vnđ</span></td>
                    </tr>
                    <tr>
                      <td>3</td>
                      <td>Tiền phòng</td>
                      <td>phòng</td>
                      <td>1</td>
                      <td>2500000</td>
                      <td>2500000 <span>vnđ</span></td>
                    </tr>
                    <!-- Total -->
                    <td colspan="5" class="text-end total">Tổng
                      tiền:
                    </td>
                    <td>2925000 <span>vnđ</span></td>
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
                        Nguyễn Văn A
                      </div>
                    </div>
                    <div class="col-6">
                      <div
                              class="invoice-detail__signature-title">
                        Người thanh toán
                      </div>
                      <div class="invoice-detail__signature-name">
                        Nguyễn Văn B
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary"
                      data-bs-dismiss="modal">Xác nhận</button>
            </div>
          </div>
        </div>
      </div>
      <!-- End view invoice detail modal -->
      <!-- End view invoice detail button -->
    </div>
    <div class="right-actions">

      <button class="invoice-action-btn list-invoices-btn">
        Danh sách hóa đơn
      </button>

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
              <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-danger"
                        data-bs-dismiss="modal">Chưa</button>
                <a href="" class="btn btn-success">
                  Xác nhận
                </a>
              </div>
            </div>
          </div>
        </div>
        <!-- End invoice confirm paid button -->
      </c:if>
    </div>
  </div>
</div>