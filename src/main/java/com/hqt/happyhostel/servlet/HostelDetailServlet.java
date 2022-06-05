package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.Provider;
import java.util.List;

@WebServlet(name = "HostelDetailServlet", value = "/HostelDetailServlet")
public class HostelDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int hostelId = Integer.parseInt(request.getParameter("hostelID"));
            Hostel hostel = new HostelDAO().getHostelById(hostelId);
            //get list room
            //get hostel services
//            List<HostelService> hostelServices
//            Services
        }catch (Exception e){
            log("Error at HostelDetailServlet: " + e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
