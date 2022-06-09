<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi" id="top">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon" />

    <!-- Title -->
    <title>Trang chủ</title>

    <!-- Bootstrap 5.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Core CSS -->
    <link rel="stylesheet" href="./assets/css/core_style/core.css">

    <!-- AOS JS -->
    <link rel="stylesheet" href="./assets/js/aos_js/dist/aos.css">
    <script src="./assets/js/aos_js/dist/aos.js"></script>

    <!-- Link your own CSS here -->
    <link rel="stylesheet" href="./assets/css/admin_page/show-list-account-watting.css">
</head>

<body>
<div class="navbar row">
    <div class="col-md-9 col-sm-9 col-lg-9 title">
        <h2>Admin</h2>
    </div>
    <div class="col-md-3 col-sm-3 col-lg-3 logout"><a href="../system/login.jsp"><button>Logout</button></a></div>
</div>
<div class="content row">
    <div class="content-left col-sm-3">
        <a href="./admin.jsp"><button>Trang Chủ</button></a>
        <br>
        <a href=""><button style="color: #ffffff; background-color: #2f4eff;">Tài Khoản</button></a>

    </div>
    <div class="content-right col-sm-9 row">
        <%
        ArrayList<Account> list = new ArrayList();
        list = (ArrayList) request.getAttribute("OWNER_LIST");
        if (list.size()==0){
            %>
        <h1>Danh sach trong</h1>
        <%
        }
        else{%>
        <table>
            <tr>
                <th>Account Name</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%for (int i=0; i<list.size(); i++){
                if(list.get(i).getStatus()==0){
            %>
            <tr>
                <td><%= list.get(i).getUsername() %></td>
                <td><%= list.get(i).getStatus() %></td>
                <td>
                    <form action="updateAccountStatus" method="post">
                        <input type="hidden" name="owner_id" value="<%=list.get(i).getAccId()%>">
                        <input type="hidden" name="status" value="<%=list.get(i).getStatus()%>">
                        <input type="submit" value="Active"/>
                    </form>
                </td>
            </tr>
            <%
            }
            }
        %>
        </table>
       <%
        }%>
    </div>
</div>
<footer>

</footer>











<!-- Script Bootstrap !important -->
<script src="./assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<!-- Jquery -->
<script src="./assets/js/jquery-3.5.1.min.js"></script>
<!-- Link your script here -->
<script src="./assets/js/system/home-handle.js"></script>
<script src="../../assets/js/admin/admin-list-account.js"></script>
</body>

</html>