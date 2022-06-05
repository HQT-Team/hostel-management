package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Services;

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
    public static final String ERROR = "error.jsp";
    public static final String SUCCESS = "list-hostels";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validDate = dateObj.format(formatter);
        List<Services> servicesList = new ArrayList<>();
        List<HostelService> hostelServiceList = new ArrayList<>();
        Account acc = new Account();


        try {
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            acc = (Account) session.getAttribute("USER");
            HostelDAO dao = new HostelDAO();
            servicesList = dao.getListServices();

            int accountId = acc.getAccId();
            String hostelName = req.getParameter("hostel-name");
            String hostelAddress = req.getParameter("hostel-address");
            String hostelProvince = req.getParameter("hostel-province");
            String hostelDistrict = req.getParameter("hostel-district");
            String hostelWard = req.getParameter("hostel-ward");

            if (servicesList.isEmpty()) {
                servicesList.add(new Services("Electric"));
                servicesList.add(new Services("Water"));
                servicesList.add(new Services("Internet"));
                servicesList.add(new Services("Management"));
                servicesList.add(new Services("Vehicle"));
                servicesList.add(new Services("Cleaning"));
            }


            //electric
            double electricityPrice = Double.parseDouble(req.getParameter("hostel-electric"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(electricityPrice).build());
            //water
            double waterPrice = Double.parseDouble(req.getParameter("hostel-water"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(waterPrice).build());

            //wifi
            double internetPrice = Double.parseDouble(req.getParameter("hostel-wifi"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(internetPrice).build());

            //Management
            double managementPrice = Double.parseDouble(req.getParameter("hostel-manage"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(managementPrice).build());

            //Vehicle
            double vehiclePrice = Double.parseDouble(req.getParameter("hostel-vehicle"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(vehiclePrice).build());

            //cleaning
            double cleaningPrice = Double.parseDouble(req.getParameter("hostel-cleaning"));
            hostelServiceList.add(HostelService.builder().validDate(validDate).servicePrice(cleaningPrice).build());


            Hostel hostel = new Hostel(accountId, hostelName, hostelAddress, hostelWard, hostelDistrict, hostelProvince);
            boolean checkInsert = dao.addHostel(hostel, servicesList, hostelServiceList);

            if (checkInsert) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AddHostel: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
