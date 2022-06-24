package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.HostelServiceDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.HostelService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateServiceServlet", value = "/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "detailHostel?hostelID=";
        try {
            int hostelId = Integer.parseInt(request.getParameter("hostel-id"));
            String[] servicesIdStr = request.getParameterValues("update-service-id");
            String[] servicesPriceStr = request.getParameterValues("update-service-price");

            List<HostelService> hostelServiceList = new ArrayList<>();
            for (int i = 0; i < servicesIdStr.length; i ++) {
                hostelServiceList.add(HostelService.builder()
                        .serviceID(Integer.parseInt(servicesIdStr[i]))
                        .servicePrice(Integer.parseInt(servicesPriceStr[i])).build());
            }

            boolean checkUpdate = new HostelServiceDAO().insertListServicesIntoHostel(hostelServiceList, hostelId);
            if (checkUpdate) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Cập nhật dịch vụ thành công!").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Cập nhật dịch vụ thất bại!").build());
            }
            url += hostelId;
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
