<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12 col-md-7 room-information__left">
        <div class="infor-group">Khu trọ trực thuộc:
            <span>${sessionScope.hostel.hostelName}</span></div>
        <div class="infor-group">Địa chỉ:
            <span>${sessionScope.hostel.address}, ${sessionScope.hostel.ward.split('-')[1]}, ${sessionScope.hostel.district.split('-')[1]}, ${sessionScope.hostel.city.split('-')[1]}</span>
        </div>
        <div class="infor-group">Diện tích:
            <span>${requestScope.roomInformation.roomArea} m2</span></div>
        <div class="infor-group">Gác: <span>${requestScope.roomInformation.hasAttic eq 1 ? "Có" : "Không"}</span></div>
        <div class="infor-group">Trạng thái: <span>${requestScope.roomInformation.roomStatus eq 1 ? "Phòng sẵn sàng cho thuê" : "Đã được thuê"}</span></div>
    </div>
    <div class="col-12 col-md-5 room-information__right">
        <div class="infor-group">Ngày bắt đầu hợp đồng:
            <span>${requestScope.contractRoom.startDate ne null ? (requestScope.contractRoom.startDate.split('-')[2]+requestScope.contractRoom.startDate.split('-')[1]+requestScope.contractRoom.startDate.split('-')[0]) : "Trống"}</span>
        </div>
        <div class="infor-group">Ngày kết thúc hợp đồng:
            <span>${requestScope.contractRoom.expiration ne null ? (requestScope.contractRoom.expiration.split('-')[2]+requestScope.contractRoom.expiration.split('-')[1]+requestScope.contractRoom.expiration.split('-')[0]) : "Trống"}</span>
        </div>
        <div class="infor-group">Tiền cọc: <span>${requestScope.contractRoom.deposit ne null ?  String.format("%,d", requestScope.contractRoom.deposit) : "Trống"}</span>
        </div>
        <div class="infor-group">Tiền phòng: <span>${requestScope.contractRoom.price ne null ? String.format("%,d", requestScope.contractRoom.price) : "Trống"}</span>
        </div>
        <div class="infor-group">Số lượng thành viên:
            <span>${requestScope.listRoommatesInfo.size()}/${requestScope.roomInformation.capacity}</span>
        </div>
    </div>
</div>
