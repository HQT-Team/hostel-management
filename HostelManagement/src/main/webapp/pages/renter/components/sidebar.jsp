<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dashboard hidden" id="dashboard">
    <div class="infor-top">
        <% String[] spilitName = account.getAccountInfo().getInformation().getFullname().split(" ");
            int size = spilitName.length;
        %>
        <img src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg" alt="">
        <p>Người Thuê</p>
        <h3>Xin Chào, <%= spilitName[size-1] %></h3>

    </div>
    <div class="card">
        <div class="card-body">
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetHostelInforServlet" ? "active" : ""}">
                <i class="fa-solid fa-person-shelter"></i>
                <a href="HostelRenterPage">Thông tin phòng</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetRoomateAccountServlet" ? "active": ""}">
                <i class="fa-solid fa-user-group"></i>
                <a href="get-roommate-infor">Xem thành viên</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/AddRenterRoommateServlet" ? "active" : ""}">
                <i class="fa-solid fa-user-plus"></i>
                <a href="AddRenterRoommatePage">Thêm thành viên</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetContractServlet" ? "active": ""}">
                <i class="fa-solid fa-file-contract"></i>
                <a href="ContractPage">Hợp đồng</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetRenterBillServlet" ? "active": ""}">
                <i class="fa-solid fa-file-invoice-dollar"></i>
                <a href="renter-invoice">Hóa đơn</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/AddReportServlet" ? "active": ""}" >
                <i class="fa-solid fa-file-import"></i>
                <a href="Renter-report">Gửi báo cáo</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetReportServlet" ? "active": ""}">
                <i class="fa-solid fa-flag"></i>
                <a href="Get-report">Xem báo cáo</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetNotificationServlet" ? "active": ""}">
                <i class="fa-solid fa-envelope-open-text"></i>
                <a href="RenterNotificationPage">Thông báo</a>
            </div>
            <div class="sidebar-item ${requestScope.uri eq "/HappyHostel/GetRenterInforServlet" ? "active": ""}" id="sidebaritem">
                <i class="fa-solid fa-id-card"></i>
                <a href="HostelRenterProfilePage?<%=account.getAccId()%>">Thông tin cá nhân</a>
            </div>
            <div class="sidebar-item">
                <i class="fa-solid fa-right-from-bracket"></i>
                <a href="logout">Đăng xuất</a>
            </div>
        </div>
    </div>
</div>