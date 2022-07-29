<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="vi_VN"/>
<div class="row">
    <div class="col-12 col-md-7 room-information__left">
        <div class="infor-group">Khu trọ trực thuộc:
            <span>${sessionScope.hostel.hostelName}</span></div>
        <div class="infor-group">Địa chỉ:
            <span>${sessionScope.hostel.address}, ${sessionScope.hostel.ward.split('-')[1]}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
        </div>
        <div class="infor-group">Diện tích:
            <span>${sessionScope.room.roomArea} m2</span></div>
        <div class="infor-group">Gác: <span>${sessionScope.room.hasAttic eq 1 ? "Có" : "Không"}</span></div>
        <div class="infor-group">Trạng thái:
            <c:choose>
                <c:when test="${sessionScope.room.roomStatus eq 0}">
                    <span class="text-danger">Đã được thuê</span>
                </c:when>
                <c:when test="${sessionScope.room.roomStatus eq 1}">
                    <span class="text-success">Sẵn sàng cho thuê</span>
                </c:when>
                <c:when test="${sessionScope.room.roomStatus eq -1}">
                    <span class="text-warning">Đang làm hợp đồng</span>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div class="col-12 col-md-5 room-information__right">
        <div class="infor-group">Ngày bắt đầu hợp đồng:
            <span>
                <c:choose>
                    <c:when test="${requestScope.contractRoom.startDate ne null}">
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.contractRoom.startDate}"
                                       var="startDate"/>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${startDate}"/>
                    </c:when>
                    <c:otherwise>
                        Trống
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="infor-group">Ngày kết thúc hợp đồng:
            <span>
                <c:choose>
                    <c:when test="${requestScope.contractRoom.expiration ne null}">
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.contractRoom.expiration}"
                                       var="expirationDate"/>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${expirationDate}"/>
                    </c:when>
                    <c:otherwise>
                        Trống
                    </c:otherwise>
                </c:choose>
        </div>
        <div class="infor-group">Tiền cọc:
            <span>
                <c:choose>
                    <c:when test="${requestScope.contractRoom.deposit ne null}">
                        <fmt:formatNumber value="${requestScope.contractRoom.deposit}" type="currency" currencySymbol="VNĐ"/>
                    </c:when>
                    <c:otherwise>
                        Trống
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="infor-group">Tiền phòng:
            <span>
                <c:choose>
                    <c:when test="${requestScope.contractRoom.price ne null}">
                        <fmt:formatNumber value="${requestScope.contractRoom.price}" type="currency" currencySymbol="VNĐ"/>
                    </c:when>
                    <c:otherwise>
                        Trống
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
        <div class="infor-group">Số lượng thành viên:
            <span>${requestScope.listRoommatesInfo eq null ? 0 : requestScope.listRoommatesInfo.size()}/${sessionScope.room.capacity}</span>
        </div>
    </div>
</div>
