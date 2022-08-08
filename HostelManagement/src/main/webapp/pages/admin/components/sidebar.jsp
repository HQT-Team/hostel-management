<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-side-bar" class="side-bar pt-5">
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "dashboard" ? "active" : ""}">
        <a href="dashboard" class="group-option__link">
            <i class="group-option__icon fa-solid fa-gauge-high"></i>
            <div class="group-option__label">Tổng quan</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "account" ? "active" : ""}">
        <a href="list-owner-account" class="group-option__link">
            <i class="group-option__icon fa-solid fa-user-gear"></i>
            <div class="group-option__label">Quản lý tài khoản</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "propose" ? "active" : ""}">
        <a href="manage-propose" class="group-option__link">
            <i class="group-option__icon fa-solid fa-lightbulb"></i>
            <div class="group-option__label">Đề xuất từ người dùng</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "service" ? "active" : ""}">
        <a href="manage-service" class="group-option__link">
            <i class="group-option__icon fa-brands fa-servicestack"></i>
            <div class="group-option__label">Quản lý dịch vụ</div>
        </a>
    </div>
    <div class="group-option ${sessionScope.CURRENT_PAGE eq "infrastructure" ? "active" : ""}">
        <a href="manage-infrastructure" class="group-option__link">
            <i class="group-option__icon fa-solid fa-cubes"></i>
            <div class="group-option__label">Quản lý cơ sở vật chất</div>
        </a>
    </div>
</div>
