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

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

    <!-- Navbar -->
    <%@include file="components/navbar.jsp"%>

    <c:if test="${requestScope.RESPONSE_MSG eq null}">
        <div id="preloader">
            <div class="dots">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>
    </c:if>

    <!-- Body -->
    <div class="container min-height">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="components/sidebar.jsp"%>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
                <div class="content-history">
                    <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                    <i class="fa-solid fa-chevron-right"></i>
                    <div class="current">Thêm khu trọ</div>
                </div>
                <div class="row mb-5">
                    <div class="content-body col-12 col-md-10 col-lg-9 col-xl-8 m-auto">
                        <form action="add-new-hostel" method="POST" class="custom-form add-hostel-form" id="add-hostel-form">
                            <div class="form-header">
                                <div class="form-title main-title">Thêm khu trọ mới</div>
                            </div>
                            <div class="spacer"></div>
                            <div class="form-group">
                                <label for="hostel-name" class="form-label">Tên: <span>*</span></label>
                                <input id="hostel-name" name="hostel-name" type="text" class="form-control" placeholder="Điền tên khu trọ">
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group">
                                <label for="hostel-address" class="form-label">Địa chỉ: <span>*</span></label>
                                <input id="hostel-address" name="hostel-address" type="text" class="form-control" placeholder="Điền số nhà, tên đường">
                                <span class="form-message"></span>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-province" class="form-label">Tỉnh/TP: <span>*</span></label>
                                        <select name="hostel-province" id="hostel-province"
                                            class="form-control form-select">
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-district" class="form-label">Quận/Huyện: <span>*</span></label>
                                        <select name="hostel-district" id="hostel-district"
                                            class="form-control form-select" disabled>
                                            <option value="">Chọn quận/huyện</option>
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="hostel-ward" class="form-label">Phường/Xã: <span>*</span></label>
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
                                    <div class="col-12 col-md-10 m-auto">
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-electric" class="form-label fill-label">Điện: <span>*</span></label>
                                                <input id="hostel-electric" name="hostel-electric" value="3500" placeholder="Nhập giá"
                                                    type="number" class="form-control fill-input">
                                                <span class="fill-unit">VNĐ/Kwh</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-water" class="form-label fill-label">Nước: <span>*</span></label>
                                                <input id="hostel-water" value="15000" type="number" placeholder="Nhập giá"
                                                    class="form-control fill-input" name="hostel-water">
                                                <span class="fill-unit">VNĐ/m3</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-wifi" class="form-label fill-label">Wifi: <span>*</span></label>
                                                <input id="hostel-wifi" name="hostel-wifi" value="100000" type="number" placeholder="Nhập giá"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">VNĐ/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-manage" class="form-label fill-label">Phí quản lý: <span>*</span></label>
                                                <input id="hostel-manage" name="hostel-manage" value="30000" type="number" placeholder="Nhập giá"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">VNĐ/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-vehicle" class="form-label fill-label">Phí giữ xe: <span>*</span></label>
                                                <input id="hostel-vehicle" name="hostel-vehicle" value="10000" type="number" placeholder="Nhập giá"
                                                    class="form-control fill-input">
                                                <span class="fill-unit">VNĐ/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="form-group price-service-fill">
                                            <div class="fill-group">
                                                <label for="hostel-cleaning" class="form-label fill-label">Phí vệ sinh: <span>*</span></label>
                                                <input id="hostel-cleaning" name="hostel-cleaning" value="10000" placeholder="Nhập giá"
                                                    type="number" class="form-control fill-input">
                                                <span class="fill-unit">VNĐ/phòng</span>
                                            </div>
                                            <span class="form-message"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="spacer"></div>
                            <div class="add-hostel-action">
                                <a href="list-hostels" class="form-submit">Hủy bỏ</a>
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
                Validator.minNumber('#hostel-electric', 1, 'Vui lòng nhập giá điện tối thiểu là 1'),
                Validator.maxNumber('#hostel-electric', maxNumber, 'Vui lòng nhập giá điện nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-electric', 'Vui lòng nhập đúng định dạng số nguyên'),
                Validator.isRequired('#hostel-water', 'Vui lòng nhập giá nước'),
                Validator.minNumber('#hostel-water', 1, 'Vui lòng nhập giá nước tối thiểu là 1'),
                Validator.maxNumber('#hostel-water', maxNumber, 'Vui lòng nhập giá nước nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-water', 'Vui lòng nhập đúng định dạng số nguyên'),
                Validator.isRequired('#hostel-wifi', 'Vui lòng nhập giá wifi'),
                Validator.minNumber('#hostel-wifi', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-wifi', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-wifi', 'Vui lòng nhập đúng định dạng số nguyên'),
                Validator.isRequired('#hostel-manage', 'Vui lòng nhập phí quản lý'),
                Validator.minNumber('#hostel-manage', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-manage', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-manage', 'Vui lòng nhập đúng định dạng số nguyên'),
                Validator.isRequired('#hostel-vehicle', 'Vui lòng nhập phí giữ xe'),
                Validator.minNumber('#hostel-vehicle', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-vehicle', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-vehicle', 'Vui lòng nhập đúng định dạng số nguyên'),
                Validator.isRequired('#hostel-cleaning', 'Vui lòng nhập phí vệ sinh'),
                Validator.minNumber('#hostel-cleaning', minNumber, 'Vui lòng nhập giá bắt đầu từ 0'),
                Validator.maxNumber('#hostel-cleaning', maxNumber, 'Vui lòng nhập giá nhỏ hơn 1000000'),
                Validator.isInteger('#hostel-cleaning', 'Vui lòng nhập đúng định dạng số nguyên'),
            ]
        });
    </script>

<c:choose>
    <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq true}">
        <!-- Alert Modal -->
        <div class="modal fade" id="alert-modal" tabindex="-1" aria-labelledby="alert-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-success" id="alert-modal-label">Thành công</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body pt-5 pb-5">
                            ${requestScope.RESPONSE_MSG.content} Bạn có muốn thêm phòng cho khu trọ ngay bây giờ không?
                    </div>
                    <div class="modal-footer justify-content-between">
                        <a href="list-hostels" class="btn btn-secondary">Để sau</a>
                        <a href="addRoom?hostelID=${requestScope.HOSTEL_ID}" class="btn btn-primary">Thêm phòng ngay</a>
                    </div>
                </div>
            </div>
        </div>

        <script>
            new bootstrap.Modal(document.getElementById('alert-modal')).show();
        </script>
    </c:when>
    <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq false}">
        <!-- Alert Modal -->
        <div class="modal fade" id="alert-modal" tabindex="-1" aria-labelledby="alert-modal-label" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-danger" id="alert-modal-label">Thất bại</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body pt-5 pb-5">
                            ${requestScope.RESPONSE_MSG.content}
                    </div>
                    <div class="modal-footer justify-content-between">
                        <a href="list-hostels" class="btn btn-secondary">Quay về</a>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Thử lại</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            new bootstrap.Modal(document.getElementById('alert-modal')).show();
        </script>
    </c:when>
</c:choose>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>

</body>

</html>