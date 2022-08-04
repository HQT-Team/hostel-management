<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dropdown">
    <button class="nut_dropdown"><i class="fa-solid fa-bars"></i></button>
    <div class="noidung_dropdown">
        <a href="HostelRenterPage">Thông tin phòng</a>
        <a href="get-roommate-infor">Xem thành viên</a>
        <a href="AddRenterRoommatePage">Thêm thành viên</a>
        <a href="ContractPage">Hợp đồng</a>
        <a href="renter-invoice">Hóa đơn</a>
        <a href="Renter-report">Gửi báo cáo</a>
        <a href="Get-report">Xem báo cáo</a>
        <a href="RenterNotificationPage">Thông báo</a>
        <a href="HostelRenterProfilePage?<%=account.getAccId()%>">Thông tin cá nhân</a>
        <a href="logout">Đăng xuất</a>
    </div>
</div>

