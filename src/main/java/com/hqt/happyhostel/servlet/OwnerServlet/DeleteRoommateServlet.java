package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteRoommateServlet", value = "/DeleteRoommateServlet")
public class DeleteRoommateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "roomDetail?roomID=";
        try {
            HttpSession session = request.getSession();

            int roomId = ((Room) session.getAttribute("room")).getRoomId();
            int renterAccountId = Integer.parseInt(request.getParameter("renter-account-id"));
            int roommateId = Integer.parseInt(request.getParameter("roommate-id"));

            RoommateInfoDAO roommateInfoDAO = new RoommateInfoDAO();
            boolean deleteStatus = roommateInfoDAO.DeleteRoommateInfo(renterAccountId, roommateId);
            if (deleteStatus) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Xóa thành viên thành công!").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Xóa thành viên thất bại").build());
            }
            url += roomId;
        } catch (Exception e) {
            log("Error at DeleteRoommateServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
