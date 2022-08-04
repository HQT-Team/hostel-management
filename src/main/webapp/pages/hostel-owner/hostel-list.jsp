<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/hostel_list/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
</head>

<body class="${requestScope.RESPONSE_MSG eq null ? "over-flow-hidden" : ""}">
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
                    <div class="content-bar">
                        <div class="content-history">
                            <div class="current">
                                Danh sách khu trọ</div>
                        </div>
                        <div class="content-actions">
                            <a href="add-hostel" class="add-hostel-btn">
                                Thêm khu trọ mới
                            </a>
                        </div>
                    </div>
                    <div class="content-body mb-5">
                        <div class="hostel-list">
                            <div class="hostel-list__header">
                                Các khu trọ hiện tại
                            </div>
                            <div class="hostel-list__items mt-4">
                                <table id="hostel-table"
                                    class="mt-4 table table-hover table-bordered table-striped hostel-table">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên</th>
                                            <th>Địa chỉ</th>
                                            <th>Tổng số phòng</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-light hostel-table__body">
                                    <c:set var="count" value="0" scope="page"/>
                                    <c:forEach var="hostels" items="${requestScope.LIST_HOSTEL}">
                                        <c:set var="count" value="${count + 1}" scope="page"/>

                                        <tr>
                                            <td>${count}</td>
                                            <td>${hostels.hostelName}</td>
                                            <td>${hostels.address}, ${hostels.ward.split('-')[1]},
                                                    ${hostels.district.split('-')[1]}, ${hostels.city.split('-')[1]}
                                            </td>
                                            <td>
                                                ${requestScope.LIST_TOTAL_ROOM.get(hostels.hostelID)}
                                            </td>
                                            <td class="hostel-table__body-link">
                                                <div class="hostel-table__body-wrapper">
                                                    <a href="detailHostel?hostelID=${hostels.hostelID}"
                                                       class="hostel-table__body-link-detail">
                                                        <i class="fa-solid fa-building-circle-arrow-right"></i>
                                                    </a>
                                                    <a href="update-hostel?hostelID=${hostels.hostelID}"
                                                       class="hostel-table__body-btn-edit"><i
                                                            class="fa-solid fa-pen-to-square"></i></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-5">
                        <div class="upload-file-section col-12 col-md-8 col-lg-7 col-xl-6">
                            <div class="upload-wrapper">
                                <h1>Thêm khu trọ bằng file Excel</h1>
                                <c:choose>
                                    <c:when test="${requestScope.SUCCESS ne null}">
                                        <h3 class="success mb-4">${requestScope.SUCCESS}</h3>
                                    </c:when>
                                    <c:when test="${requestScope.ERROR ne null}">
                                        <h3 class="failed mb-4">${requestScope.ERROR}</h3>
                                    </c:when>
                                </c:choose>
                                <c:if test="${requestScope.SUCCESS eq null}">
                                    <form action="HostelFileUpLoadServlet" method="POST" enctype="multipart/form-data">
                                        <input class="form-control" type="file" name="file"
                                               accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                        <button class="btn btn-primary fs-3" type="submit">Tải file lên hệ thống</button>
                                    </form>
                                </c:if>
                                <c:if test="${requestScope.SUCCESS ne null}">
                                    <form action="import-hostel" method="GET">
                                        <input type="hidden" value="${requestScope.file}" name="fileName">
                                        <button class="btn btn-primary fs-3" type="submit">
                                            Thêm khu trọ từ tệp excel đã tải lên
                                        </button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${requestScope.SUCCESS_IMPORT ne null || requestScope.ERROR_IMPORT ne null}">
                            <div class="upload-file-section col-12 col-md-4 col-lg-5 col-xl-6">
                                <div class="upload-wrapper">
                                    <h1>Kết quả thêm khu trọ</h1>
                                    <c:forEach var="e" items="${requestScope.SUCCESS_IMPORT}">
                                        <h3 class="success mb-4 fw-normal">${e.content}</h3>
                                    </c:forEach>
                                    <c:forEach var="e" items="${requestScope.ERROR_IMPORT}">
                                        <h3 class="failed mb-4 fw-normal">${e.content}</h3>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="components/footer.jsp"%>

        <!-- Toast element -->
        <div id="toast">&nbsp;</div>

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
    <!-- Simple Datatable JS -->
    <script src="./assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <!-- Toast Alert -->
    <script src="./assets/js/toast-alert.js"></script>
    <!-- Push notification -->
    <script src="./assets/js/push-notification-alert.js"></script>
    <!-- Web socket -->
    <script src="./assets/js/receiveWebsocket.js"></script>
    <script>
        $(document).ready(function () {
            // Initial datatable
            $('#hostel-table').DataTable({
                columnDefs: [
                    { orderable: false, targets: 0 },
                    { orderable: false, targets: 3 },
                    { orderable: false, targets: 4 }
                ],
                "order": [],
            });

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

    <script type="text/javascript">
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