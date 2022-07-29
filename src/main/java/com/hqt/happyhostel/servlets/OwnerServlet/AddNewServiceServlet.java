package com.hqt.happyhostel.servlets.OwnerServlet;

import com.hqt.happyhostel.dao.HostelServiceDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.HostelService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNewServiceServlet", value = "/AddNewServiceServlet")
public class AddNewServiceServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "detailHostel?hostelID=";
        try {
            int hostelId = Integer.parseInt(request.getParameter("hostel-id"));
            int serviceId = Integer.parseInt(request.getParameter("service-id"));
            int servicePrice = Integer.parseInt(request.getParameter("service-price"));

            HostelServiceDAO hostelServiceDAO = new HostelServiceDAO();

            List<HostelService> list = hostelServiceDAO.getCurrentListServicesOfAHostel(hostelId);
            boolean checkUpdate = hostelServiceDAO.updateStatusOfListHostelServices(0, list);
            if (!checkUpdate) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
                url += hostelId;
            } else {
                list.add(HostelService.builder()
                        .serviceID(serviceId)
                        .hostelID(hostelId)
                        .servicePrice(servicePrice).build());

                boolean checkInsert = hostelServiceDAO.insertListServicesIntoHostel(list, hostelId);
                if (checkInsert) {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Thêm dịch vụ mới thành công!").build());
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Thêm dịch vụ mới thất bại!").build());
                }
                url += hostelId;
            }
        } catch (Exception e) {
            log("Error at UpdateServiceServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
