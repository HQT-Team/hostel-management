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
    <title>Danh sách khu trọ</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/update-hostel-style/style.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
</head>

<body>

    <div class="app">

        <!-- Navbar -->
        <%@include file="components/navbar.jsp"%>

        <!-- Body -->
        <div class="container">
            <div class="row position-relative">

                <!-- Side bar -->
                <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                    <%@include file="components/sidebar.jsp"%>
                </div>

                <!-- Content -->
                <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
                    <div class="content-history">
                        <a href="list-hostels" class="history-link">Danh sách khu trọ</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <a href="detailHostel?hostelID=${requestScope.HOSTEL.hostelID}" class="history-link">${requestScope.HOSTEL.hostelName}</a>
                        <i class="fa-solid fa-chevron-right"></i>
                        <div class="current">
                            Cập nhật thông tin
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-8 col-xl-7 col-xxl-5 m-auto">
                            <div class="content-body">
                                <form action="UpdateHostelServlet" method="post" class="custom-form form-update-hostel"
                                    id="form-update-hostel">
                                    <!-- Title -->
                                    <div class="form-header">
                                        <div class="form-title main-title">Cập nhật thông tin</div>
                                    </div>
                                    <div class="spacer"></div>
                                    <input type="hidden" name="hostelID" value="${requestScope.HOSTEL.hostelID}" />
                                    <!-- Input -->
                                    <div class="form-group">
                                        <label for="hostel-name" class="form-label">Tên: <span>*</span></label>
                                        <input id="hostel-name" name="hostel-name" type="text" value="${requestScope.HOSTEL.hostelName}"
                                            placeholder="Nhập tên khu trọ" class="form-control">
                                        <span class="form-message"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="hostel-address" class="form-label">Địa chỉ: <span>*</span></label>
                                        <input id="hostel-address" name="hostel-address" value="${requestScope.HOSTEL.address}"
                                            placeholder="Nhập địa chỉ khu trọ" type="text" class="form-control">
                                        <span class="form-message"></span>
                                    </div>
                                    <div class="row">
                                        <div class="col-4">
                                            <div class="form-group">
                                                <label for="hostel-province" class="form-label">Tỉnh/TP:
                                                    <span>*</span></label>
                                                <select name="hostel-province" id="hostel-province"
                                                    class="form-control form-select">
                                                    <option value="${requestScope.HOSTEL.city}">${requestScope.HOSTEL.city.split("-")[1]}</option>
                                                </select>
                                                <span class="form-message"></span>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="form-group">
                                                <label for="hostel-district" class="form-label">Quận/Huyện:
                                                    <span>*</span></label>
                                                <select name="hostel-district" id="hostel-district"
                                                    class="form-control form-select">
                                                    <option value="${requestScope.HOSTEL.district}">${requestScope.HOSTEL.district.split("-")[1]}</option>
                                                </select>
                                                <span class="form-message"></span>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="form-group">
                                                <label for="hostel-ward" class="form-label">Phường/Xã:
                                                    <span>*</span></label>
                                                <select name="hostel-ward" id="hostel-ward"
                                                    class="form-control form-select">
                                                    <option value="${requestScope.HOSTEL.ward}">${requestScope.HOSTEL.ward.split("-")[1]}</option>
                                                </select>
                                                <span class="form-message"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="spacer"></div>
                                    <!-- Action -->
                                    <div class="update-hostel-action">
                                        <a href="list-hostels" class="form-submit">Hủy bỏ</a>
                                        <button type="submit" class="form-submit">Cập nhật</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="components/footer.jsp"%>

        <!-- Push notification element -->
        <div id="push-noti"></div>

    </div>

    <!-- Script Bootstrap !important -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <!-- JQuery -->
    <script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <!-- Link your script here -->
    <script src="./assets/js/handle-main-navbar.js"></script>
    <!-- Axios -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="./assets/js/owner/update-hostel/handle-address.js"></script>
    <script src="./assets/js/valid-form.js"></script>
    <!-- Push notification -->
    <script src="./assets/js/push-notification-alert.js"></script>
    <!-- Web socket -->
    <script src="./assets/js/receiveWebsocket.js"></script>

    <script>
        let maxNumber = 1000000;
        let minNumber = 0;

        Validator({
            form: '#form-update-hostel',
            formGroupSelector: '.form-group',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#hostel-name', 'Vui lòng nhập tên của khu trọ'),
                Validator.isRequired('#hostel-address', 'Vui lòng nhập địa chỉ của khu trọ'),
                Validator.isRequired('#hostel-province', 'Vui lòng chọn tỉnh/thành phố'),
                Validator.isRequired('#hostel-district', 'Vui lòng chọn quận/huyện'),
                Validator.isRequired('#hostel-ward', 'Vui lòng chọn phường/xã')
            ]
        });
    </script>

    <script type="text/javascript">
        // Receive
        receiveWebsocket(alertPushNoti);

        // Close when leave
        window.onbeforeunload = function(){
            receiveWebsocket.disconnectWebSocket();
        };
    </script>

</body>

</html>