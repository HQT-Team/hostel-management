<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>Tổng quan</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"
            integrity="sha512-sW/w8s4RWTdFFSduOTGtk4isV1+190E/GghVffMA9XczdJ2MDzSzLEubKAs5h0wzgSJOQTRYyaz73L3d6RtJSg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    ></script>
    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css"/>

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/statistic/style.css"/>

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

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

<input type="hidden" name="render-number" value="" id="render-number"/>
<div class="app">

    <!-- Navbar -->
    <%@include file="./components/navbar.jsp" %>

    <!-- Body -->
    <div class="container">
        <div class="row position-relative">

            <!-- Side bar -->
            <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
                <%@include file="components/sidebar.jsp" %>
            </div>

            <!-- Content -->
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- Your content here -->
                <c:if test="${requestScope.error == null}">
                    <div class="content mt-5">
                        <div class="statistic-content">
                            <form action="statistic" method="POST" id="form-search">
                                <div class="search">
                                    <div class="search-hostel">
                                        <select name="select-hostel" id="select-hostel">
                                            <c:forEach items="${requestScope.listHostel}" var="hostel">
                                                <option value="${hostel.getHostelName()}"
                                                        id="${hostel.getHostelName()}">${hostel.getHostelName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="search-year">
                                        <select name="select-year" id="select-year">
                                            <c:forEach items="${requestScope.listYear}" var="year">
                                                <option value="${year}" id="${year}">${year}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="search-quater">
                                        <input type="hidden" value=""/>
                                        <select name="select-quater" id="quater">
                                            <option id="quater_1" value="quater_1">Quý 1</option>
                                            <option id="quater_2" value="quater_2">Quý 2</option>
                                            <option id="quater_3" value="quater_3">Quý 3</option>
                                            <option id="quater_4" value="quater_4">Quý 4</option>
                                            <option id="all-quater" value="all-quater">Tất cả</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <div class="money">
                                <div class="income">
                                    <div>
                                        <h3>${requestScope.totalMoney == null ? 0 : requestScope.totalMoney} VND</h3>
                                        <p>Thu nhập</p>
                                    </div>
                                </div>
                                <div class="expense">
                                    <div>
                                        <h3>${requestScope.expenseMoney == null ? 0 : requestScope.expenseMoney} VND</h3>
                                        <p>Chi phí</p>
                                    </div>
                                </div>
                                <div class="revenue">
                                    <div>
                                        <h3>${requestScope.revenueMoney == null ? 0 : requestScope.revenueMoney} VND</h3>
                                        <p>Doanh thu</p>
                                    </div>
                                </div>
                            </div>
                            <div class="chart" id="chart">
                                <div class="chartwithquater" id="chartwithquater">
                                    <div>
                                        <canvas id="myBarChart"></canvas>
                                    </div>
                                    <div>
                                        <canvas id="myLineChart"></canvas>
                                    </div>
                                </div>
                                <div class="chartwithyear" id="chartwithyear">
                                    <div>
                                        <canvas id="myBarChartWithYear"></canvas>
                                    </div>
                                    <div>
                                        <canvas id="myLineChartWithYear"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="summary">
                                <div class="top-revenue">
                                    <h3>Báo Cáo</h3>
                                    <table class="content-table">
                                        <thead>
                                        <tr>
                                            <th>Số lượng</th>
                                            <th>Tỷ lệ phản hồi</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <th>${requestScope.listReport.size() == null ? 0 :  requestScope.listReport.size()}</th>
                                            <th>${requestScope.rate == null ? 0 : requestScope.rate}%</th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="room-infor">
                                    <h3>Phòng Trọ</h3>
                                    <p>Đang cho thuê: ${requestScope.numberEmpty == null ? 0 : requestScope.numberEmpty}</p>
                                    <p>Phòng Trống: ${requestScope.numberRenting == null ? 0 : requestScope.numberRenting}</p>
                                    <p>Đang làm hợp đồng: ${requestScope.numberContract == null ? 0 : requestScope.numberContract}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope.error != null}">
                    <!-- Warning -->
                    <div class="content-warning mt-5">
                        <div class="blink"></div>
                        <span>Hệ thống nhận thấy bạn chưa có khu trọ nào cả! <a href="add-hostel">
                                Click vào đây để tạo khu trọ mới nhen!</a>
                        </span>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./components/footer.jsp" %>

    <!-- Push notification element -->
    <div id="push-noti"></div>
</div>

<!-- Script Bootstrap !important -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
<!-- JQuery -->
<script
        src="./assets/js/jquery-3.5.1.min.js"
        type="text/javascript"
></script>
<!-- Navbar -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Chart JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="./assets/js/owner/statistic/statistic.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<script>
    if (${requestScope.hostelName eq null ? false : true}) {
        document.getElementById("${requestScope.hostelName}").setAttribute("selected", "selected");
    }
    if (${requestScope.year eq null ? false : true}) {
        document.getElementById("select-year").disabled = false;
        document.getElementById("quater").disabled = false;
        document.getElementById("${requestScope.year}").setAttribute("selected", "selected");
    }
    if (parseInt(${requestScope.sizeListHostel}) == 0) {
        document.getElementById("select-year").disabled = true;
        document.getElementById("quater").disabled = true;
    }
    if (${requestScope.listRoomToCheck.size() == 0}) {
        document.getElementById("select-year").disabled = true;
        document.getElementById("quater").disabled = true;
    }
    if (${requestScope.listRoomToCheck.size() > 0}) {
        document.getElementById("select-year").disabled = false;
        document.getElementById("quater").disabled = false;
    }
    if (${requestScope.listRoom.size() == 0}) {
        document.getElementById("select-year").disabled = true;
        document.getElementById("quater").disabled = true;
    }
    if (${requestScope.listRoom.size() > 0}) {
        document.getElementById("select-year").disabled = false;
        document.getElementById("quater").disabled = false;
    }

    if (${requestScope.quater eq null ? false : true}) {
        document.getElementById("${requestScope.quater}").setAttribute("selected", "selected");
    }
    var listCancel = [${requestScope.contractCancelRate1}, ${requestScope.contractCancelRate2}, ${requestScope.contractCancelRate3}];
    var listCreate = [${requestScope.contractCreateRate1}, ${requestScope.contractCreateRate2}, ${requestScope.contractCreateRate3}];
    var list = [${requestScope.totalMoneyMonth1}, ${requestScope.totalMoneyMonth2}, ${requestScope.totalMoneyMonth3}];
    let listMonthtmp = [1, 2, 3];
    if (${requestScope.quater eq "quater_1"})
        listMonthtmp = [1, 2, 3];
    if (${requestScope.quater eq "quater_2"})
        listMonthtmp = [4, 5, 6];
    if (${requestScope.quater eq "quater_3"})
        listMonthtmp = [7, 8, 9];
    if (${requestScope.quater eq "quater_4"})
        listMonthtmp = [10, 11, 12];
    newBarChartWithQuater(list, listMonthtmp);
    newLineChartWithQuater(listCreate, listCancel, listMonthtmp);
    if (${requestScope.quater eq "all-quater"}) {
        var list = [
            ${requestScope.month_1},
            ${requestScope.month_2},
            ${requestScope.month_3},
            ${requestScope.month_4},
            ${requestScope.month_5},
            ${requestScope.month_6},
            ${requestScope.month_7},
            ${requestScope.month_8},
            ${requestScope.month_9},
            ${requestScope.month_10},
            ${requestScope.month_11},
            ${requestScope.month_12}
        ];
        var listCreate = [
            ${requestScope.createMonth_1},
            ${requestScope.createMonth_2},
            ${requestScope.createMonth_3},
            ${requestScope.createMonth_4},
            ${requestScope.createMonth_5},
            ${requestScope.createMonth_6},
            ${requestScope.createMonth_7},
            ${requestScope.createMonth_8},
            ${requestScope.createMonth_9},
            ${requestScope.createMonth_10},
            ${requestScope.createMonth_11},
            ${requestScope.createMonth_12}
        ];
        var listCancel = [
            ${requestScope.cancelMonth_1},
            ${requestScope.cancelMonth_2},
            ${requestScope.cancelMonth_3},
            ${requestScope.cancelMonth_4},
            ${requestScope.cancelMonth_5},
            ${requestScope.cancelMonth_6},
            ${requestScope.cancelMonth_7},
            ${requestScope.cancelMonth_8},
            ${requestScope.cancelMonth_9},
            ${requestScope.cancelMonth_10},
            ${requestScope.cancelMonth_11},
            ${requestScope.cancelMonth_12}
        ];
        chartwithquater.style.display = "none";
        chartwithyear.style.removeProperty("display");
        newBarChartWithYear(list);
        newLineChartWithYear(listCreate, listCancel);
    }
</script>

<script type="text/javascript">
    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function () {
        receiveWebsocket.disconnectWebSocket();
    };
</script>

<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>
</html>