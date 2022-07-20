package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.HostelServiceDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateServiceServlet", value = "/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {

    private String otp = RandomStringGenerator.randomOTP(5);

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "detailHostel?hostelID=";
        HandlerStatus handlerStatus = null;
        try {
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
                for (int i = 0; i < servicesIdStr.length; i ++) {
                    hostelServiceList.add(HostelService.builder()
                            .serviceID(Integer.parseInt(servicesIdStr[i]))
                            .servicePrice(Integer.parseInt(servicesPriceStr[i])).build());
                }

                checkUpdate = hostelServiceDAO.insertListServicesIntoHostel(hostelServiceList, hostelId);
                if (checkUpdate) {

                    /*------------------Gui Mail------------------*/
                    ArrayList<String> accMailList = new ArrayList<>();
                    String mail = null;
                    ArrayList<Integer> renterList = new HostelDAO().getListRenterIdByHostelId(hostelId);
                    for (int id : renterList) {
                        mail = new AccountDAO().getAccountInformationById(id).getInformation().getEmail();
                        if (mail != null) {
                            accMailList.add(mail);
                        }
                    }


                if (accMailList != null && accMailList.size() > 0) {
                    String domain = "http://localhost:8080/HappyHostel/RenterNotificationPage";
                    if (new MailUtils().SendMailNotice(accMailList, domain)) {
                        handlerStatus = HandlerStatus.builder().status(true).content("Mail đã được gửi thành công. Vui lòng kiểm tra Email của bạn.").build();
                    } else {
                        handlerStatus = HandlerStatus.builder().status(false).content("Không thể gửi Mail. Vui lòng kiểm tra lại các thông tin.").build();
                    }
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
                /*------------------Gui Mail------------------*/

                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Cập nhật dịch vụ thành công!").build());
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Cập nhật dịch vụ thất bại!").build());
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
