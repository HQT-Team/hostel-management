<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-nav bg-white">
    <div class="container">
        <div class="row main-nav-body">
            <div class="col-3 col-lg-3 col-xl-3 col-xxl-2">
                <div class="main-nav__logo">
                    <a href="dashboard" class="main-nav__logo-link">
                        <img class="main-nav__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                    </a>
                </div>
            </div>
            <div class="col-9 col-lg-9 col-xl-9 col-xxl-10 wrapper">
                <div class="main-nav__label">
                    <h3 class="title">
                        <c:choose>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'dashboard'}">
                                Tổng quan
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'account'}">
                                Quản lý tài khoản
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'propose'}">
                                Quản lý đề xuất người dùng
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'service'}">
                                Quản lý dịch vụ khu trọ
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'infrastructure'}">
                                Quản lý cơ sở vật chất phòng
                            </c:when>
                        </c:choose>
                    </h3>
                </div>
                <div class="main-nav__action">
                    <div id="nav-profile-btn" class="profile">
                        <div class="profile__infor">
                            <h3 class="infor__name">${sessionScope.USER.accountInfo.information.fullname}</h3>
                            <span class="infor__role">Quản trị viên</span>
                        </div>
                        <div class="profile__avatar">
                            <img class="avatar__img" src="./assets/images/avatars/user-avatar.jpg"
                                 alt="User avatar image">
                        </div>
                    </div>
                    <div id="menu-sidebar-btn" class="menu-sidebar-btn">
                        <i class="fa-solid fa-bars"></i>
                    </div>
                </div>
            </div>

            <!-- Profile dropdown -->
            <div id="nav-profile-dropdown" class="profile__actions">
<%--                <a href="" class="action__view-profile-link">--%>
<%--                    <div class="action__image">--%>
<%--                        <img src="./assets/images/avatars/user-avatar.jpg" alt="">--%>
<%--                    </div>--%>
<%--                    <div class="action__content">--%>
<%--                        <div class="title">${sessionScope.USER.accountInfo.information.fullname}</div>--%>
<%--                        <span class="subtitle">Xem trang cá nhân của bạn</span>--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--                <div class="spacer"></div>--%>
                <a href="logout" class="action__logout">
                    <div class="action__image">
                        <i class="fa-solid fa-arrow-right-from-bracket"></i>
                    </div>
                    <div class="action__content">
                        <div class="title">Đăng xuất</div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
