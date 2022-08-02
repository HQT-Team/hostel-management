package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.InfrastructureDAO;
import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.InfrastructureItem;
import com.hqt.happyhostel.dto.Infrastructures;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ManageInfrastructureServlet", value = "/ManageInfrastructureServlet")
public class ManageInfrastructureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("CURRENT_PAGE", "infrastructure");
            List<InfrastructureItem> infrastructuresList = new InfrastructureDAO().getAllInfrastructure();
            request.setAttribute("infrastructuresList", infrastructuresList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-infrastructure-page").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
