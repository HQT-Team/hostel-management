<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21/05/2022
  Time: 9:12 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h1>Dashboard</h1>
<h1>Welcome ${requestScope.owner.hostelOwnerInfo.information.fullname}</h1>

<form action="addRoom" method="post">
    Số lượng: <input type="number" value="1" name="txtQuantityRoom"> <br>
    Tên Phòng: <input type="text" value="1" name="txtRoomName"> <br>
    Số lượng thành viên tối đa: <input type="number" value="4" name="txtCapacity"> <br>
    Gác:
    <select name="txtAttic">
        <option value="true" selected disabled hidden>True</option>
        <option value="1">True</option>
        <option value="0">False</option>
    </select> <br>
    Diện tích: <input type="number" value="" name="txtRoomArea"> <br>
    Nhà vệ sinh: <input type="number" value="1" name="txtNumberRestrooms"> <br>
    Cửa sổ: <input type="number" value="1" name="txtNumberWindows"> <br>
    Máy lạnh: <input type="number" value="1" name="txtNumberAirConditions"> <br>
    <button type="submit" value="createRoom" name="action">Tạo mới</button>
</form>

</body>

</html>
