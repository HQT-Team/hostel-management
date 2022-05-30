package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "loginPage";
        String username = request.getParameter("txtemail");
        String password = request.getParameter("txtpassword");
        try {
            HostelOwnerAccount owner = HostelOwnerDAO.getAccountByUsernameAndPassword(username, password);
            if (owner != null) {
                url = "success";
                request.setAttribute("owner", owner);

                Hostels hostels = RoomDAO.getHostelInformationByHostelID(1);
                request.setAttribute("hostel", hostels);

                Contract contract = RoomDAO.getContract(55);
                request.setAttribute("contract", contract);

                Invoices invoice = RoomDAO.getNearestInvoice(55);
                request.setAttribute("invoice", invoice);

                Payment payment = RoomDAO.getNearestPayments(55);
                request.setAttribute("payment", payment);

                Consume consume = RoomDAO.getNearestConsume(55);
                request.setAttribute("consume", consume);

                ArrayList<RoommateInformation> roommateInformationArrayList = RoomDAO.getRoommateInformation(55);
                request.setAttribute("roommateList", roommateInformationArrayList);

                ArrayList<Infrastructures> infrastructures = RoomDAO.getInfrastructures(55);
                request.setAttribute("infrastructures", infrastructures);

                Room room = RoomDAO.getRoomInformation(55);
                request.setAttribute("roomInformation", room);

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
