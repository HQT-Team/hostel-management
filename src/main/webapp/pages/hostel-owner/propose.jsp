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
    <title>Góp ý</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/notifications_style/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

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
            <div class="col-xxl-10 m-auto">
                <div class="col-12 col-md-8 col-lg-8 col-xl-7 col-xxl-8">
                    <!-- Tab menu -->
                    <div class="tabs">
                        <div class="tabs-item active">
                            <i class="tabs-icon fa-solid fa-envelope-circle-check"></i> ý kiến đã gửi
                        </div>
                        <div class="tabs-item">
                            <i class="tabs-icon fa-solid fa-paper-plane"></i> Gửi ý kiến
                        </div>
                        <div class="line"></div>
                    </div>
                </div>

                <!-- Content item - List proposes sent -->
                <div class="content__item active">
                    <!-- Proposes list container -->
                    <div id="list-notifications-container" class="content__body mb-5">
                        <table id="notification-table" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                                <th class="text-center">Nội dung</th>
                                <th class="text-center">Ngày gửi</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Phản hồi</th>
                                <th class="text-center">Người duyệt</th>
                            </thead>
                            <tbody class="content__tbody">
                            <c:forEach var="propose" items="${requestScope.proposeList}">
                                <tr>
                                    <td>
                                        ${propose.content}
                                    </td>
                                    <td class="text-center">
                                        <fmt:parseDate var="ParseDate" value="${propose.sendDate}" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate pattern = "dd/MM/yyyy" value="${ParseDate}" />
                                    </td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${propose.status eq 0}">
                                                <span class="text-primary">Đang chờ phản hồi</span>
                                            </c:when>
                                            <c:when test="${propose.status eq 1}">
                                                <span class="text-success">Chấp thuận</span>
                                            </c:when>
                                            <c:when test="${propose.status eq -1}">
                                                <span class="text-danger">Bị từ chối</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        ${propose.reply eq null ? "N/a" : propose.reply}
                                    </td>
                                    <td>
                                        ${propose.replyAccount eq null ? "N/a" : propose.replyAccount.accountInfo.information.fullname}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Content item - Send notification -->
                <div class="content__item">
                    <div class="col-12 col-xl-9 m-auto mb-5 content__body">
                        <form id="add-notification-form" action="send-propose" method="post" class="custom-form">
                            <div class="form-header">
                                <h1 class="form-title">Gửi đề xuất/ý kiến</h1>
                            </div>
                            <div class="spacer"></div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="noti-content" class="form-label">Nội dung:
                                        <span>*</span></label>
                                    <textarea name="propose-content" id="noti-content" class="form-control textarea"></textarea>
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

<!-- Toast element -->
<div id="toast">&nbsp;</div>

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
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- CKEditor -->
<script src="./assets/js/ckeditor.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>
<!-- Toast Alert -->
<script src="./assets/js/toast-alert.js"></script>

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
        .catch( error => {
            console.error(error);
        });

        // Valid form
        Validator({
            form: "#add-notification-form",
            formGroupSelector: ".form-group",
            errorSelector: ".form-message",
            rules: [
                Validator.isRequired("#noti-content", "Vui lòng nhập nội dung ý kiến, đề xuất"),
            ],
        });


        // Initial datatable
        $(`#notification-table`).DataTable({
            "order": [],
        });

        const tabs = document.querySelectorAll(".tabs-item");
        const contents = document.querySelectorAll(".content__item");

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

<!-- Loader -->
<script>
    const loader = document.getElementById('preloader');

    window.addEventListener('load', () => {
        loader.setAttribute('closing', '');
        loader.addEventListener('animationend', () => {
            document.body.classList.remove('over-flow-hidden');
            loader.style.display = 'none';
        }, { once: true });

        <c:choose>
        <c:when test="${requestScope.RESPONSE_MSG.status eq true}">
        toast({
            title: 'Thành công',
            message: '${requestScope.RESPONSE_MSG.content}',
            type: 'success',
            duration: 5000
        });
        </c:when>
        <c:when test="${requestScope.RESPONSE_MSG.status eq false}">
        toast({
            title: 'Lỗi',
            message: '${requestScope.RESPONSE_MSG.content}',
            type: 'error',
            duration: 5000
        });
        </c:when>
        </c:choose>

    });

</script>
</body>

</html>

