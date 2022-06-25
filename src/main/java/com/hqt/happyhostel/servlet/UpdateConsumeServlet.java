package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.ConsumeDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateConsumeServlet", value = "/UpdateConsumeServlet")
public class UpdateConsumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-hostels";
        try {
            int numberElectric = Integer.parseInt(request.getParameter("number-electric"));
            int numberWater = Integer.parseInt(request.getParameter("number-water"));
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            Consume consume = Consume.builder()
                    .numberWater(numberWater)
                    .numberElectric(numberElectric)
                    .status(0)
                    .roomID(roomID).build();
            boolean isSuccess = new ConsumeDAO().updateConsumeNumber(consume);
            if (isSuccess) {
                url = "roomDetail";
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Cập nhật số tiêu thụ thành công!").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Cập nhật số tiêu thụ thất bại!").build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
