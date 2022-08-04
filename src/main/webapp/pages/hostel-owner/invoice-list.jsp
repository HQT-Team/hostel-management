<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>

    <!-- Title -->
    <title>Hóa đơn</title>

    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/invoices_style/style.css">

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

            <div class="col-xxl-9 m-auto">
                <div class="col-12 col-md-8 col-lg-8 col-xl-7 col-xxl-8">
                    <!-- Tab menu -->
                    <div class="tabs">
                        <div class="tabs-item active">
                            <i class="tabs-icon fa-solid fa-circle-exclamation"></i> Chưa thanh toán
                        </div>
                        <div class="tabs-item active">
                            <i class="tabs-icon fa-solid fa-file-circle-check"></i> Đã thanh toán
                        </div>
                        <div class="line"></div>
                    </div>
                </div>

                <!-- Content item - Invoice Not pay yet -->
                <div class="content__item active">
                    <!-- filter bar -->
                    <div class="filter__wrapper">
                        <table>
                            <tr>
                                <td></td>
                                <td>Khu trọ</td>
                                <td>Phòng</td>
                            </tr>
                            <tr>
                                <td><i class="fa-solid fa-sliders"></i> Lọc</td>
                                <form id="form-no-payment-bill">
                                    <td>
                                        <select name="hostelId" id="filter__hostel-select-1">
                                            <option value="">Tất cả</option>
                                            <c:forEach var="hostel" items="${requestScope.LIST_HOSTELS}">
                                                <option value="${hostel.hostelID}">${hostel.hostelName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="roomId" id="filter__room-select-1" disabled>
                                            <option value="">Tất cả</option>
                                        </select>
                                    </td>
                                </form>
                            </tr>
                        </table>
                    </div>
                    <!-- Infor box -->
                    <div id="content__body-1" class="content__body mb-5">
                        <table id="invoice-table-1" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                                <tr>
                                    <th class="text-center">Mã</th>
                                    <th class="text-center">Tên</th>
                                    <th class="text-center">Phòng số</th>
                                    <th class="text-center">Khu trọ</th>
                                    <th class="text-center">Tổng tiền</th>
                                    <th class="text-center">Ngày tạo</th>
                                </tr>
                            </thead>
                            <tbody class="content__tbody">
                            <c:set var="hostelName" value="${sessionScope.INVOICE_NOT_PAYMENT_LIST_HOSTEL_NAME}"/>
                            <c:set var="hostelID" value="${sessionScope.INVOICE_NOT_PAYMENT_LIST_HOSTEL_ID}"/>
                            <c:set var="roomNumber" value="${sessionScope.INVOICE_NOT_PAYMENT_LIST_ROOM_NUMBER}"/>
                            <c:forEach var="bill" items="${sessionScope.INVOICE_NOT_PAYMENT_LIST}" varStatus="loop">
                                <tr>
                                    <td class="text-center">
                                        <a href="getRoomInvoiceDetail?billID=${bill.billID}&hostelID=${hostelID.get(loop.index)}&roomID=${bill.roomID}">#B${bill.billID}</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="getRoomInvoiceDetail?billID=${bill.billID}&hostelID=${hostelID.get(loop.index)}&roomID=${bill.roomID}">${bill.billTitle}</a>
                                    </td>
                                    <td class="text-center">${roomNumber[loop.index]}</td>
                                    <td class="text-center">${hostelName[loop.index]}</td>

                                    <td class="text-center">
                                        <fmt:formatNumber value="${bill.totalMoney}" type="currency" currencySymbol="VNĐ"/>
                                    </td>

                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${bill.createdDate}" var="createdDate"/>
                                    <td class="text-center">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${createdDate}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Content item - Invoice Paid -->
                <div class="content__item active">
                    <!-- filter bar -->
                    <div class="filter__wrapper">
                        <table>
                            <tr>
                                <td></td>
                                <td>Khu trọ</td>
                                <td>Phòng</td>
                            </tr>
                            <tr>
                                <td><i class="fa-solid fa-sliders"></i> Lọc</td>
                                <form id="form-payment-bill">
                                    <td>
                                        <select name="hostelId" id="filter__hostel-select-2">
                                            <option value="">Tất cả</option>
                                            <c:forEach var="hostel" items="${requestScope.LIST_HOSTELS}">
                                                <option value="${hostel.hostelID}">${hostel.hostelName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="roomId" id="filter__room-select-2" disabled>
                                            <option value="">Tất cả</option>
                                        </select>
                                    </td>
                                </form>
                            </tr>
                        </table>
                    </div>
                    <!-- Infor box -->
                    <div id="content__body-2" class="content__body mb-5">
                        <table id="invoice-table-2" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                                <tr>
                                    <th class="text-center">Mã</th>
                                    <th class="text-center">Tên</th>
                                    <th class="text-center">Phòng số</th>
                                    <th class="text-center">Khu trọ</th>
                                    <th class="text-center">Tổng tiền</th>
                                    <th class="text-center">Ngày tạo</th>
                                </tr>
                            </thead>
                            <tbody class="content__tbody">
                            <c:set var="hostelName" value="${sessionScope.INVOICE_PAYMENT_LIST_HOSTEL_NAME}"/>
                            <c:set var="roomNumber" value="${sessionScope.INVOICE_PAYMENT_LIST_ROOM_NUMBER}"/>
                            <c:set var="hostelID" value="${sessionScope.INVOICE_PAYMENT_LIST_HOSTEL_ID}"/>
                            <c:forEach var="bill" items="${sessionScope.INVOICE_PAYMENT_LIST}" varStatus="loop">
                                <tr>
                                    <td class="text-center">
                                        <a href="getRoomInvoiceDetail?billID=${bill.billID}&hostelID=${hostelID.get(loop.index)}&roomID=${bill.roomID}">#B${bill.billID}</a>
                                    </td>
                                    <td class="text-center">
                                        <a href="getRoomInvoiceDetail?billID=${bill.billID}&hostelID=${hostelID.get(loop.index)}&roomID=${bill.roomID}">${bill.billTitle}</a>
                                    </td>
                                    <td class="text-center">${roomNumber[loop.index]}</td>
                                    <td class="text-center">${hostelName[loop.index]}</td>

                                    <td class="text-center">
                                        <fmt:formatNumber value="${bill.totalMoney}" type="currency" currencySymbol="VNĐ"/>
                                    </td>

                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${bill.createdDate}" var="createdDate"/>
                                    <td class="text-center">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${createdDate}"/>
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
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp" %>

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
<!-- Select2 JS -->
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<!-- Axios -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<!-- Load data async -->
<script src="./assets/js/load-bill-async.js"></script>
<!-- Push notification -->
<script src="./assets/js/push-notification-alert.js"></script>
<!-- Web socket -->
<script src="./assets/js/receiveWebsocket.js"></script>
<!-- Toast Alert -->
<script src="./assets/js/toast-alert.js"></script>
<script>
    $(document).ready(function () {

        const tabs = document.querySelectorAll(".tabs-item");
        const contents = document.querySelectorAll(".content__item");

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

        for (let i = 1; i <= 2; i++) {
            // Select 2
            $('#filter__hostel-select-' + i).select2();
            $('#filter__room-select-' + i).select2();

            // Initial datatable
            $('#invoice-table-' + i).DataTable();
        }

        for (let i = 0; i < 2; i++) {
            contents[i].classList.remove("active");
            tabs[i].classList.remove("active");
        }

        ((index = 0) => {
            tabs[index].classList.add("active");
            contents[index].classList.add("active");
        })();

        // Filter for not payment bill
        $('#filter__hostel-select-1').on('change', () => {
            $('#form-no-payment-bill').submit();
        });

        $('#filter__room-select-1').on('change', () => {
            $('#form-no-payment-bill').submit();
        });

        // Filter
        $('#form-no-payment-bill').submit(function(e) {
            e.preventDefault();

            axios({
                method: 'post',
                url: 'http://localhost:8080/HappyHostel/get-invoice-list-async',
                params: {
                    'hostelId': $('#filter__hostel-select-1').find(':selected').val(),
                    'roomId': $('#filter__room-select-1').find(':selected').val(),
                    'type': 0
                }
            })
            .then(function (response) {
                loadBillAsync(response.data[0], response.data[1], response.data[2], 1);
                loadListRoomsAsync(response.data[3], response.data[4], 1);
            })
            .catch(function (error) {
                console.log(error);
            });
        });

        // Filter for payment bill
        $('#filter__hostel-select-2').on('change', () => {
            $('#form-payment-bill').submit();
        });

        $('#filter__room-select-2').on('change', () => {
            $('#form-payment-bill').submit();
        });

        // Filter
        $('#form-payment-bill').submit(function(e) {
            e.preventDefault();

            axios({
                method: 'post',
                url: 'http://localhost:8080/HappyHostel/get-invoice-list-async',
                params: {
                    'hostelId': $('#filter__hostel-select-2').find(':selected').val(),
                    'roomId': $('#filter__room-select-2').find(':selected').val(),
                    'type': 1
                }
            })
                .then(function (response) {
                    console.log(response);
                    loadBillAsync(response.data[0], response.data[1], response.data[2], 2);
                    loadListRoomsAsync(response.data[3], response.data[4], 2);
                })
                .catch(function (error) {
                    console.log(error);
                });
        });
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
