<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28/05/2022
  Time: 10:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/images/favicon.png" type="image/x-icon" />
    <title>Trang chủ</title>
    <!-- Link Bootstrap !important -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Base CSS !important -->
    <link rel="stylesheet" href="./assets/css/style.css">
    <!-- Link your CSS here -->
    <link rel="stylesheet" href="./assets/css/hostel_owner_style/hostel_detail_style/style.css">
    <!-- Simple Datatable CSS -->
    <link href="https://cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

</head>

<body>
<!-- Navbar -->
<%@include file="./Component/hostel-owner/navbar.jsp"%>

<!-- Body -->
<div class="container">
    <div class="row position-relative">
        <!-- Side bar -->
        <div class="col-12 col-lg-3 col-xl-3 col-xxl-2">
        <%@include file="./Component/hostel-owner/sidebar.jsp"%>
        </div>

        <!-- Content -->
        <div class="col-12 col-lg-9 col-xl-9 col-xxl-10 col-xxl-10 content-group">
        </div>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="copyright-wrapper d-flex justify-content-center">
                    <div class="copyright-logo">
                        <img src="./assets/images/hql_logo_white_notext.svg" alt="Logo">
                    </div>
                    <div class="copyright-content">© 2022 HQT-Hostel. All rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- Waring modal -->
<div class="modal fade" id="updateServicesModel" tabindex="-1" aria-labelledby="updateServicesModelLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title updateServicesModel-label" id="updateServicesModelLabel">Cảnh báo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body updateServicesModel-content">
                Bạn nên gửi thông báo đến người thuê trước khi cập nhật giá dịch vụ mới đẻ giảm thiểu các trường hợp
                khiếu nại không mong muốn từ người thuê!
            </div>
            <div class="modal-footer updateServicesModel-action">
                <button type="button" class="btn btn-secondary back-btn" data-bs-dismiss="modal">Quay lại</button>
                <a href="" class="btn btn-primary continue-btn">Đã rõ và tiếp tục</a>
            </div>
        </div>
    </div>
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
<script>
    $(document).ready(function () {
        // Initial datatable
        $('#rooms-table').DataTable();
    });
</script>
</body>

</html>
