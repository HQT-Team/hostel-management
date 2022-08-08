package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ManageServiceServlet", value = "/ManageServiceServlet")
public class ManageServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("CURRENT_PAGE", "service");
            Map<String, Services> services = new ServicesDAO().getAll();
            List<Services> servicesList = new ArrayList<>();
            for (Map.Entry<String, Services> entry : services.entrySet()) {
                servicesList.add(entry.getValue());
            }
            request.setAttribute("servicesList", servicesList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-service-page").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
