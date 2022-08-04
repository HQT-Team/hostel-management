package com.hqt.happyhostel.servlets.RenterServlets;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RoommateDetailServlet", value = "/RoommateDetailServlet")
public class RoommateDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("uri", "/HappyHostel/GetRoomateAccountServlet");
        int roommateId = Integer.parseInt(req.getParameter("roommateID"));
        ArrayList<RoommateInfo> listroommateinfor = (ArrayList<RoommateInfo>) session.getAttribute("listroommateinfor");
        RoommateInfo roommate1 = null;
        for (int i = 0; i < listroommateinfor.size(); i++) {
            if (listroommateinfor.get(i).getRoommateID() == Integer.parseInt(req.getParameter("roommateID"))) {
                roommate1 = listroommateinfor.get(i);
                session.setAttribute("roommate1", roommate1);
            }
        }
        req.getRequestDispatcher("Renter-update-roommate").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int roommateId = Integer.parseInt(req.getParameter("roommateID"));
        ArrayList<RoommateInfo> listroommateinfor = (ArrayList<RoommateInfo>) session.getAttribute("listroommateinfor");
        RoommateInfo roommate1 = null;
        for (int i = 0; i < listroommateinfor.size(); i++) {
            if (listroommateinfor.get(i).getRoommateID() == Integer.parseInt(req.getParameter("roommateID"))) {
                roommate1 = listroommateinfor.get(i);
                session.setAttribute("roommate1", roommate1);
            }
        }
        req.getRequestDispatcher("Renter-update-roommate").forward(req, resp);
    }
}
