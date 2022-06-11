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
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/add-new-hostel-style/style.css">

</head>

<body>
    <!-- Navbar -->
    <%@include file="components/navbar.jsp"%>

    <!-- Body -->
    <div class="container min-height">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="components/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
                <div class="content-bar pt-5">
                    <div class="content-history">
                        <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <div class="current">Thêm khu trọ</div>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="content-body col-12 col-md-10 col-lg-9 col-xl-8 m-auto">
                        <form action="add-new-hostel" method="POST" class="custom-form add-hostel-form" id="add-hostel-form">
                            <div class="form-header">
                                <div class="form-title main-title">Thêm khu trọ mới</div>
                            </div>
                            <div class="spacer"></div>
                            <div class="form-group">
                                <label for="hostel-name" class="form-label">Tên:</label>
                                <input id="hostel-name" name="hostel-name" type="text" class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="hostel-address" class="form-label">Địa chỉ:</label>
                                <input id="hostel-address" name="hostel-address" type="text" class="form-control">
                                <span class="form-message"></span>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-province" class="form-label">Tỉnh/TP:</label>
                                        <select name="hostel-province" id="hostel-province"
                                            class="form-control form-select">
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-district" class="form-label">Quận/Huyện:</label>
                                        <select name="hostel-district" id="hostel-district"
                                            class="form-control form-select" disabled>
                                            <option value="">Chọn quận/huyện</option>
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-ward" class="form-label">Phường/Xã:</label>
                                        <select name="hostel-ward" id="hostel-ward" class="form-control form-select"
                                            disabled>
                                            <option value="">Chọn phường/xã</option>
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="spacer"></div>
                            <div class="price-service-group">
                                <div class="form-header">
                                    <div class="form-title price-service-title">Giá dịch vụ</div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-md-8 m-auto">
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-electric" class="form-label fill-label">Điện:</label>
                                                <input id="hostel-electric" name="hostel-electric" value="0"
                                                    type="number" class="form-control fill-input">
                                                <span class="fill-unit">/KwH</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-water" class="form-label fill-label">Nước:</label>
                                                <input id="hostel-water" value="0" type="number"
                                                    class="form-control fill-input" name="hostel-water">
                                                <span class="fill-unit">/m3</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-wifi" class="form-label fill-label">Wifi:</label>
                                                <input id="hostel-wifi" name="hostel-wifi" value="0" type="number"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-manage" class="form-label fill-label">Phí quản
                                                    lý:</label>
                                                <input id="hostel-manage" name="hostel-manage" value="0" type="number"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-vehicle" class="form-label fill-label">Phí giữ
                                                    xe:</label>
                                                <input id="hostel-vehicle" name="hostel-vehicle" value="0" type="number"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">/chiếc</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-cleaning" class="form-label fill-label">Phí vệ
                                                    sinh:</label>
                                                <input id="hostel-cleaning" name="hostel-cleaning" value="0"
                                                    type="number" class="form-control fill-input">
                                                <span class="fill-unit">/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="spacer"></div>
                            <div class="add-hostel-action">
                                <a href="ShowListHostel" class="form-submit">Hủy bỏ</a>
                                <button class="form-submit">Tạo phòng</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="components/footer.jsp"%>

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
    <script src="./assets/js/handle-address.js"></script>
    <script src="./assets/js/valid-form.js"></script>
    <script>
        let maxNumber = 1000000;
        let minNumber = 0;

        Validator({
            form: '#add-hostel-form',
            formGroupSelector: '.form-group',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#hostel-name', 'Vui lòng nhập tên của khu trọ'),
                Validator.isRequired('#hostel-address', 'Vui lòng nhập địa chỉ của khu trọ'),
                Validator.isRequired('#hostel-province', 'Vui lòng chọn tỉnh/thành phố'),
                Validator.isRequired('#hostel-district', 'Vui lòng chọn quận/huyện'),
                Validator.isRequired('#hostel-ward', 'Vui lòng chọn phường/xã'),
                Validator.isRequired('#hostel-electric', 'Vui lòng nhập giá điện'),
                Validator.minNumber('#hostel-electric', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-electric', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isRequired('#hostel-water', 'Vui lòng nhập giá nước'),
                Validator.minNumber('#hostel-water', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-water', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isRequired('#hostel-wifi', 'Vui lòng nhập giá wifi'),
                Validator.minNumber('#hostel-wifi', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-wifi', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isRequired('#hostel-manage', 'Vui lòng nhập phí quản lý'),
                Validator.minNumber('#hostel-manage', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-manage', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isRequired('#hostel-vehicle', 'Vui lòng nhập phí giữ xe'),
                Validator.minNumber('#hostel-vehicle', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-vehicle', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isRequired('#hostel-cleaning', 'Vui lòng nhập phí vệ sinh'),
                Validator.minNumber('#hostel-cleaning', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-cleaning', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),

            ]
        });
    </script>

</body>

</html>