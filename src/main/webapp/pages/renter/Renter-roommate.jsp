<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hqt.happyhostel.dto.RoommateInfo" %>
<%@ page import="com.hqt.happyhostel.dto.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xem thành viên</title>
    <link rel="icon" href="./assets/images/favicon/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/core_style/core.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/renter_page/Renter-roommate.css">

    <!-- CSS Push Notification -->
    <link rel="stylesheet" href="./assets/css/push_notification_style/style.css">

</head>

<body>
<div>
        <%
    ArrayList<RoommateInfo> listroommateinfor = (ArrayList<RoommateInfo>) session.getAttribute("listroommateinfor");
    Account account = (Account)session.getAttribute("USER");
  %>
    <!-- navbar -->

    <!-- content -->
    <div class="main-body row" style="padding: 0;margin: 0;">
        <%@include file="components/sidebar.jsp" %>

        <div class="content">
    <%@include file="components/navbar.jsp" %>
            <c:if test="${listroommateinfor.size() == 0}">
                <h1 style="color: red; font-size: 26px; text-align: center; margin-top: 20px">Chưa Có Thành Viên</h1>
            </c:if>
            <c:if test="${listroommateinfor.size() != 0}">
                <h2 class="title-table">Danh Sách Bạn Cùng Phòng</h2>
                <div>
                    <table class="table">
                        <tr>
                            <th>STT</th>
                            <th>Họ và tên</th>
                            <th colspan="3">Hành Động</th>
                        </tr>
                        <%
                            int x = 1;
                        %>
                        <c:forEach items="${listroommateinfor}" var="roommateinfor">
                            <tr>
                                <td><%=x%>
                                </td>
                                <td><span>${roommateinfor.getInformation().getFullname()}</span></td>
                                <td>
                                    <button type="button" class="btnAction" data-bs-toggle="modal"
                                            data-bs-target="#staticBackdrop<%=x%>">Chi tiết
                                    </button>
                                </td>
                                <td>
                                    <a href="RoommateUpdateDetail?roommateID=${roommateinfor.roommateID}"
                                       role="button"
                                       class="btnAction">Chỉnh Sửa</a>
                                </td>
                                <td>
                                    <a href="DeleteRoommatePage?roommateID=${roommateinfor.roommateID}" role="button"
                                       class="btnAction">Xoá</a>
                                </td>

                            </tr>

                            <%
                                x += 1;
                            %>
                        </c:forEach>

                    </table>
                </div>
                <%
                    for (int y = 1; y < x; y++) {
                %>
                <div class="modal fade" id="staticBackdrop<%=y%>" data-bs-backdrop="static" data-bs-keyboard="false"
                     tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Thông Tin Chi Tiết
                                </h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body" style="text-align: left">
                                <h3>
                                    <strong>Tên: </strong> <%=listroommateinfor.get(y - 1).getInformation().getFullname()%>
                                </h3>
                                <h3>
                                    <strong>Email: </strong> <%=listroommateinfor.get(y - 1).getInformation().getEmail()%>
                                </h3>
                                <h3><strong>Ngày
                                    Sinh: </strong> <%=listroommateinfor.get(y - 1).getInformation().getBirthday()%>
                                </h3>
                                <h3><strong>Giới Tính: </strong>
                                    <c:if test="<%= listroommateinfor.get(y-1).getInformation().getSex() == 1 %>">
                                        Nam
                                    </c:if>
                                    <c:if test="<%= listroommateinfor.get(y-1).getInformation().getSex() == 0 %>">
                                        Nữ
                                    </c:if>
                                </h3>
                                <h3><strong>Số Điện
                                    Thoại: </strong> <%=listroommateinfor.get(y - 1).getInformation().getPhone()%>
                                </h3>
                                <h3><strong>Địa
                                    Chỉ: </strong> <%=listroommateinfor.get(y - 1).getInformation().getAddress()%>
                                </h3>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Thoát</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

            </c:if>
        </div>

    </div>

    <!-- footer -->

    <%@include file="components/footer.jsp" %>

    <!-- Push notification element -->
    <div id="push-noti"></div>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <script src="./assets/js/renter/Renter-roommate.js"></script>

    <!-- Push notification -->
    <script src="./assets/js/push-notification-alert.js"></script>
    <!-- Web socket -->
    <script src="./assets/js/receiveWebsocket.js"></script>

    <script type="text/javascript">
        // Receive
        receiveWebsocket(alertPushNoti);
        // Close when leave
        window.onbeforeunload = function () {
            receiveWebsocket.disconnectWebSocket();
        };
    </script>

</body>

</html>