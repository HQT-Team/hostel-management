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
                        <td>
                            <select name="" id="">
                                <option value="0">Tất cả</option>
                                <c:forEach var="hostelName" items="${sessionScope.HOSTEL_DROP_DOWN_NAME}">
                                    <option value="${hostelName}">${hostelName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <!-- Infor box -->
            <div class="col-xxl-9">
                <div class="content__body">
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
                                <td><a href="detailHostel?hostelID=${roomList.hostelId}"
                                       class="content__tbody-hostel-link">${hosteListName.get(loop.index)}</a></td>
                                <td><a href="roomDetail?roomID=${roomList.roomId}&hostelID=${roomList.hostelId}" class="content__tbody-room-link">${roomList.roomNumber}</a>
                                </td>
                                <c:if test="${roomList.roomStatus eq 1}">
                                    <td class="content__tbody-status yes">Sẵn sàng cho thuê</td>
                                </c:if>
                                <c:if test="${roomList.roomStatus eq 0}">
                                    <td class="content__tbody-status no">Đã cho thuê</td>
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
<script>
    $(document).ready(function () {
        // Initial datatable
        $('#room-table').DataTable();
    });
</script>
<!-- Link your script here -->


<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
