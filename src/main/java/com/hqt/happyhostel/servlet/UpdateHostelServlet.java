package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Hostels;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateHostelServlet", value = "/UpdateHostelServlet")
public class UpdateHostelServlet extends HttpServlet {
    public static final String ERROR = "list-hostels";
    public static final String SUCCESS = "ShowListHostelsServlet";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int hostelID = Integer.parseInt(req.getParameter("hostelID"));
            Hostels hostel = new HostelDAO().getHostelById(hostelID);
            req.setAttribute("HOSTEL", hostel);
            req.getRequestDispatcher("update-hostel-page").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Hello Get");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        req.setCharacterEncoding("UTF-8");
        try {
            System.out.println("Day ne");
            int hostelID = Integer.parseInt(req.getParameter("hostelID"));
            String hostelName = req.getParameter("hostel-name");
            String hostelAddress = req.getParameter("hostel-address");
            String hostelWard = req.getParameter("hostel-ward");
            String hostelDistrict = req.getParameter("hostel-district");
            String hostelProvince = req.getParameter("hostel-province");

            HostelDAO dao = new HostelDAO();
            Hostels newHostel = new Hostels(hostelName, hostelAddress, hostelWard, hostelDistrict, hostelProvince);
            boolean checkUpdate = dao.updateHostel(newHostel, hostelID);
            if (checkUpdate) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateHostel: " + e.toString());
        } finally {
            System.out.println("Hello Post");
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
