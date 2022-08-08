package com.hqt.happyhostel.servlets.RenterServlets;

import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.RoommateInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetRoomateAccountServlet", value = "/GetRoomateAccountServlet")
public class GetRoomateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            Account account = (Account)session.getAttribute("USER");

            List<RoommateInfo> list = new RoommateInfoDAO().getListRoommatesOfAnAccount(account.getAccId());
            session.setAttribute("listroommateinfor", list);
            request.setAttribute("uri", request.getRequestURI());
            request.getRequestDispatcher("Renter-roommate").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

