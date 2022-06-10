<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12 col-md-7 room-information__left">
        <div class="infor-group">Khu trọ trực thuộc:
            <span>${sessionScope.hostel.hostelName}</span></div>
        <div class="infor-group">Địa chỉ:
            <span>${sessionScope.hostel.address}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
        </div>
        <div class="infor-group">Diện tích:
            <span>${requestScope.roomInformation.roomArea} m2</span></div>
        <div class="infor-group">Gác: <span>
                                    <c:choose>
                                        <c:when test="${requestScope.roomInformation.hasAttic eq 1}">
                                            <c:out value="Có"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Không"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span></div>
        <div class="infor-group">Trạng thái: <span>
                                    <c:choose>
                                        <c:when test="${requestScope.roomInformation.roomStatus eq 1}">
                                            <c:out value="Phòng sẵn sàng cho thuê"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Đã được thuê"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span></div>
    </div>
    <div class="col-12 col-md-5 room-information__right">
        <div class="infor-group">Ngày bắt đầu hợp đồng:
            <span>${requestScope.contractRoom.startDate.split('-')[2]}-${requestScope.contractRoom.startDate.split('-')[1]}-${requestScope.contractRoom.startDate.split('-')[0]}</span>
        </div>
        <div class="infor-group">Ngày kết thúc hợp đồng:
            <span>${requestScope.contractRoom.expiration.split('-')[2]}-${requestScope.contractRoom.expiration.split('-')[1]}-${requestScope.contractRoom.expiration.split('-')[0]}</span>
        </div>
        <div class="infor-group">Tiền cọc: <span>${String.format("%,d", requestScope.contractRoom.deposit)}</span>
        </div>
        <div class="infor-group">Tiền phòng: <span>${String.format("%,d", requestScope.contractRoom.price)}</span>
        </div>
        <div class="infor-group">Số lượng thành viên:
            <span>${requestScope.quantityMember}/${requestScope.roomInformation.capacity}</span>
        </div>
    </div>
</div>
