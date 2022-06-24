package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateRoommateServlet", value = "/UpdateRoommateServlet")
public class UpdateRoommateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoommateInfoDAO roommateInfoDAO = new RoommateInfoDAO();

        String roomId = request.getParameter("roomID");
        try {
            int roommateId = Integer.parseInt(request.getParameter("roommate-id"));
            String fullName = request.getParameter("full-name");
            String dateOfBirth = request.getParameter("dob");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phone-number");
            String email = request.getParameter("email");
            String cccd = request.getParameter("cccd");
            String address = request.getParameter("address");
            String parentName = request.getParameter("parent-name");
            String parentPhone = request.getParameter("parent-phone");

            Information information = Information.builder()
                    .fullname(fullName)
                    .birthday(dateOfBirth)
                    .sex(gender)
                    .email(email)
                    .phone(phoneNumber)
                    .address(address)
                    .cccd(cccd).build();
            RoommateInfo roommateInfo = RoommateInfo.builder()
                    .roommateID(roommateId)
                    .information(information)
                    .parentName(parentName)
                    .parentPhone(parentPhone).build();

            boolean check = roommateInfoDAO.UpdateRoommateInfo(roommateInfo);
            if (check) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Cập nhật thông tin thành viên phòng thành công!").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra. Cập nhật thông tin thành viên thất bại!").build());
            }
        } catch (Exception e) {
            log("Error at UpdateRoommateServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher("roomDetail?roomID=" + roomId).forward(request, response);
        }
    }
}
