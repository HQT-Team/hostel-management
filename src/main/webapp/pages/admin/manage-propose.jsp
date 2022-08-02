<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Quản lý ý kiến/đề xuất</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/manage-proposes/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

    <style>
        .ck-editor__editable_inline {
            height: 200px;
        }
    </style>

</head>

<body class="${ requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <div id="preloader">
        <div class="dots">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</c:if>

<div class="app">

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
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- List proposes -->
                <div class="row mt-5">
                    <div class="propose col-12">
                        <div class="propose__title">
                            Danh sách ý kiến/đề xuất từ người dùng
                        </div>
                        <table id="propose__table"
                               class="propose__table mt-4 mb-4 table table-hover table-bordered table-striped">
                            <thead class="table-dark propose__table-head">
                            <tr>
                                <th>Người gửi</th>
                                <th>Nội dung</th>
                                <th>Ngày gửi</th>
                                <th>Trạng thái</th>
                                <th>Phản hồi</th>
                                <th>Ngày phản hồi</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody class="table-light propose__table-body">
                            <c:forEach var="propose" items="${requestScope.proposeList}">
                                <tr>
                                    <td class="text-center">${propose.sendAccount eq null ? "N/a" : propose.sendAccount.username}</td>
                                    <td>${propose.content}</td>
                                    <td class="text-center">
                                        <fmt:parseDate var="ParseSendDate" value="${propose.sendDate}" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate pattern = "dd/MM/yyyy" value="${ParseSendDate}" />
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
                                    <td>${propose.reply eq null ? "N/a" : propose.reply}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${propose.replyDate eq null}">
                                                <span>N/a</span>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:parseDate var="ParseReplyDate" value="${propose.replyDate}" pattern="yyyy-MM-dd" />
                                                <fmt:formatDate pattern = "dd/MM/yyyy" value="${ParseReplyDate}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${propose.status eq 0}">
                                            <!-- Approve Section -->
                                            <button class="btn btn-success fs-4 w-100" data-bs-toggle="modal"
                                                    data-bs-target="#propose-approve__modal-${propose.id}">Phê duyệt</button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="propose-approve__modal-${propose.id}" tabindex="-1"
                                             aria-labelledby="propose-approve__modal-label-${propose.id}" aria-hidden="true">
                                            <div class="modal-dialog modal-lg modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="propose-approve__modal-label-${propose.id}">
                                                            Phê duyệt đề xuất/ý kiến
                                                        </h5>
                                                        <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <form id="propose-approve-form-${propose.id}" action="handle-propose" method="POST">
                                                        <input type="hidden" name="proposeId" value="${propose.id}" />
                                                        <input type="hidden" name="changeStatus" value="1" />
                                                        <div class="modal-body mt-5 mb-5">
                                                            <div class="form-group">
                                                                <label for="propose-approve-textarea-${propose.id}" class="form-label">Lý do: <span>*</span></label>
                                                                <textarea id="propose-approve-textarea-${propose.id}" name="proposeReply" class="form-control" placeholder="Nhập lý do"></textarea>
                                                                <div class="form-message mb-0 mt-4"></div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer justify-content-between">
                                                            <button type="button" class="btn btn-secondary fs-4"
                                                                    data-bs-dismiss="modal">
                                                                Đóng
                                                            </button>
                                                            <button type="submit" class="btn btn-success fs-4">
                                                                Phê duyệt
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                            <!-- Reject Section -->
                                            <button class="btn btn-danger fs-4 w-100 mt-3" data-bs-toggle="modal"
                                                    data-bs-target="#propose-reject__modal-${propose.id}">Từ chối</button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="propose-reject__modal-${propose.id}" tabindex="-1"
                                                 aria-labelledby="propose-reject__modal-label-${propose.id}" aria-hidden="true">
                                                <div class="modal-dialog modal-lg modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="propose-reject__modal-label-${propose.id}">
                                                                Từ chối đề xuất/ý kiến
                                                            </h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form id="propose-reject-form-${propose.id}" action="handle-propose" method="POST">
                                                            <input type="hidden" name="proposeId" value="${propose.id}" />
                                                            <input type="hidden" name="changeStatus" value="-1" />
                                                            <div class="modal-body mt-5 mb-5">
                                                                <div class="form-group">
                                                                    <label for="propose-reject-textarea-${propose.id}" class="form-label">Lý do: <span>*</span></label>
                                                                    <textarea id="propose-reject-textarea-${propose.id}" name="proposeReply" class="form-control" placeholder="Nhập lý do từ chối"></textarea>
                                                                    <div class="form-message mb-0 mt-4"></div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer justify-content-between">
                                                                <button type="button" class="btn btn-secondary fs-4"
                                                                        data-bs-dismiss="modal">
                                                                    Đóng
                                                                </button>
                                                                <button type="submit" class="btn btn-danger fs-4">
                                                                    Từ chối
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./components/footer.jsp"%>

    <!-- Toast element -->
    <div id="toast">&nbsp;</div>

</div>

<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Simple Datatable JS -->
<script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
<!-- Toast -->
<script src="./assets/js/toast-alert.js"></script>
<!-- Valid form -->
<script src="./assets/js/valid-form.js"></script>
<!-- CKEditor -->
<script src="./assets/js/ckeditor.js"></script>

<script>
    // Initial CKEditor
    <c:forEach var="propose" items="${requestScope.proposeList}">
        <c:if test="${propose.status eq 0}">
        // Initial CKEditor
        ClassicEditor.create(document.querySelector('#propose-approve-textarea-${propose.id}'), {
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
            form: "#propose-approve-form-${propose.id}",
            formGroupSelector: ".form-group",
            errorSelector: ".form-message",
            rules: [
                Validator.isRequired("#propose-approve-textarea-${propose.id}", "Vui lòng nhập lý do phê duyệt!")
            ],
        });

        // Initial CKEditor
        ClassicEditor.create(document.querySelector('#propose-reject-textarea-${propose.id}'), {
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
        form: "#propose-reject-form-${propose.id}",
        formGroupSelector: ".form-group",
        errorSelector: ".form-message",
        rules: [
            Validator.isRequired("#propose-reject-textarea-${propose.id}", "Vui lòng nhập lý do phê duyệt!")
        ],
    });
        </c:if>
    </c:forEach>

    <c:choose>
    <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq true}">
    toast({
        title: 'Thành công',
        message: '${requestScope.RESPONSE_MSG.content}',
        type: 'success',
        duration: 5000
    });
    </c:when>
    <c:when test="${requestScope.RESPONSE_MSG ne null && requestScope.RESPONSE_MSG.status eq false}">
    toast({
        title: 'Lỗi',
        message: '${requestScope.RESPONSE_MSG.content}',
        type: 'error',
        duration: 5000
    });
    </c:when>
    </c:choose>

    $('#propose__table').DataTable();
</script>

<c:if test="${requestScope.RESPONSE_MSG eq null}">
    <!-- Loader -->
    <script src="./assets/js/loading-handler.js"></script>
</c:if>
</body>

</html>

