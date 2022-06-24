package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.Hostel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddHostelServlet", value = "/AddHostelServlet")
public class AddHostelServlet extends HttpServlet {
    public static final String ERROR = "add-hostel";
    public static final String SUCCESS = "add-hostel";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validDate = dateObj.format(formatter);
        List<HostelService> hostelServiceList = new ArrayList<>();
        Account acc;

        try {
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            acc = (Account) session.getAttribute("USER");
            HostelDAO dao = new HostelDAO();

            int accountId = acc.getAccId();
            String hostelName = req.getParameter("hostel-name");
            String hostelAddress = req.getParameter("hostel-address");
            String hostelProvince = req.getParameter("hostel-province");
            String hostelDistrict = req.getParameter("hostel-district");
            String hostelWard = req.getParameter("hostel-ward");

            //electric
            int electricityPrice = Integer.parseInt(req.getParameter("hostel-electric"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(electricityPrice).build());

            //water
            int waterPrice = Integer.parseInt(req.getParameter("hostel-water"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(waterPrice).build());

            //wifi
            int internetPrice = Integer.parseInt(req.getParameter("hostel-wifi"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(internetPrice).build());

            //Management
            int managementPrice = Integer.parseInt(req.getParameter("hostel-manage"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(managementPrice).build());

            //Vehicle
            int vehiclePrice = Integer.parseInt(req.getParameter("hostel-vehicle"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(vehiclePrice).build());

            //cleaning
            int cleaningPrice = Integer.parseInt(req.getParameter("hostel-cleaning"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(cleaningPrice).build());

            Hostel hostel = Hostel.builder()
                    .hostelOwnerAccountID(accountId)
                    .hostelName(hostelName)
                    .address(hostelAddress)
                    .ward(hostelWard)
                    .district(hostelDistrict)
                    .city(hostelProvince).build();
            int hostelId = dao.addHostel(hostel, hostelServiceList);

            if (hostelId >= 0) {
                url = SUCCESS;
                req.setAttribute("HOSTEL_ID", hostelId);
                req.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Tạo khu trọ thành công!").build());
            } else {
                req.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Tạo khu trọ thất bại!").build());
            }
        } catch (Exception e) {
            log("Error at AddHostel: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
