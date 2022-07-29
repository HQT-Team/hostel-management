<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>Danh sách phòng</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/room-list_style/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

    <!-- Select2 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
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
<%@include file="./components/navbar.jsp" %>

<!-- Body -->
<div class="container">
    <div class="row position-relative">
        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
            <%@include file="./components/sidebar.jsp" %>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
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
                                    <c:forEach var="hostel" items="${requestScope.HOSTEL_LIST}">
                                        <option value="${hostel.hostelID}">${hostel.hostelName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </form>
                    </tr>
                </table>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9">
                <div id="list-rooms-container" class="content__body">
                    <table id="room-table" class="content__table table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th class="text-center">Khu trọ</th>
                                <th class="text-center">Phòng số</th>
                                <th class="text-center">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="hosteListName" value="${sessionScope.HOSTEL_LIST_NAME}"/>
                        <c:forEach var="roomList" items="${sessionScope.ROOM_LIST}" varStatus="loop">
                            <tr>
                                <td>
                                    <a href="detailHostel?hostelID=${roomList.hostelId}"
                                       class="content__tbody-hostel-link">${hosteListName.get(loop.index)}</a>
                                </td>
                                <td>
                                    <a href="roomDetail?roomID=${roomList.roomId}&hostelID=${roomList.hostelId}"
                                       class="content__tbody-room-link">${roomList.roomNumber}</a>
                                </td>
                                <c:if test="${roomList.roomStatus eq 1}">
                                    <td class="content__tbody-status yes">Sẵn sàng cho thuê</td>
                                </c:if>
                                <c:if test="${roomList.roomStatus eq 0}">
                                    <td class="content__tbody-status no">Đã cho thuê</td>
                                </c:if>
                                <c:if test="${roomList.roomStatus eq -1}">
                                    <td class="content__tbody-status wait">Đang tiến hành làm hợp đồng</td>
                                </c:if>
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
<%@include file="./components/footer.jsp" %>

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
<script src="./assets/js/load-room-async.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<script>
    $(document).ready(function () {
        // Select 2
        $(`#filter__hostel-select`).select2();

        // Initial datatable
        $('#room-table').DataTable();

        // Filter
        $('#filter__hostel-select').on('change', () => {
            $('#filter-form').submit();
        })

        $('#filter-form').submit(function(e) {
            e.preventDefault();

            axios.interceptors.request.use(function (config) {
                $('#list-rooms-container').html("Loading...");
                return config;
            });

            axios({
                method: 'post',
                url: 'http://localhost:8080/HappyHostel/getRoomList',
                params: {
                    'hostelId': $('#filter__hostel-select').find(':selected').val(),
                }
            })
            .then(function (response) {
                console.log(response);
                loadRoomAsync(response.data[0], response.data[1]);
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
