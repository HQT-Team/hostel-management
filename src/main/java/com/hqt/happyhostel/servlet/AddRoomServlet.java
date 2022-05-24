package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.HostelOwnerAccount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddRoomServlet", value = "/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "loginPage";
        int quantityRoom = Integer.getInteger(request.getParameter("txtQuantityRoom"));
        String roomNumber = request.getParameter("txtRoomName");
        int capacity = Integer.getInteger(request.getParameter("txtCapacity"));
        String attic = request.getParameter("txtAttic");
        String roomArea = request.getParameter("txtRoomArea");
        String restrooms = request.getParameter("txtNumberRestrooms");
        String windows = request.getParameter("txtNumberWindows");
        String airConditions = request.getParameter("txtNumberAirConditions");
        try {
            Boolean isSuccess = RoomDAO.addNewRoom(1, roomNumber, 4, 25.00);
            if (isSuccess) {
                url = "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
