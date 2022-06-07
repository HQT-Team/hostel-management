package com.hqt.happyhostel.servlet.AccountServlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateRenterAccountServlet", value = "/CreateRenterAccountServlet")
public class CreateRenterAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String money = request.getParameter("money");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        try {

        }catch (Exception e){
            log("Error at LoginServlet: " + e.toString());
        }
    }
}
