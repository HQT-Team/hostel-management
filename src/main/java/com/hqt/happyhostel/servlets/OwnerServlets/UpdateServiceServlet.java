package com.hqt.happyhostel.servlets.OwnerServlets;

import com.hqt.happyhostel.dao.HostelServiceDAO;
import com.hqt.happyhostel.dao.ServiceInfoDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.ServiceInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateServiceServlet", value = "/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {
    private final String SUCCESS = "add-update-service-noti";
    private final String FAIL = "detailHostel?hostelID=";
    private final String ERROR = "error-page";

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        HandlerStatus handlerStatus = null;
        try {
            url = FAIL;
            int hostelId = Integer.parseInt(request.getParameter("hostel-id"));
            String[] servicesIdStr = request.getParameterValues("update-service-id");
            String[] servicesPriceStr = request.getParameterValues("update-service-price");
            HostelServiceDAO hostelServiceDAO = new HostelServiceDAO();

            // Remove current hostel services
            List<HostelService> currentHostelServiceList = hostelServiceDAO.getCurrentListServicesOfAHostel(hostelId);
            boolean checkUpdate = hostelServiceDAO.updateStatusOfListHostelServices(0, currentHostelServiceList);
            if (!checkUpdate) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
                url += hostelId;
            } else {
                List<HostelService> hostelServiceList = new ArrayList<>();
                for (int i = 0; i < servicesIdStr.length; i++) {
                    hostelServiceList.add(HostelService.builder()
                            .serviceID(Integer.parseInt(servicesIdStr[i]))
                            .servicePrice(Integer.parseInt(servicesPriceStr[i])).build());
                }

                checkUpdate = hostelServiceDAO.insertListServicesIntoHostel(hostelServiceList, hostelId);
                if (checkUpdate) {

                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Cập nhật dịch vụ thành công!").build());

                    String content = "Cập nhật dịch vụ: \n";
                    List<ServiceInfo> serviceInfoList = new ServiceInfoDAO().getServicesOfHostel(hostelId);
                    for (ServiceInfo item: serviceInfoList) {
                        content += item.getServiceName() + ": " + item.getServicePrice() + " || ";
                    }

                    request.setAttribute("noti-hostel-id", hostelId);
                    request.setAttribute("noti-title", "Thông báo cập nhật dịch vụ");
                    request.setAttribute("noti-content", content);
                    url = SUCCESS;
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Cập nhật dịch vụ thất bại!").build());
                }
            }
        } catch (Exception e) {
            log("Error at UpdateServiceServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
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
