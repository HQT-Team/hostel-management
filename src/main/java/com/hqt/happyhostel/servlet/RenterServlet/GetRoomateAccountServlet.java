package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.RoommateInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetRoomateAccountServlet", value = "/GetRoomateAccountServlet")
public class GetRoomateAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("USER");
        ArrayList<RoommateInfo> list = new ArrayList<>();
        list = AccountDAO.getRoommateInformationById(account.getAccId());
        session.setAttribute("listroommateinfor", list);
        request.getRequestDispatcher("Renter-roommate").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

