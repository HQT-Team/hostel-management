package com.hqt.happyhostel.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateRoomServlet", value = "/UpdateRoomServlet")
public class UpdateRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "loginPage";
        try {
            url = "success";

            int roomNumber = Integer.parseInt(request.getParameter("txtRoomNumber"));
            double roomArea = Double.parseDouble(request.getParameter("txtRoomArea"));
            int hasAttic = Integer.parseInt(request.getParameter("hasAttic"));
            int restRoomQuantity = Integer.parseInt(request.getParameter("restRoomQuantity"));
            int restRoomStatus = Integer.parseInt(request.getParameter("restRoomStatus"));

            int windowsQuantity = Integer.parseInt(request.getParameter("windowsQuantity"));
            int windowsStatus = Integer.parseInt(request.getParameter("windowsStatus"));

            int airConditionsQuantity = Integer.parseInt(request.getParameter("airConditionsQuantity"));
            int airConditionStatus = Integer.parseInt(request.getParameter("airConditionStatus"));



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
