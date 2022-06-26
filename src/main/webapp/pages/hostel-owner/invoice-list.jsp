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
                                <form action="" method="post">
                                    <td>
                                        <select name="" id="filter__hostel-select-1"
                                                style="min-width: 100px; max-width: 200px;">
                                            <option value="">Tất cả</option>
                                            <option value="">Nova Land</option>
                                            <option value="">Nova Sky</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="" id="filter__room-select-1"
                                                style="min-width: 100px; max-width: 200px;">
                                            <option value="">Tất cả</option>
                                            <option value="">1</option>
                                            <option value="">2</option>
                                            <option value="">3</option>
                                            <option value="">4</option>
                                        </select>
                                    </td>
                                </form>
                            </tr>
                        </table>
                    </div>
                    <!-- Infor box -->
                    <div class="content__body">
                        <table id="invoice-table-1" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                            <th class="text-center">Mã</th>
                            <th class="text-center">Tên</th>
                            <th class="text-center">Phòng số</th>
                            <th class="text-center">Khu trọ</th>
                            <th class="text-center">Tổng tiền</th>
                            <th class="text-center">Ngày tạo</th>
                            </thead>
                            <tbody class="content__tbody">
                            <tr>
                                <td class="text-center">
                                    <a href="./invoice-detail.html">#IV123</a>
                                </td>
                                <td class="text-center">
                                    <a href="./invoice-detail.html">02/2022</a>
                                </td>
                                <td class="text-center">1</td>
                                <td class="text-center">Nova Land</td>
                                <td class="text-center">2.500.000 VNĐ</td>
                                <td class="text-center">21/01/2022</td>
                            </tr>
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
                                <form action="" method="post">
                                    <td>
                                        <select name="" id="filter__hostel-select-2"
                                                style="min-width: 100px; max-width: 200px;">
                                            <option value="">Tất cả</option>
                                            <option value="">Fake Land</option>
                                            <option value="">Fake Sky</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="" id="filter__room-select-2"
                                                style="min-width: 100px; max-width: 200px;">
                                            <option value="">Tất cả</option>
                                            <option value="">7</option>
                                            <option value="">8</option>
                                            <option value="">9</option>
                                            <option value="">10</option>
                                        </select>
                                    </td>
                                </form>
                            </tr>
                        </table>
                    </div>
                    <!-- Infor box -->
                    <div class="content__body">
                        <table id="invoice-table-2" class="content__table table table-bordered table-striped">
                            <thead class="content__thead">
                            <th class="text-center">Mã</th>
                            <th class="text-center">Tên</th>
                            <th class="text-center">Phòng số</th>
                            <th class="text-center">Khu trọ</th>
                            <th class="text-center">Tổng tiền</th>
                            <th class="text-center">Ngày tạo</th>
                            </thead>
                            <tbody class="content__tbody">
                            <tr>
                                <td class="text-center">
                                    <a href="./invoice-detail.html">#IV124</a>
                                </td>
                                <td class="text-center">
                                    <a href="./invoice-detail.html">03/2022</a>
                                </td>
                                <td class="text-center">1</td>
                                <td class="text-center">Nova Land</td>
                                <td class="text-center">2.900.000 VNĐ</td>
                                <td class="text-center">21/01/2022</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@include file="./components/footer.jsp"%>

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
<script>
    $(document).ready(function () {

        const tabs = document.querySelectorAll(".tabs-item");
        const contents = document.querySelectorAll(".content__item");

        const tabActive = document.querySelector(".tabs-item.active");
        const line = document.querySelector(".tabs .line");

        let i = 0, lengthTabs = tabs.length;

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
            $(`#filter__hostel-select-${i}`).select2();
            $(`#filter__room-select-${i}`).select2();

            // Initial datatable
            $(`#invoice-table-${i}`).DataTable();
        }

        for (let i = 0; i < 2; i++) {
            contents[i].classList.remove("active");
            tabs[i].classList.remove("active");
        }

        ((index = 0) => {
            tabs[index].classList.add("active");
            contents[index].classList.add("active");
        })();

    });
</script>
<!-- Preload -->
<script src="./assets/js/handle-preloader.js" type="text/javascript"></script>
</body>

</html>
