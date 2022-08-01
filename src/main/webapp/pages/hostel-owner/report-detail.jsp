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
    <title>Chi tiết phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/report-detail_style/style.css">

    <!-- CSS Push Nnotification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

    <style>
        .ck-editor__editable_inline {
            height: 200px;
        }
    </style>
</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">
<!-- Navbar -->
<%@include file="./components/navbar.jsp"%>

<!-- Loader -->
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
                <c:choose>
                    <c:when test="${requestScope.reportDetail.report.status eq 0}">
                        <a href="report?type=0" class="history-link">Danh sách báo cáo chưa tiếp nhận</a>
                    </c:when>
                    <c:when test="${requestScope.reportDetail.report.status eq 1}">
                        <a href="report?type=1" class="history-link">Danh sách báo cáo đang xử lý</a>
                    </c:when>
                    <c:when test="${requestScope.reportDetail.report.status eq 2}">
                        <a href="report?type=2" class="history-link">Danh sách báo cáo đã hoàn thành</a>
                    </c:when>
                </c:choose>
                <i class="fa-solid fa-chevron-right"></i>
                <div class="current">Báo cáo #RP${requestScope.reportDetail.report.reportID}</div>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9 m-auto">
                <div class="content__body mb-5">
                    <div class="report">
                        <h1 class="report__title">Báo cáo #RP${requestScope.reportDetail.report.reportID}</h1>
                        <div class="report__spacer"></div>
                        <div class="report__info row">
                            <div class="col-6">
                                <div class="report__item">Khu trọ: <span>${requestScope.reportDetail.hostel.hostelName}</span></div>
                                <div class="report__item">Phòng số: <span>${requestScope.reportDetail.room.roomNumber}</span></div>
                                <div class="report__item">Người đại diện: <span>${requestScope.reportDetail.renterInformation.fullname}</span></div>
                                <div class="report__item">Số điện thoại: <span>${requestScope.reportDetail.renterInformation.phone}</span></div>
                            </div>
                            <div class="col-6">
                                <div class="report__item">Loại: <span class="red">${requestScope.reportDetail.category.cateTitle}</span>
                                </div>
                                <div class="report__item">Trạng thái hiện tại:
                                    <c:choose>
                                        <c:when test="${requestScope.reportDetail.report.status eq 0}">
                                            <span class="notyet">Chưa tiếp nhận</span>
                                        </c:when>
                                        <c:when test="${requestScope.reportDetail.report.status eq 1}">
                                            <span class="process">Đang xử lý</span>
                                        </c:when>
                                        <c:when test="${requestScope.reportDetail.report.status eq 2}">
                                            <span class="finished">Hoàn thành</span>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.reportDetail.report.sendDate}" var="sendDate"/>
                                <div class="report__item">Ngày gửi:
                                    <span><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${sendDate}"/></span>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="report__item">Nội dung:
                                    <span>${requestScope.reportDetail.report.content}</span>
                                </div>
                            </div>
                        </div>
                        <div class="report__spacer"></div>
                        <c:choose>
                            <c:when test="${requestScope.reportDetail.report.status eq 0}">
                                <form id="update-report" action="update-report" method="POST">
                                    <input type="hidden" name="reportId" value="${requestScope.reportDetail.report.reportID}" />
                                    <input type="hidden" name="action" value="reply" />
                                    <div class="form-group">
                                        <label for="response-textarea" class="form-label">Phản hồi: <span>*</span></label>
                                        <textarea name="response" id="response-textarea" class="form-control" placeholder="Nhập phản hồi"></textarea>
                                        <span class="form-message mt-4"></span>
                                    </div>
                                    <div class="report__spacer"></div>
                                    <div class="report__action d-flex justify-content-between">
                                        <a href="report?type=0" class="btn btn-outline-dark">Quay lại</a>
                                        <button type="submit" class="btn btn-danger">Xác nhận và xử lý báo cáo</button>
                                    </div>
                                </form>
                            </c:when>
                            <c:when test="${requestScope.reportDetail.report.status eq 1}">
                                <div class="report__reply">
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.reportDetail.report.replyDate}" var="replyDate"/>
                                    <div class="report__item">Ngày tiếp nhận:
                                        <span><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${replyDate}"/></span>
                                    </div>
                                    <div class="report__item">Phản hồi: <span>${requestScope.reportDetail.report.reply}</span></div>
                                </div>
                                <form action="update-report" method="POST">
                                    <input type="hidden" name="reportId" value="${requestScope.reportDetail.report.reportID}" />
                                    <input type="hidden" name="action" value="finished" />
                                    <div class="report__spacer"></div>
                                    <div class="report__action d-flex justify-content-between">
                                        <a href="report?type=1" class="btn btn-outline-dark">Quay lại</a>
                                        <button type="submit" class="btn btn-danger">Hoàn thành báo cáo</button>
                                    </div>
                                </form>
                            </c:when>
                            <c:when test="${requestScope.reportDetail.report.status eq 2}">
                                <div class="report__reply">
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.reportDetail.report.replyDate}" var="replyDate"/>
                                    <div class="report__item">Ngày tiếp nhận:
                                        <span><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${replyDate}"/></span>
                                    </div>
                                    <div class="report__item">Phản hồi: <span>${requestScope.reportDetail.report.reply}</span></div>
                                </div>
                                <div class="report__spacer"></div>
                                <div class="report__finish">
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${requestScope.reportDetail.report.completeDate}" var="completeDate"/>
                                    <div class="report__item">Ngày hoàn thành:
                                        <span><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${completeDate}"/></span>
                                    </div>
                                </div>
                                <div class="report__spacer"></div>
                                <div class="report__action d-flex justify-content-between">
                                    <a href="report?type=2" class="btn btn-outline-dark">Quay lại</a>
                                </div>
                            </c:when>
                        </c:choose>
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

<!-- Toast element -->
<div id="toast">&nbsp;</div>
<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/sendWebsocket.js"></script>
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- CKEditor -->
<script src="./assets/js/ckeditor.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>

<script>
    <c:if test="${requestScope.reportDetail.report.status eq 0}">
    // Initial CKEditor
    ClassicEditor.create(document.querySelector('#response-textarea'), {
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

    // Validator form
    Validator({
        form: "#update-report",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#response-textarea", "Vui lòng nhập phản hồi"),
        ],
    });
    </c:if>

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
</script>

<script type="text/javascript">
    // Send
    <c:if test="${requestScope.RESPONSE_MSG.status == true}">
    const params = new Object();
    params.sender = "hostel_owner";
    params.receiver = "hostel_renter";
    params.hostel_receiver_id =  null;
    params.account_receiver_id = "${requestScope.reportDetail.report.sendAccountID}";
    params.messages = "${requestScope.SOCKET_MSG}";
    sendToWebSocket(params);
    </c:if>

    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function(){
        receiveWebsocket.disconnectWebSocket();
    };
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>
