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
    <title>Tổng quan</title>

    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/dashboard/style.css">

    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

</head>

<body class="over-flow-hidden">

<div id="preloader">
    <div class="dots">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

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
            <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 pb-5 content-group">
                <!-- Welcome -->
                <div class="content-welcome mt-5">
                    <img src="./assets/images/avatars/user-avatar.jpg" alt="User avatar" class="welcome-image">
                    <div class="welcome-content">
                        <div class="welcome-title">Chào mừng, <span>${sessionScope.USER.accountInfo.information.fullname}</span></div>
                        <c:choose>
                            <c:when test="${sessionScope.MES == 'nothing'}">
                                <p class="welcome-paragraph">Bạn chưa có phòng trọ nào cả!</p>
                            </c:when>
                            <c:otherwise>
                                <p class="welcome-paragraph">Những bản tóm tắt đang ở đây, hãy xem qua!</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <!-- Summary revenue -->
                <div class="content-body">
                    <div class="summary-top-wrapper">
                        <div class="row">
                            <div class="col-12 col-md-5 summary-top">
                                <div class="summary-header">
                                    <div class="summary-title">Tóm tắt</div>
                                    <div class="summary-date">${requestScope.startDay}/${requestScope.timeNow} - ${requestScope.endDay}/${requestScope.timeNow}</div>
                                </div>
                                <div class="summary-income">
                                    <div class="summary-income_title">Doanh thu tháng này</div>
                                    <div class="summary-income_money">${requestScope.listMoneyOfOneAndTwoMonthAgo[0]} VNĐ</div>
                                    <div class="summary-income_compared">
                                            <span class="summary-income_compared-percent">
                                                <i class="fa-solid fa-arrow-trend-up"></i>
                                                ${requestScope.comparePercentOfTwoMonthAgo}%
                                            </span>
                                        so với
                                        <span class="summary-income_compared-prev-price">
                                                ${requestScope.listMoneyOfOneAndTwoMonthAgo[1]} VNĐ</span> của tháng trước
                                        <br/>
                                        <span>Bạn đang sở hữu: <strong>${requestScope.numberHostel}</strong> khu trọ</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-7 mt-4 mt-md-0">
                                <div class="summary-chart">
                                    <canvas id="revenueChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="summary-bottom-wrapper">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="summary-total-wrapper">
                                    <div class="summary-total-img success">
                                        <i class="summary-total-icon success fa-solid fa-hand-holding-dollar"></i>
                                    </div>
                                    <div class="summary-total-content">
                                        <div class="summary-total-price">${requestScope.averageMoneyOfHotel} VNĐ</div>
                                        <div class="summary-total-title">Doanh thu trung bình</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 mt-4 mt-sm-0">
                                <div class="summary-total-wrapper">
                                    <div class="summary-total-img error">
                                        <i class="summary-total-icon error fa-solid fa-screwdriver-wrench"></i>
                                    </div>
                                    <div class="summary-total-content">
                                        <div class="summary-total-price">${requestScope.averageReport} lần mỗi tháng</div>
                                        <div class="summary-total-title">Số lần báo cáo hư hỏng trung bình</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Sumary report -->
                <div class="row mt-4">
                    <div class="col-12 col-md-7 col-xl-6 col-xxl-5">
                        <div class="content-body">
                            <div class="report-summary-title">Tóm tắt báo cáo</div>
                            <div class="report-summary-list">
                                <div class="report-summary-item new">
                                    <div class="report-summary-status">Mới</div>
                                    <div class="report-summary-count">${requestScope.newReport}</div>
                                </div>
                                <div class="report-summary-item process">
                                    <div class="report-summary-status">Đang xử lí</div>
                                    <div class="report-summary-count">${requestScope.waittingReport}</div>
                                </div>
                                <div class="report-summary-item finished">
                                    <div class="report-summary-status">Xong</div>
                                    <div class="report-summary-count">${requestScope.doneReport}</div>
                                </div>
                            </div>
                            <div class="report-summary-result-percent">
                                Tỷ lệ tiếp nhận báo cáo: <span class="high">99%</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="components/footer.jsp"%>

</div>
<script>
    console.log(${requestScope.listMoneyOfOneAndTwoMonthAgo[0]})
    let listMonth = [${requestScope.listSixMonthAgo[5]},
        ${requestScope.listSixMonthAgo[4]},
        ${requestScope.listSixMonthAgo[3]},
        ${requestScope.listSixMonthAgo[2]},
        ${requestScope.listSixMonthAgo[1]},
        ${requestScope.listSixMonthAgo[0]}]
    console.log(listMonth);
    let listMoney = [${requestScope.listMoneyEachMonth[5]},
        ${requestScope.listMoneyEachMonth[4]},
        ${requestScope.listMoneyEachMonth[3]},
        ${requestScope.listMoneyEachMonth[2]},
        ${requestScope.listMoneyEachMonth[1]},
        ${requestScope.listMoneyEachMonth[0]}]
    console.log(listMoney);
</script>
<!-- Script Bootstrap !important -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<!-- JQuery -->
<script src="./assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<!-- Link your script here -->
<script src="./assets/js/handle-main-navbar.js"></script>
<!-- Chart JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="./assets/js/owner/dashboard/revenue-chart.js"></script>

<!-- Loader -->
<script src="./assets/js/loading-handler.js"></script>
</body>

</html>