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
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'hostel'}">
                                Khu trọ
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'room'}">
                                Phòng trọ
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'notification'}">
                                Thông báo
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'report'}">
                                Báo cáo
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'invoice'}">
                                Hóa đơn
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'statistic'}">
                                Thống kê
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'account'}">
                                Tài khoản
                            </c:when>
                            <c:when test="${sessionScope.CURRENT_PAGE eq 'propose'}">
                                Phản hồi/Đóng góp
                            </c:when>
                        </c:choose>
                    </h3>
                </div>
                <div class="main-nav__action">
                    <div id="nav-notification-btn" class="notification">
                        <i class="notification__icon fa-solid fa-bell"></i>

                        <!-- Remove class "active" when don't have any new notification -->
                        <span class="notification__warning active"><i class="fa-solid fa-exclamation"></i></span>
                    </div>
                    <div id="nav-profile-btn" class="profile">
                        <div class="profile__infor">
                            <h3 class="infor__name">${sessionScope.USER.accountInfo.information.fullname}</h3>
                            <span class="infor__role">Chủ phòng trọ</span>
                        </div>
                        <div class="profile__avatar">
                            <img class="avatar__img" src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg"
                                 alt="User avatar">
                        </div>
                    </div>
                    <div id="menu-sidebar-btn" class="menu-sidebar-btn">
                        <i class="fa-solid fa-bars"></i>
                    </div>
                </div>
            </div>

            <!-- List notification -->
            <div id="notification-list" class="notification__list">
                <div class="notification__list-header">
                    <h3>Thông báo</h3>
                    <a href="#">Xem tất cả</a>
                </div>
                <ul class="notification__list-items">
                    <!-- If this notification has been read, please add more class "readed" -->
                    <li class="notification__item">
                        <a href="#">
                            <div class="marker"></div>
                            <div class="notification__item-img">
                                <i class="notification__item-icon fa-solid fa-circle-exclamation"></i>
                            </div>
                            <div class="notification__item-info">
                                <div class="notification__item-name">
                                    <div class="room">
                                        Đang cập nhật
                                    </div>
                                    <div class="hostel">
                                        <span>Khu trọ:</span> Đang cập nhật
                                    </div>
                                </div>
                                <div class="notification__item-content">
                                    Tính năng đang được phát triển! Vui lòng quay lại sau bạn nhé! <3
                                </div>
                                <div class="notification__item-time">
                                    1 phút trước
                                </div>
                            </div>
                        </a>
                    </li>
                <!--    <li class="notification__item">
                        <a href="">
                            <div class="marker"></div>
                            <div class="notification__item-img">
                                <i class="notification__item-icon fa-solid fa-circle-exclamation"></i>
                            </div>
                            <div class="notification__item-info">
                                <div class="notification__item-name">
                                    <div class="room">
                                        Phòng số 11
                                    </div>
                                    <div class="hostel">
                                        <span>Khu trọ:</span> Nova land
                                    </div>
                                </div>
                                <div class="notification__item-content">
                                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                    qua sửa chữa giúp em với ạ!
                                </div>
                                <div class="notification__item-time">
                                    18 tiếng trước
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="notification__item readed">
                        <a href="">
                            <div class="marker"></div>
                            <div class="notification__item-img">
                                <i class="notification__item-icon success fa-solid fa-circle-exclamation"></i>
                            </div>
                            <div class="notification__item-info">
                                <div class="notification__item-name">
                                    <div class="room">
                                        Phòng số 11
                                    </div>
                                    <div class="hostel">
                                        <span>Khu trọ:</span> Nova land
                                    </div>
                                </div>
                                <div class="notification__item-content">
                                    Anh ơi bóng đèn trong nhà vệ sinh phòng em bị hư, mong anh sớm
                                    qua sửa chữa giúp em với ạ!
                                </div>
                                <div class="notification__item-time">
                                    18 tiếng trước
                                </div>
                            </div>
                        </a>
                    </li> -->
                </ul>
            </div>

            <!-- Profile dropdown -->
            <div id="nav-profile-dropdown" class="profile__actions">
                <a href="profile" class="action__view-profile-link">
                    <div class="action__image">
                        <img src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg" alt="User avatar">
                    </div>
                    <div class="action__content">
                        <div class="title">${sessionScope.USER.accountInfo.information.fullname}</div>
                        <span class="subtitle">Xem trang cá nhân của bạn</span>
                    </div>
                </a>
                <div class="spacer"></div>
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