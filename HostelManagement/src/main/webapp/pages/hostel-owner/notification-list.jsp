<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Thông báo</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/notifications_style/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

    <!-- Select2 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

    <style>
        .ck-editor__editable_inline {
            height: 200px;
        }
    </style>

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

            <div class="col-xxl-9 m-auto">
                <div class="col-12 col-md-8 col-lg-8 col-xl-7 col-xxl-8">
                    <!-- Tab menu -->
                    <div class="tabs">
                        <div class="tabs-item active">
                            <i class="tabs-icon fa-solid fa-check-to-slot"></i> Thông báo đã gửi
                        </div>
                        <div class="tabs-item active">
                            <i class="tabs-icon fa-solid fa-envelope-open-text"></i> Gửi thông báo
                        </div>
                        <div class="line"></div>
                    </div>
                </div>

                <!-- Content item - List notifications sent -->
                <div class="content__item active">
                    <!-- filter bar -->
                    <div class="filter__wrapper">
                        <table>
                            <tr>
                                <td></td>
                                <td>Khu trọ</td>
                            </tr>
                            <tr>
                                <td><i class="fa-solid fa-sliders"></i> Lọc</td>
                                <form id="filter-form">
                                    <td>
                                        <select name="hostelId" id="filter__hostel-select">
                                            <option value="">Tất cả</option>
                                            <c:forEach var="hostel" items="${sessionScope.HOSTEL_LIST}">
                                                <option value="${hostel.hostelID}">${hostel.hostelName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </form>
                            </tr>
                        </table>
                    </div>
                    <!-- Notification list container -->
                    <div id="list-notifications-container" class="content__body mb-5">
                        <table id="notification-table" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                            <th class="text-center">Mã</th>
                            <th class="text-center">Tiêu đề</th>
                            <th class="text-center">Ngày gửi</th>
                            <th class="text-center">Khu trọ</th>
                            </thead>
                            <tbody class="content__tbody">
                            <c:forEach var="notification" items="${requestScope.NOTIFICATION_LIST}">
                                <tr>
                                    <td class="text-center">
                                        <a href="owner-review-notification?action=view&notification_id=${notification.notification_id}">#NF${notification.notification_id}</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="owner-review-notification?action=view&notification_id=${notification.notification_id}">${notification.title}</a>
                                    </td>
                                    <td class="text-center">
                                        <fmt:parseDate var="ParseDate" value="${notification.createDate}" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate pattern = "dd/MM/yyyy" value="${ParseDate}" />
                                    </td>
                                    <td class="text-center">
                                        <c:forEach var="hostel" items="${sessionScope.HOSTEL_LIST}">
                                            <c:if test="${hostel.hostelID eq notification.hostel_id}">
                                                ${hostel.hostelName}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Content item - Send notification -->
                <div class="content__item active">
                    <div class="col-12 col-xl-9 m-auto mb-5 content__body">
                        <form id="add-notification-form" action="add-notification" method="post" class="custom-form">
                            <div class="form-header">
                                <h1 class="form-title">Gửi thông báo mới</h1>
                            </div>
                            <div class="spacer"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="noti-title" class="form-label">Tiêu đề: <span>*</span></label>
                                        <input type="text" id="noti-title" name="noti-title" placeholder="Nhập tiêu đề"
                                               class="form-control">
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="noti-hostel-id" class="form-label">Khu trọ:
                                            <span>*</span></label>
                                        <select name="noti-hostel-id" id="noti-hostel-id" class="form-control">
                                            <option value="">Chọn khu trọ nhận thông báo</option>
                                            <c:forEach var="hostel" items="${sessionScope.HOSTEL_LIST}">
                                                <option value="${hostel.hostelID}">${hostel.hostelName}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="noti-content" class="form-label">Nội dung:
                                        <span>*</span></label>
                                    <textarea name="noti-content" id="noti-content" class="form-control textarea"></textarea>
                                    <span class="form-message mt-4 mb-0"></span>
                                </div>
                            </div>
                            <div class="spacer"></div>
                            <div class="form-action d-flex justify-content-end">
                                <button class="form-submit">Gửi</button>
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

<!-- Push notification element -->
<div id="push-noti"></div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Simple Datatable JS -->
<script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
<!-- Select2 JS -->
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Load data async -->
<script src="./assets/js/load-notification-async.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- CKEditor -->
<script src="./assets/js/ckeditor.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>

<script>
    $(document).ready(function () {
        // Initial CKEditor
        ClassicEditor.create( document.querySelector('#noti-content'), {
            toolbar: {
                items: [
                    'heading', '|',
                    'fontfamily', 'fontsize', '|',
                    'alignment', '|',
                    'fontColor', 'fontBackgroundColor', '|',
                    'bold', 'italic', 'strikethrough', 'underline', 'subscript', 'superscript', '|',
                    'link', '|',
                    'bulletedList', 'numberedList', 'todoList', '|',
                    'code', 'codeBlock', 'blockQuote', '|',
                    'undo', 'redo'
                ],
                shouldNotGroupWhenFull: true
            }
        })
            .then(editor => {
                console.log(editor);
            })
            .catch( error => {
                console.error(error);
            });

        // Valid form
        Validator({
            form: "#add-notification-form",
            formGroupSelector: ".form-group",
            errorSelector: ".form-message",
            rules: [
                Validator.isRequired("#noti-title", "Vui lòng nhập tiêu đề của thông báo"),
                Validator.isRequired("#noti-hostel-id", "Vui lòng chọn khu trọ nhận thông báo"),
                Validator.isRequired("#noti-content", "Vui lòng nhập nội dung thông báo"),
            ],
        });

        // Select 2
        $(`#filter__hostel-select`).select2();
        $('#noti-hostel-id').select2();

        // Initial datatable
        $(`#notification-table`).DataTable({
            "order": [],
        });

        const tabs = document.querySelectorAll(".tabs-item");
        const contents = document.querySelectorAll(".content__item");

        for (let i = 0; i < 2; i++) {
            contents[i].classList.remove("active");
            tabs[i].classList.remove("active");
        }

        ((index = 0) => {
            tabs[index].classList.add("active");
            contents[index].classList.add("active");
        })(0);

        const tabActive = document.querySelector(".tabs-item.active");
        const line = document.querySelector(".tabs .line");

        let i = 0;

        line.style.left = tabActive.offsetLeft + "px";
        line.style.width = tabActive.offsetWidth + "px";

        tabs.forEach((tab, index) => {
            const content = contents[index];

            tab.onclick = function () {
                i = index;

                document.querySelector(".tabs-item.active").classList.remove("active");
                document.querySelector(".content__item.active").classList.remove("active");

                line.style.left = this.offsetLeft + "px";
                line.style.width = this.offsetWidth + "px";

                this.classList.add("active");
                content.classList.add("active");
            };
        });

        // Filter
        $('#filter__hostel-select').on('change', () => {
            $('#filter-form').submit();
        })

        $('#filter-form').submit(function(e) {
            e.preventDefault();

            axios.interceptors.request.use(function (config) {
                $('#list-notifications-container').html("Loading...");
                return config;
            });

            axios({
                method: 'post',
                url: 'http://localhost:8080/HappyHostel/owner-get-notification-list',
                params: {
                    'hostelID': $('#filter__hostel-select').find(':selected').val(),
                }
            })
            .then(function (response) {
                console.log(response);
                loadNotificationAsync(response.data[0], response.data[1]);
            })
            .catch(function (error) {
                console.log(error);
            });
        });
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

<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
