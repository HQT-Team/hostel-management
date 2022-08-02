<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-side-bar" class="side-bar pt-5">
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "dashboard" ? "active" : ""}">
        <a href="dashboard" class="group-option__link">
            <i class="group-option__icon fa-solid fa-gauge-high"></i>
            <div class="group-option__label">Tổng quan</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "hostel" ? "active" : ""}">
        <a href="list-hostels" class="group-option__link">
            <i class="group-option__icon fa-solid fa-hotel"></i>
            <div class="group-option__label">Khu trọ</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "room" ? "active" : ""}">
        <a href="getRoomList" class="group-option__link">
            <i class="group-option__icon fa-solid fa-door-open"></i>
            <div class="group-option__label">Phòng trọ</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "notification" ? "active" : ""}">
        <a href="owner-get-notification-list" class="group-option__link">
            <i class="group-option__icon fa-solid fa-envelope-open-text"></i>
            <div class="group-option__label">Thông báo</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "report" ? "active" : ""}">
        <a href="report" class="group-option__link">
            <i class="group-option__icon fa-solid fa-triangle-exclamation"></i>
            <div class="group-option__label">Báo cáo</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "invoice" ? "active" : ""}">
        <a href="getInvoiceList" class="group-option__link">
            <i class="group-option__icon fa-solid fa-file-invoice-dollar"></i>
            <div class="group-option__label">Hóa đơn</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "statistic" ? "active" : ""}">
        <a href="statistic" class="group-option__link">
            <i class="group-option__icon fa-solid fa-chart-simple"></i>
            <div class="group-option__label">Thống kê</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "account" ? "active" : ""}">
        <a href="profile" class="group-option__link">
            <i class="group-option__icon fa-solid fa-user"></i>
            <div class="group-option__label">Tài khoản</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "propose" ? "active" : ""}">
        <a href="propose" class="group-option__link">
            <i class="group-option__icon fa-solid fa-envelopes-bulk"></i>
            <div class="group-option__label">Góp ý hệ thống</div>
        </a>
    </div>
</div>
