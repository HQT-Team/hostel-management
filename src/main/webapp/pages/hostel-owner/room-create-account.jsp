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
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-create-account-style/style.css">

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
                    <div class="current">Tạo tài khoản</div>
                </div>
            </div>
            <div class="row mb-5">
                <div class="content-body col-12 col-md-10 col-lg-9 col-xl-6 m-auto">
                    <form actions="" method="post" class="custom-form create-room-account-form"
                          id="create-room-account-form">
                        <div class="form-header">
                            <div class="form-title main-title">Tạo tài khoản</div>
                        </div>
                        <div class="spacer"></div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-username" class="form-label">Tên tài khoản:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-username" name="room-username" type="text"
                                           class="form-control m-0" placeholder="Nhập tên tài khoản truy cập phòng">
                                </div>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-fee" class="form-label">Tiền phòng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-fee" name="room-fee" type="number" class="form-control m-0"
                                           placeholder="Nhập số tiền phòng">
                                </div>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-deposit" class="form-label">Tiền cọc:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-deposit" name="room-deposit" type="number"
                                           class="form-control m-0" placeholder="Nhập số tiền cọc cho phòng">
                                </div>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-startdate" class="form-label">Ngày bắt đầu hợp đồng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-startdate" name="room-startdate" type="date"
                                           class="form-control m-0">
                                </div>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="row align-items-center">
                                <div class="col-6">
                                    <label for="room-enddate" class="form-label">Ngày kết thúc hợp đồng:
                                        <span>*</span></label>
                                </div>
                                <div class="col-6">
                                    <input id="room-enddate" name="room-enddate" type="date"
                                           class="form-control m-0" placeholder="">
                                </div>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="spacer"></div>
                        <div class="create-room-account-actions">
                            <a href="./hostel.html" class="form-submit">Hủy bỏ</a>
                            <button class="form-submit">Xác nhận và tạo mã tham gia</button>
                        </div>
                    </form>
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
<script src="./assets/js/valid-form.js"></script>
<script>
    Validator({
        form: '#create-room-account-form',
        formGroupSelector: '.form-group',
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired('#room-username', 'Vui lòng nhập trường này'),
            Validator.isUsername('#room-username'),
            Validator.maxLength('#room-username', 64, 'Tên tài khoản dài tối đa 64 kí tự'),
            Validator.isRequired('#room-deposit', 'Vui lòng nhập trường này'),
            Validator.minNumber('#room-deposit', 1, 'Vui lòng nhập tối thiểu 1'),
            Validator.maxNumber('#room-deposit', 100000000, 'Vui lòng nhập tối đa 100000000'),
            Validator.isRequired('#room-startdate', 'Vui lòng nhập trường này'),
            Validator.isRequired('#room-enddate', 'Vui lòng nhập trường này'),
        ]
    });
</script>

</body>

</html>
