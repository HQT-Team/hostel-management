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
    <title>Chi tiết phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/report-detail_style/style.css">

</head>

<body class="over-flow-hidden">
<!-- Loader -->
<div id="preloader">
    <div class="dots">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

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
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
            <!-- History link bar -->
            <div class="content-history">
                <a href="./reports.html" class="history-link">Danh sách báo cáo</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Báo cáo #VA123</div>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9 m-auto">
                <div class="content__body">
                    <div class="report">
                        <h1 class="report__title">Báo cáo #VA123</h1>
                        <div class="report__spacer"></div>
                        <div class="report__info row">
                            <div class="col-6">
                                <div class="report__item">Khu trọ: <span>Nova Land</span></div>
                                <div class="report__item">Phòng số: <span>1</span></div>
                                <div class="report__item">Người đại diện: <span>Nguyễn Văn A</span></div>
                                <div class="report__item">Số điện thoại: <span>0792111222</span></div>
                            </div>
                            <div class="col-6">
                                <div class="report__item">Loại: <span class="red">Hư hỏng cơ sở vật chất</span>
                                </div>
                                <div class="report__item">Trạng thái hiện tại: <span class="notyet">Chưa tiếp
                                            nhận</span></div>
                                <div class="report__item">Ngày gửi: <span>21/02/2022</span></div>
                            </div>
                            <div class="col-12">
                                <div class="report__item">Nội dung: <span>Anh ơi phòng em bị hư ống nước ạ! Anh qua
                                            xem giúp em với nhé! Em có chuẩn bị vài đồ cần thiết cho chúng mình
                                            rùi :3 </span></div>
                            </div>
                        </div>
                        <div class="report__spacer"></div>
                        <div class="report__reply d-none">
                            <div class="report__item">Ngày tiếp nhận: <span>trống</span></div>
                            <div class="report__item">Phản hồi: <span>trống</span></div>
                        </div>
                        <form action="" method="">
                            <div class="form-group">
                                <label for="" class="form-label">Phản hồi: <span>*</span></label>
                                <textarea name="" id="" class="form-control" placeholder="Nhập phản hồi"></textarea>
                            </div>
                            <div class="report__spacer"></div>
                            <div class="report__action d-flex justify-content-between">
                                <a href="" class="btn btn-outline-dark">Quay lại</a>
                                <button class="btn btn-danger">Xác nhận và xử lý báo cáo</button>
                            </div>
                        </form>
                        <div class="report__spacer d-none"></div>
                        <div class="report__finish d-none">
                            <div class="report__item">Ngày hoàn thành: <span>trống</span></div>
                        </div>
                        <form action="" method="" class="d-none">
                            <div class="report__spacer"></div>
                            <div class="report__action d-flex justify-content-between">
                                <a href="./reports.html" class="btn btn-outline-dark">Quay lại</a>
                                <button class="btn btn-danger">Hoàn thành báo cáo</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp"%>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Link your script here -->


<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
