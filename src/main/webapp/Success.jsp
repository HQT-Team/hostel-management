<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21/05/2022
  Time: 9:12 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <option value="1" selected>True</option>
        <%--        <option value="1">True</option>--%>
        <option value="0">False</option>
    </select> <br>
    Diện tích: <input type="number" value="" name="txtRoomArea"> <br>
    Nhà vệ sinh: <input type="number" value="1" name="txtNumberRestrooms"> <br>
    Cửa sổ: <input type="number" value="1" name="txtNumberWindows"> <br>
    Máy lạnh: <input type="number" value="1" name="txtNumberAirConditions"> <br>
    <button type="submit" value="createRoom" name="action">Tạo mới</button>
    <br>

    <c:choose>
        <c:when test="${requestScope.isSuccess eq 'false'}">
            <td>Room number already existed, try again</td>
        </c:when>
        <c:when test="${requestScope.isSuccess eq 'true'}">
            <td>Add room(s) success</td>
        </c:when>
    </c:choose>
</form>
<br>

<c:set var="hostel" value="${requestScope.hostel}"></c:set>

<c:set var="room" value="${requestScope.roomInformation}"/>
Phòng: <c:out value="${room.getRoomNumber()}"></c:out> <br>
Khu trọ trực thuộc: <c:out value="${hostel.getName()}"></c:out> <br>
Địa chỉ: <c:out value="${hostel.getAddress()}"></c:out>, quận <c:out value="${hostel.getDistrict()}"></c:out>,TP. <c:out
        value="${hostel.getCity()}"></c:out> <br>

<c:set var="contract" value="${requestScope.contract}"></c:set>
Ngày bắt đầu hợp đồng: <c:out value="${contract.getStartDate()}"></c:out> <br>
Ngày kết thúc hợp đồng: <c:out value="${contract.getExpiration()}"></c:out> <br>
Tiền cọc: <c:out value="${contract.getDeposit()}"></c:out> <br>

<c:set var="invoice" value="${requestScope.invoice}"/>
<c:set var="payment" value="${requestScope.payment}"/>
<c:set var="consume" value="${requestScope.consume}"/>

<h3>Số điện/nước tiêu thụ</h3>
Ngày cập nhật gần nhất: <c:out value="${consume.getEndConsumeDate()}"></c:out> <br>
Điện: <c:out value="${consume.getNumberElectric()}"></c:out> <br>
Nước: <c:out value="${consume.getNumberWater()}"></c:out> <br>


<h3>Hóa đơn gần nhất</h3>
Ngày tạo: <c:out value="${invoice.getCreatedDate()}"></c:out> <br>
Trạng thái: <c:choose>
    <c:when test="${invoice.getPaymentStatus() eq 0}">
        <c:out value="Chưa thanh toán"></c:out>
    </c:when>
    <c:when test="${invoice.getPaymentStatus() eq 1}">
        <c:out value="Đã thanh toán"></c:out>
    </c:when>
</c:choose> <br>
Hình thức thanh toán: <c:out value="${payment.getPaymentMethod()}"></c:out> <br>
Tổng tiền: <c:out value="${invoice.getTotalMoney()}"></c:out>vnđ <br>

<h3>Danh sách thành viên</h3>
<table>
    <tr>
        <th>Họ và tên</th>
        <th>Giới tính</th>
        <th>Địa chỉ</th>
        <th>Số điện thoại</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="roommateList" items="${requestScope.roommateList}">
        <tr>
            <td><c:out value="${roommateList.getFullName()}"></c:out></td>
            <td>
                <c:choose>
                    <c:when test="${roommateList.getSex() eq 1}">
                        <c:out value="Nam"></c:out>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Nữ"></c:out>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><c:out value="${roommateList.getAddress()}"></c:out></td>
            <td><c:out value="${roommateList.getPhone()}"></c:out></td>
            <td>
                <input type="submit" value="Chi tiết">
                <input type="submit" value="Cập nhật">
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Danh sách các thiết bị trong phòng</h3>
<table>
    <tr>
        <th>Tên thiết bị</th>
        <th>Số lượng</th>
        <th>Tình trạng</th>
    </tr>
    <c:forEach var="infrastructures" items="${requestScope.infrastructures}">
        <tr>
            <td><c:out value="${infrastructures.getName()}"></c:out></td>
            <td><c:out value="${infrastructures.getQuantity()}"></c:out></td>
            <td>
                <c:choose>
                    <c:when test="${infrastructures.getStatus() eq 1}">
                        <c:out value="Tốt"></c:out>
                    </c:when>
                    <c:otherwise>
                        <c:out value="Hỏng"></c:out>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<c:set var="infras" value="${requestScope.infrastructures}"/>

<h3>Thay đổi thông tin phòng</h3>
<form action="updateRoom" method="post">
    Tên phòng: <input type="number" value="${room.getRoomNumber()}" name="txtRoomNumber"> <br>
    Diện tích: <input type="number" value="${room.getRoomArea()}" name="txtRoomArea"> <br>
    Gác:
    <select name="hasAttic">
        <c:choose>
            <c:when test="${room.getHasAttic() eq 1}">
                <option value="${room.getHasAttic()}" selected>Có</option>
                <option value="0">False</option>
            </c:when>
            <c:otherwise>
                <option value="${room.getHasAttic()}" selected>Không</option>
                <option value="1">True</option>
            </c:otherwise>
        </c:choose>
    </select> <br>

    Nhà vệ sinh: <input type="number" value="${infras.get(0).getQuantity()}" name="restRoomQuantity">. Tình trạng:
    <select
            name="restRoomStatus">
        <c:choose>
            <c:when test="${infras.get(0).getStatus() eq 1}">
                <option value="1" selected>Tốt</option>
                <option value="0">Hỏng</option>
            </c:when>
            <c:otherwise>
                <option value="0" selected>Hỏng</option>
                <option value="1">Tốt</option>
            </c:otherwise>
        </c:choose>
    </select> <br>

    Cửa sổ: <input type="number" value="${infras.get(1).getQuantity()}" name="windowsQuantity">. Tình trạng: <select
        name="windowsStatus">
    <c:choose>
        <c:when test="${infras.get(1).getStatus() eq 1}">
            <option value="1" selected>Tốt</option>
            <option value="0">Hỏng</option>
        </c:when>
        <c:otherwise>
            <option value="0" selected>Hỏng</option>
            <option value="1">Tốt</option>
        </c:otherwise>
    </c:choose>
</select> <br>

    Máy lạnh: <input type="number" value="${infras.get(2).getQuantity()}" name="airConditionsQuantity">. Tình trạng:
    <select name="airConditionStatus">
        <c:choose>
            <c:when test="${infras.get(2).getStatus() eq 1}">
                <option value="1" selected>Tốt</option>
                <option value="0">Hỏng</option>
            </c:when>
            <c:otherwise>
                <option value="0" selected>Hỏng</option>
                <option value="1">Tốt</option>
            </c:otherwise>
        </c:choose>
    </select> <br>

    <button type="submit" value="updateRoom" name="action">Cập nhật phòng</button>
</form>
<br>

</body>

</html>
