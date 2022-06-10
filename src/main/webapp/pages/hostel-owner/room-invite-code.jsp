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
    <title>Thêm khu trọ</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-invite-code-style/style.css">

</head>

<body>
<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

<!-- Body -->
<div class="container min-height">
    <div class="row position-relative">

        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp"%>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
            <div class="content-bar pt-5">
                <div class="content-history">
                    <a href="./hostel.html" class="history-link">Danh sách khu trọ</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <a href="./room-detail.html" class="history-link">NovaLand Sky</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <a href="./room-detail.html" class="history-link">Phòng 11</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <div class="current">Mã tham gia</div>
                </div>
            </div>
            <div class="row mb-5">
                <div class="content-body col-12 col-lg-12 col-xxl-10 m-auto">
                    <div class="invite-header">
                        <div class="invite-countdown">
                            Thời gian hiệu lực còn lại <span id="count-down">00:00</span> phút <br>
                            Trạng thái: <span id="invite-status" class="invite-countdown-success">Còn hiệu
                                    lực</span>
                        </div>
                        <div id="invite-recreate" class="invite-recreate d-none">
                            <form action="">
                                <button class="invite-recreate-btn" type="submit">Tạo mới mã tham gia</button>
                            </form>
                        </div>
                    </div>
                    <div class="invite-group">
                        <div class="row">
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">QR code</div>
                                <div id="invite-content__image" class="invite-content">
                                    <!-- Image here -->
                                </div>
                                <div class="invite-note">Đưa mã này cho người thuê để họ quét và gia nhập hệ thống
                                    ngay lập tức</div>
                            </div>
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">Mã tham gia</div>
                                <div id="invite-content__code"
                                     class="invite-content code-fragment invite-content__code">
                                    <code id="invite-code" class="invite-code">
                                        aLgdsfjOIlkafgjlnsldfkLJKGF430
                                    </code>
                                    <button id="invite-copy__code" class="invite-copy invite-copy__code">Sao
                                        chép</button>
                                </div>
                                <div class="invite-note">Đưa mã này cho người thuê để họ nhập khi đăng
                                    ký tài khoản
                                    trở thành thành viên của ứng dụng</div>
                            </div>
                            <div class="col-md-4 mb-4 mb-md-0">
                                <div class="invite-title">Liên kết trực tiếp</div>
                                <div id="invite-content__link"
                                     class="invite-content code-fragment invite-content__link">
                                    <code id="invite-link" class="invite-url">
                                        https://www.google.com/search?q
                                    </code>
                                    <button id="invite-copy__link" class="invite-copy invite-copy__link">Sao
                                        chép</button>
                                </div>
                                <div class="invite-note">
                                    Đưa liên kết này cho người thuê để nhanh chóng tham gia ứng dụng
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="invite-notices">* Chú ý:</div>
                    <ul class="invite-notices-list">
                        <li class="invite-notices-items">
                            Khi hết thời gian hiệu lực thì QR Code, mã tham gia và liên kết sẽ không thể truy cập
                        </li>
                        <li class="invite-notices-items">
                            Trong một ngày chỉ có thể tạo tối đa 3 lần mã tham gia
                        </li>
                    </ul>
                    <a href="" class="invite-action"><i class="fa-solid fa-arrow-left"></i> Quay về</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp"%>

<!-- Script Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<script src="./assets/js/owner/room-invite-code/handle-copy.js"></script>
<script src="./assets/js/owner/room-invite-code/handle-countdown.js"></script>
<script>
    // Handle encode and render QR image
    const imgBase64Code = 'iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAABe0lEQVR4Xu2XQW6DQAxFHWUxS44wNyEXiwQSF4Ob+AgsZ4Fw/T0RSRO67KeVsBSEeSws5vvbEfsp5P3BFic5CeJvEBWRq5VWJIs0vWcNm/hv0HKbRcrNBrOVToo0yOWebWwGlfYQgrzPaT2OWDJbZK82BjGcTxqR287JEQg0qqX1svzyoV4CqSEXCDUe1pxINLozTX6bpvme445KUJZVpyj+aSRt50MjCcqEU2SIQ/GIS9AWS4ZTIFukqpVJzD2yz3AKHNKSy9anLKLJ5g5O4e+EVp5Vs4jEnBibRaR10453qMSQ48MAu2fwiabVe8OmKo4le6lsUrxP1TV6RcfG2GATi9rM+zShVcqzNhYpl7nzZpUO60MH46ATzCy3KW8QVPni5CQSoXGFSdQ7KlFBbT64HrvLy/mwiMXGMrllYm4/thgqwf6msOp6PmiaI0j0KWr7rlEiEfyTsGl+Uy+HWOywMbdXTAw+CY3GDhtu+a7e3yf7cZKTIP4n+QKa4GQH8+Of1AAAAABJRU5ErkJggg==';
    const image = new Image();
    image.src = `data:image/png;base64,${imgBase64Code}`;
    image.classList.add("invite-qr-img");
    $('#invite-content__image').append(image);

    // Handle Countdown time
    handleCountDown({
        targetDate: "2022-6-10 07:51:00"
    });
</script>

</body>

</html>