<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Trang chủ</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/admin_home_page.css">
</head>

<body>
    <!-- Navbar -->
    <div class="main-nav bg-white">
        <div class="container">
            <div class="row main-nav-body">
                <div class="col-3 col-lg-3 col-xl-3 col-xxl-2">
                    <div class="main-nav__logo">
                        <a href="" class="main-nav__logo-link">
                            <img class="main-nav__logo-img" src="./assets/images/logos/logo.png" alt="Logo">
                        </a>
                    </div>
                </div>
                <div class="col-9 col-lg-9 col-xl-9 col-xxl-10 wrapper">
                    <div class="main-nav__label">
                        <h3 class="title" id="nav__title">Trang Chủ</h3>
                    </div>
                    <div class="main-nav__action">
                        <div id="nav-notification-btn" class="notification">
                            <i class="notification__icon fa-solid fa-bell"></i>

                            <!-- Remove class "active" when don't have any new notification -->
                            <span class="notification__warning active"><i class="fa-solid fa-exclamation"></i></span>
                        </div>
                        <div id="nav-profile-btn" class="profile">
                            <div class="profile__infor">
                                <h3 class="infor__name">Nguyễn Lâm Thảo Tâm</h3>
                                <span class="infor__role">Chủ phòng trọ</span>
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

                <!-- List notification -->
                <div id="notification-list" class="notification__list">
                    <div class="notification__list-header">
                        <h3>Thông báo</h3>
                        <a href="">Xem tất cả</a>
                    </div>
                    <ul class="notification__list-items">
                        <!-- If this notification has been read, please add more class "readed" -->
                        <li class="notification__item">
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
                        <li class="notification__item">
                            <a href="">
                                <div class="marker"></div>
                                <div class="notification__item-img">
                                    <i class="notification__item-icon report fa-solid fa-circle-exclamation"></i>
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
                        </li>
                        <li class="notification__item">
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
                        <li class="notification__item">
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
                    </ul>
                </div>

                <!-- Profile dropdown -->
                <div id="nav-profile-dropdown" class="profile__actions">
                    <a href="" class="action__view-profile-link">
                        <div class="action__image">
                            <img src="./assets/images/avatars/user-avatar.jpg" alt="">
                        </div>
                        <div class="action__content">
                            <div class="title">Nguyễn Lâm Thảo Tâm</div>
                            <span class="subtitle">Xem trang cá nhân của bạn</span>
                        </div>
                    </a>
                    <div class="spacer"></div>
                    <a href="" class="action__logout">
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




    <!-- Body -->
    <div class="container min-height">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <div id="main-side-bar" class="side-bar pt-5">
                    <div class="group-option">
                        <button onclick="hightlight('content__left--first-button')" id="content__left--first-button"
                            class="group-option__link">
                            <i class="group-option__icon fa-solid fa-gauge-high"></i>
                            <div class="group-option__label" id="content__left--first-button-title">Trang chủ</div>
                        </button>
                    </div>
                    <div>
                        <button onclick="hightlight('content__left--second-button')" id="content__left--second-button"
                            class="group-option__link">
                            <i class="group-option__icon fa-solid fa-hotel"></i>
                            <div class="group-option__label" id="nav__center--title">Tài khoản</div>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">

                <div class="content__right col-md-12 col-lg-12 col-sm-12" id="content__right">
                    <div></div>
                    <h1>Quản Lí Phòng Trọ</h1>
                    <h1 id="content--add">Ngày càng có nhiều nền khoa học thế giới thứ ba và
                        những người được đào tạo về công nghệ học đang hướng
                        đến các quốc gia thịnh vượng hơn với mức lương cao hơn
                        và điều kiện làm việc tốt hơn.</h1>
                    <button class="btn btn-primary">Đọc thêm</button>
                </div>
                <div class="content__img" id="content__img">
                    <img src="https://s3.us-west-2.amazonaws.com/www.bookingninjas.com/img/illustration-2.svg">
                </div>

                <div class="content__right-2 col-md-12 col-lg-12" id="content__right-2" style="margin:auto ;">
                    <button class="button-1" id="button-1" onclick="choose('button-1', 'button-1__title')">
                        <p id="button-1__title">Tài Khoản Chờ Phê Duyệt</p>
                    </button>
                    <button class="button-2" id="button-2" onclick="choose('button-2', 'button-2__title')">
                        <p id="button-2__title">Danh Sách Tài Khoản Chủ Trọ</p>
                    </button>
                    <h1>Danh Sách Tài Khoản</h1>
                    <form action="#" method="post">
                        <input type="text" placeholder="Tên Tài Khoản"><input id="button_search" type="submit"
                            name="action" value="Search">
                    </form>
                    <div>
                        <table class="hide table__approve-account">
                            <tr>
                                <th>STT</th>
                                <th>Username</th>
                                <th>Detail</th>
                                <th>Action</th>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><button id="button_view" type="button" class="btn btn-link" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal">
                                        View
                                    </button></td>
                                <td><a href="#" id="activeBtn" onclick="disablebtn()">Active</a></td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <table class="hide table__view-account">
                            <tr>
                                <th>STT</th>
                                <th>Username</th>
                                <th>Status</th>
                                <th>Detail</th>
                                <th>Action</th>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Unbock</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Detail Information</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h2>Full Name</h2>
                                <h2>Email</h2>
                                <h2>Birth Day</h2>
                                <h2>Sex</h2>
                                <h2>Phone</h2>
                                <h2>Addess</h2>
                                <h2>Identity Card Number</h2>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer style="bottom: 0px;">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="copyright-wrapper d-flex justify-content-center">
                        <div class="copyright-content">© 2022 HQT-Hostel. All rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>



    <!-- Script Bootstrap !important -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <!-- Jquery -->
    <script src="./assets/js/jquery-3.5.1.min.js"></script>
    <!-- Link your script here -->
    <script src="./assets/js/handle-main-navbar.js"></script>
    <script src="./assets/js/admin/admin_home_page.js"></script>

</body>

</html>