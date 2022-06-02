<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-side-bar" class="side-bar pt-5">
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "dashboard" ? "active" : ""}">
        <a href="" class="group-option__link">
            <i class="group-option__icon fa-solid fa-gauge-high"></i>
            <div class="group-option__label">Tổng quan</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "hostel" ? "active" : ""}">
        <a href="AddRoomPage" class="group-option__link">
            <i class="group-option__icon fa-solid fa-hotel"></i>
            <div class="group-option__label">Khu trọ</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "room" ? "active" : ""}">
        <a href="RoomDetailPage" class="group-option__link">
            <i class="group-option__icon fa-solid fa-door-open"></i>
            <div class="group-option__label">Phòng trọ</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "notification" ? "active" : ""}">
        <a href="" class="group-option__link">
            <i class="group-option__icon fa-solid fa-envelope-open-text"></i>
            <div class="group-option__label">Thông báo</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "report" ? "active" : ""}">
        <a href="" class="group-option__link">
            <i class="group-option__icon fa-solid fa-triangle-exclamation"></i>
            <div class="group-option__label">Báo cáo</div>
        </a>
    </div>
</div>