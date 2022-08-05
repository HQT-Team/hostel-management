<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="vi_VN"/>
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

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">
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
                <c:if test="${sessionScope.NO_HAVE_HOSTEL ne null && sessionScope.NO_HAVE_HOSTEL eq true}" >
                    <!-- Warning -->
                    <div class="content-warning mt-5">
                        <div class="blink"></div>
                        <span>Hệ thống nhận thấy bạn chưa có khu trọ nào cả! <a href="add-hostel">
                                Click vào đây để tạo khu trọ mới nhen!</a>
                        </span>
                    </div>
                </c:if>

                <!-- Welcome -->
                <div class="content-welcome mt-5">
                    <img src="./assets/images/avatars/${sessionScope.USER.accountInfo.information.sex == 1 ? "male" : "female"}.jpg" alt="User avatar" class="welcome-image">
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
                                    <div class="summary-title">Tóm tắt - ${requestScope.Hostel != null ? requestScope.Hostel.hostelName : "Trống"}</div>
                                    <div class="summary-date">${requestScope.StartDay} - ${requestScope.EndDay}</div>
                                </div>
                                <div class="summary-income">
                                    <div class="summary-income_title">Doanh thu tháng trước</div>
                                    <div class="summary-income_money">
                                        <fmt:formatNumber value="${requestScope.ListMoneyEachMonth.get(0)}" type="currency" currencySymbol="VNĐ"/>
                                    </div>
                                    <div class="summary-income_compared">
                                        <c:choose>
                                            <c:when test="${requestScope.ComparePercentOfTwoMonthAgo < 0}">
                                                <span class="summary-income_compared-percent down">
                                                    <i class="fa-solid fa-arrow-trend-down"></i>
                                                    ${requestScope.ComparePercentOfTwoMonthAgo}%
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="summary-income_compared-percent up">
                                                    <i class="fa-solid fa-arrow-trend-up"></i>
                                                    ${requestScope.ComparePercentOfTwoMonthAgo}%
                                                </span>
                                            </c:otherwise>
                                        </c:choose>
                                        so với
                                        <span class="summary-income_compared-prev-price">
                                            <fmt:formatNumber value="${requestScope.ListMoneyEachMonth.get(1)}" type="currency" currencySymbol="VNĐ"/>
                                            </span> của tháng trước đó
                                        <br/>
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
                                        <div class="summary-total-price">
                                            <fmt:formatNumber value="${requestScope.AverageMoneyOfHotel}" type="currency" currencySymbol="VNĐ"/>
                                        </div>
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
                                        <div class="summary-total-price">${requestScope.AverageReport} lần mỗi tháng</div>
                                        <div class="summary-total-title">Số lần báo cáo trung bình</div>
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
                                    <div class="report-summary-count">${requestScope.NumberNewReport}</div>
                                </div>
                                <div class="report-summary-item process">
                                    <div class="report-summary-status">Đang xử lí</div>
                                    <div class="report-summary-count">${requestScope.NumberProcessReport}</div>
                                </div>
                                <div class="report-summary-item finished">
                                    <div class="report-summary-status">Xong</div>
                                    <div class="report-summary-count">${requestScope.NumberDoneReport}</div>
                                </div>
                            </div>
                            <div class="report-summary-result-percent">
                                Tỷ lệ tiếp nhận báo cáo trong 1 ngày kể từ thời điểm nhận báo cáo:
                                <c:choose>
                                    <c:when test="${requestScope.RATE_REPLY_REPORT > 70.0}">
                                        <span class="high">${requestScope.RATE_REPLY_REPORT}%</span>
                                    </c:when>
                                    <c:when test="${requestScope.RATE_REPLY_REPORT > 40.0}">
                                        <span class="medium">${requestScope.RATE_REPLY_REPORT}%</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="low">${requestScope.RATE_REPLY_REPORT}%</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="mt-4 mt-md-0 col-12 col-md-5 col-xl-6 col-xxl-7">
                        <div class="content-body">
                            <div class="overview-title">Tổng quan toàn diện</div>
                            <div class="overview-items">Tổng số khu trọ:
                                <span>${requestScope.NumberHostel eq null ? "0" : requestScope.NumberHostel}</span>
                            </div>
                            <div class="overview-items">Tổng số phòng trọ: <span>${requestScope.NumberRoom}</span></div>
                            <div class="overview-items">Tổng số phòng đã được thuê: <span>${requestScope.NumberRentedRoom}</span></div>
                            <div class="overview-items">Tổng số phòng chưa được thuê: <span>${requestScope.NumberReadyRoom}</span></div>
                            <div class="overview-items">Tổng số phòng đang được làm hợp đồng: <span>${requestScope.NumberProcessRoom}</span></div>
                            <div class="overview-items">Số thông báo đã gửi: <span>${requestScope.NumberNotification}</span></div>
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
<script>
    let list = ${requestScope.ListMonthAndYear};
    const newList = list.map(item => item.join('/')).reverse();
    let listMonth = [
        ...newList
    ];
    let tmp = ${requestScope.ListMoneyEachMonth};
    tmp.reverse();
    let listMoney = [
        ...tmp
    ];
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
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>

<script type="text/javascript">
    // Receive
    receiveWebsocket(alertPushNoti);

    // Close when leave
    window.onbeforeunload = function(){
        receiveWebsocket.disconnectWebSocket();
    };
</script>
<!-- Loader -->
<script src="./assets/js/loading-handler.js"></script>


</body>

</html>