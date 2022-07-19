package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EndRentalServlet", value = "/EndRentalServlet")
public class EndRentalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "roomDetail?roomID=";
        try {
            int roomId = Integer.parseInt(request.getParameter("room-id"));
            int renterAccountId = Integer.parseInt(request.getParameter("renter-account-id"));

            AccountDAO accountDAO = new AccountDAO();
            RoomDAO roomDAO = new RoomDAO();
            ContractDAO contractDAO = new ContractDAO();

            boolean updateResult = accountDAO.updateAccountStatus(renterAccountId, 2);

            boolean updateRoomStatusResult = roomDAO.updateRoomStatus(roomId, 1);

            boolean updateContractStatus = contractDAO.updateContractStatus(roomId, renterAccountId);

            if (updateResult && updateRoomStatusResult && updateContractStatus) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Bạn đã kết thúc hợp đồng thành công!").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Kết thúc hợp đồng thất bại!").build());
            }
            url += roomId;
        } catch (Exception e) {
            log("Error at EndRentalServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
