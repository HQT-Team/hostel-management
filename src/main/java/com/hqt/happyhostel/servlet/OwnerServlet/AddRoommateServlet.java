package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRoommateServlet", value = "/AddRoommateServlet")
public class AddRoommateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String roomId = request.getParameter("roomID");
        try {
            // Get parameters from client (room details -> Add roommate member button -> Form)
            int roomCapacity = Integer.parseInt(request.getParameter("room-capacity"));
            String currentRenterAccountUsername = request.getParameter("current-room-username");
            String fullName = request.getParameter("full-name");
            String dateOfBirth = request.getParameter("dob");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phone-number");
            String email = request.getParameter("email");
            String cccd = request.getParameter("cccd");
            String address = request.getParameter("address");
            String parentName = request.getParameter("parent-name");
            String parentPhone = request.getParameter("parent-phone");

            // Get AccountID of Current Renter by currentRenterAccountUsername
            int accountId = AccountDAO.getAccountIdByUserName(currentRenterAccountUsername);

            // Check get accountId is true or false
            if (accountId >= 0)  {
                List<RoommateInfo> listCurrentRoommate = RoommateInfoDAO.getListRoommatesOfAnAccount(accountId);

                if (listCurrentRoommate.size() < roomCapacity) {
                    Information information = Information.builder()
                            .fullname(fullName)
                            .birthday(dateOfBirth)
                            .sex(gender)
                            .email(email)
                            .phone(phoneNumber)
                            .address(address)
                            .cccd(cccd).build();
                    RoommateInfo roommateInfo = RoommateInfo.builder()
                            .information(information)
                            .parentName(parentName)
                            .parentPhone(parentPhone).build();

                    // Insert into RoommateInformation table
                    boolean check = RoommateInfoDAO.AddRoommateInformationOfAnAccount(roommateInfo, accountId);

                    if (check) {
                        request.setAttribute("SUCCESS", "Thêm thành viên mới vào phòng thành công");
                    } else {
                        request.setAttribute("ERROR", "Đã có lỗi xảy ra! Vui lòng thử lại sau");
                    }
                } else {
                    request.setAttribute("ERROR", "Phòng đã đạt số lượng thành viên tối đa! Thêm thành viên thất bại!");
                }
            } else {
                request.setAttribute("ERROR", "Đã có lỗi xảy ra! Vui lòng thử lại sau");
            }
        } catch (Exception e) {
            log("Error at AddRoommateServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher("roomDetail?roomID=" + roomId).forward(request, response);
        }
    }
}
