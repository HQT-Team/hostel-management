package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Hostels;
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
    public static final String SUCCESS = "ShowListHostel";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validDate = dateObj.format(formatter);
        List<Services> servicesList = new ArrayList<>();
        Account acc = new Account();

        try {
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            acc = (Account)session.getAttribute("USER");

            int accountId = acc.getAccId();
            String hostelName = req.getParameter("hostel-name");
            String hostelAddress = req.getParameter("hostel-address");
            String hostelProvince = req.getParameter("hostel-province");
            String hostelDistrict = req.getParameter("hostel-district");
            String hostelWard = req.getParameter("hostel-ward");

            double electricityPrice = Double.parseDouble(req.getParameter("hostel-electric"));
            servicesList.add(new Services("Electricity", electricityPrice, validDate));
            double waterPrice = Double.parseDouble(req.getParameter("hostel-water"));
            servicesList.add(new Services("Water", waterPrice, validDate));
            double internetPrice = Double.parseDouble(req.getParameter("hostel-wifi"));
            servicesList.add(new Services("Internet", internetPrice, validDate));
            double managementPrice = Double.parseDouble(req.getParameter("hostel-manage"));
            servicesList.add(new Services("Management", managementPrice, validDate));
            double vehiclePrice = Double.parseDouble(req.getParameter("hostel-vehicle"));
            servicesList.add(new Services("Vehicle", vehiclePrice, validDate));
            double cleaningPrice = Double.parseDouble(req.getParameter("hostel-cleaning"));
            servicesList.add(new Services("Cleaning", cleaningPrice, validDate));

            Hostels hostel = new Hostels(accountId,hostelName,hostelAddress,hostelWard,hostelDistrict,hostelProvince);
            HostelDAO dao = new HostelDAO();
            boolean checkInsert = dao.addHostel(hostel, servicesList);

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
