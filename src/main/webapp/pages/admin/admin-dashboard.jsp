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
    <title>Tổng quan</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/admin-dashboard/style.css">

</head>

<body class="over-flow-hidden">

<div id="preloader">
    <div class="dots">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

<div class="app">

    <!-- Navbar -->
    <%@include file="./components/navbar.jsp"%>

    <!-- Body -->
    <div class="container">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="./components/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- Welcome -->
                <div class="content-welcome mt-5">
                    <img src="./assets/images/avatars/user-avatar.jpg" alt="User avatar" class="welcome-image">
                    <div class="welcome-content">
                        <div class="welcome-title">Chào mừng, <span>${sessionScope.USER.accountInfo.information.fullname}</span></div>
                        <p class="welcome-paragraph">Quản trị hệ thống thật là dễ :3</p>
                    </div>
                </div>

                <div class="content__body">
                    <!-- Content Number -->
                    <div class="row">

                        <!-- Total Account Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Tổng tài khoản
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                ${requestScope.totalAccountOwner}
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-users fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- New Register Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Đăng ký mới (${requestScope.DateNow})
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                ${requestScope.totalNewAccountInRecentMonth}
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-user-plus fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Handled Requests Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                Phản hồi đã xử lý
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                                                        ${requestScope.percenProposeListAccepted}%
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="progress progress-sm mr-2">
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                             style="width: ${requestScope.percenProposeListAccepted}%" aria-valuenow="50" aria-valuemin="0"
                                                             aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                Phản hồi đang đợi
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.proposeListWaiting}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-lg-6 mb-4">
                            <!-- Illustrations -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-4">
                                    <h6 class="m-0 font-weight-bold text-primary">
                                        Minh họa
                                    </h6>
                                </div>
                                <div class="card-body">
                                    <div class="text-center">
                                        <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
                                             src="./assets/images/banners/illustration-2.svg" alt="...">
                                    </div>
                                    <p>
                                        Ngày càng có nhiều nền
                                        <a target="_blank" rel="nofollow" href="https://www.google.com/">
                                            khoa học thế giới thứ ba
                                        </a>
                                        và những người được đào tạo về công nghệ học đang hướng
                                        đến các quốc gia thịnh vượng hơn với mức lương cao hơn
                                        và điều kiện làm việc tốt hơn.
                                    </p>
                                    <a target="_blank" rel="nofollow" href="https://www.google.com/">
                                        Đọc thêm &rarr;
                                    </a>
                                </div>
                            </div>
                            <!-- Approach -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-4">
                                    <h6 class="m-0 font-weight-bold text-primary">
                                        Phương pháp tiếp cận phát triển
                                    </h6>
                                </div>
                                <div class="card-body">
                                    <p class="mb-0">
                                        HQT Team tự hào là doanh nghiệp hàng đầu trong tư vấn, nghiên cứu và phát
                                        triển các giải
                                        pháp cho doanh nghiệp. Chúng tôi tự tin có thể mang đến cho bạn một bộ phần
                                        mềm quản lý nhà
                                        trọ hoàn toàn giống như chúng tôi đã quảng cáo.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!-- Content Column -->
                        <div class="col-lg-6 mb-4">
                            <!-- Project Card Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-4">
                                    <h6 class="m-0 font-weight-bold text-primary">
                                        Dự án
                                    </h6>
                                </div>
                                <div class="card-body">
                                    <h4 class="small font-weight-bold">Tiến độ dự án <span
                                            class="float-right">98%</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-danger" role="progressbar" style="width: 98%"
                                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Kiểm thử <span class="float-right">90%</span>
                                    </h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 90%"
                                             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Database <span class="float-right">99%</span>
                                    </h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar" role="progressbar" style="width: 99%"
                                             aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Các màn hình <span
                                            class="float-right">99%</span></h4>
                                    <div class="progress mb-4">
                                        <div class="progress-bar bg-info" role="progressbar" style="width: 99%"
                                             aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <h4 class="small font-weight-bold">Hoàn thiện chức năng <span
                                            class="float-right">Hoàn thành!</span></h4>
                                    <div class="progress">
                                        <div class="progress-bar bg-success" role="progressbar" style="width: 100%"
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./components/footer.jsp"%>

</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>

<!-- Loader -->
<script src="./assets/js/loading-handler.js"></script>
</body>

</html>
