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
            <div class="group-option__label">Tài khoản</div>
        </a>
    </div>
</div>
