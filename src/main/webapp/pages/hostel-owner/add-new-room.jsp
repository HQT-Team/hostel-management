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
    <title>Thêm phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/add-new-room-style/style.css">

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

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
        <!-- Sidebar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp"%>
        </div>
        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 content-group">
            <!-- Content head bar -->
            <div class="content-history">
                <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a href="detailHostel?hostelID=${requestScope.hostel.hostelID}" class="history-link">${requestScope.hostel.hostelName}</a>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Thêm phòng</div>
            </div>
            <!-- Form -->
            <div class="row mb-5">
                <div class="content-body col-12 col-md-10 col-lg-9 col-xl-8 col-xxl-6 m-auto">
                    <!-- Form body -->
                    <form action="addRoom" method="post" class="custom-form add-room-form" id="add-hostel-form">
                        <!-- Title -->
                        <div class="form-header">
                            <div class="form-title main-title">Thêm phòng mới</div>
                        </div>
                        <!-- Warning -->
                        <div class="form-warning">
                            <p><span>*</span> Để tạo cùng lúc nhiều phòng, hãy thay đổi số lượng
                                phòng cần tạo,
                                mặc định để 1 là tạo 1 phòng!</p>
                            <p><span>*</span> Khi tạo nhiều phòng cùng lúc, tên phòng sẽ được tạo ngẫu nhiên,
                                bạn có thể đổi tên phòng sau này!</p>
                        </div>
                        <div class="spacer"></div>
                        <!-- Input -->
                        <div class="form-group">
                            <div class="form-wrapper">
                                <label for="room-quantity" class="form-label">Số lượng: <span>*</span></label>
                                <input id="room-quantity" name="room-quantity" type="number" value="1"
                                       class="form-control">
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="form-wrapper">
                                <label for="room-name" class="form-label">Phòng số: </label>
                                <input id="room-name" name="room-name" type="number" class="form-control"
                                       placeholder="Phòng số ...">
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="form-wrapper">
                                <label for="room-capacity" class="form-label">Số lượng thành viên tối đa:
                                    <span>*</span></label>
                                <input id="room-capacity" name="room-capacity" type="number" value="1"
                                       class="form-control">
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="form-wrapper">
                                <label for="room-area" class="form-label fill-label">
                                    Diện tích <span>*</span></label>
                                <input id="room-area" name="room-area" value="20" type="number"
                                       class="form-control">
                                <span class="form-unit d-block text-end" style="width: 34px;">m2</span>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <div class="form-wrapper">
                                <label for="room-floor" class="form-label fill-label">
                                    Gác <span>*</span>
                                </label>
                                <select id="room-floor" class="form-control fill-input"
                                        name="room-floor">
                                    <option value="1" selected>Có</option>
                                    <option value="0">Không</option>
                                </select>
                                <span class="fill-unit"></span>
                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="spacer"></div>
                        <div class="infrastructure-group">
                            <div class="form-header">
                                <div class="form-title infrastructure-title">Cơ sở vật chất</div>
                            </div>
                            <div class="form-group infrastructure-fill">
                                <div class="infrastructure-wrapper">
                                    <label for="room-toilet" class="form-label fill-label">Nhà vệ
                                        sinh:</label>
                                    <input id="room-toilet" name="room-toilet" value="1" type="number"
                                           class="form-control fill-input">
                                    <span class="fill-unit">cái</span>
                                    <select name="room-toilet-status" class="fill-status">
                                        <option value="1" selected>Sử dụng tốt</option>
                                        <option value="0">Hư hỏng</option>
                                    </select>
                                </div>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group infrastructure-fill">
                                <div class="infrastructure-wrapper">
                                    <label for="room-window" class="form-label fill-label">Cửa
                                        sổ:</label>
                                    <input id="room-window" name="room-window" value="1" type="number"
                                           class="form-control fill-input">
                                    <span class="fill-unit">cái</span>
                                    <select name="room-window-status" class="fill-status">
                                        <option value="1" selected>Sử dụng tốt</option>
                                        <option value="0">Hư hỏng</option>
                                    </select>
                                </div>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group infrastructure-fill">
                                <div class="infrastructure-wrapper">
                                    <label for="room-door" class="form-label fill-label">Cửa ra
                                        vào</label>
                                    <input id="room-door" name="room-door" value="1" type="number"
                                           class="form-control fill-input">
                                    <span class="fill-unit">cái</span>
                                    <select name="room-door-status" class="fill-status">
                                        <option value="1" selected>Sử dụng tốt</option>
                                        <option value="0">Hư hỏng</option>
                                    </select>
                                </div>
                                <span class="form-message"></span>
                            </div>
                            <div class="form-group infrastructure-fill">
                                <div class="infrastructure-wrapper">
                                    <label for="room-air-conditioner" class="form-label fill-label">Máy
                                        lạnh</label>
                                    <input id="room-air-conditioner" name="room-air-conditioner" value="1"
                                           type="number" class="form-control fill-input">
                                    <span class="fill-unit">cái</span>
                                    <select name="room-air-conditioner-status" class="fill-status">
                                        <option value="1" selected>Sử dụng tốt</option>
                                        <option value="0">Hư hỏng</option>
                                    </select>
                                </div>
                                <span class="form-message"></span>
                            </div>
                        </div>
                        <div class="spacer"></div>
                        <!-- Action -->
                        <div class="add-room-action">
                            <a href="detailHostel?hostelID=${requestScope.hostel.hostelID}" class="form-submit">Hủy bỏ</a>
                            <input type="hidden" name="hostelID" value="${requestScope.hostel.hostelID}">
                            <button class="form-submit">Tạo phòng</button>
                        </div>
                    </form>
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
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<script src="./assets/js/valid-form.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<script>

    Validator({
        form: '#add-hostel-form',
        formGroupSelector: '.form-group',
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired('#room-quantity', 'Vui lòng nhập số lượng phòng cần tạo'),
            Validator.minNumber('#room-quantity', 1, 'Vui lòng nhập số lượng tối thiểu là 1'),
            Validator.maxNumber('#room-quantity', 50, 'Vui lòng nhập số lượng dưới 50'),
            Validator.isInteger('#room-quantity', 'Số lượng phòng phải là số nguyên'),
            Validator.isRequired('#room-name', 'Vui lòng nhập phòng số'),
            Validator.isRequired('#room-capacity', 'Vui lòng nhập số lượng thành viên tối đa'),
            Validator.minNumber('#room-capacity', 1, 'Vui lòng nhập số lượng tối thiểu là 1'),
            Validator.maxNumber('#room-capacity', 10, 'Vui lòng nhập số lượng dưới 10'),
            Validator.isInteger('#room-capacity', 'Số lượng người phải là số nguyên'),
            Validator.maxNumber('#room-area', 1000, 'Vui lòng nhập giá trị dưới 1000'),
            Validator.minNumber('#room-area', 1, 'Vui lòng nhập giá trị tối thiểu là 1'),
            Validator.minNumber('#room-toilet', 0, 'Vui lòng nhập giá trị tối thiểu là 0'),
            Validator.isInteger('#room-toilet', 'Số lượng phải là số nguyên'),
            Validator.minNumber('#room-window', 0, 'Vui lòng nhập giá trị tối thiểu là 0'),
            Validator.isInteger('#room-window', 'Số lượng phải là số nguyên'),
            Validator.minNumber('#room-door', 0, 'Vui lòng nhập giá trị tối thiểu là 0'),
            Validator.isInteger('#room-door', 'Số lượng phải là số nguyên'),
            Validator.minNumber('#room-air-conditioner', 0, 'Vui lòng nhập giá trị tối thiểu là 0'),
            Validator.isInteger('#room-air-conditioner', 'Số lượng phải là số nguyên'),
        ]
    });

    function getParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    const inputName = document.querySelector('#room-name');
    const errorElement = getParent(inputName, ".form-group").querySelector('.form-message');

    document.querySelector('#room-quantity').addEventListener('change', (e) => {
        if (e.target.value != '1') {
            inputName.setAttribute("disabled", "true");
            inputName.value = "0";
            errorElement.innerHTML = "";
        } else {
            inputName.removeAttribute("disabled");
            inputName.value = "";
        }
    })
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
                        ${requestScope.RESPONSE_MSG.content}
                    </div>
                    <div class="modal-footer justify-content-between">
                        <a href="detailHostel?hostelID=${requestScope.hostel.hostelID}" class="btn btn-secondary">Quay về khu trọ</a>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Thêm tiếp</button>
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
                        <a href="detailHostel?hostelID=${requestScope.hostel.hostelID}" class="btn btn-secondary">Quay về khu trọ</a>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Thêm lại</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            new bootstrap.Modal(document.getElementById('alert-modal')).show();
        </script>
    </c:when>
</c:choose>

<script type="text/javascript">
    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function () {
        receiveWebsocket.disconnectWebSocket();
    };
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>

</body>

</html>