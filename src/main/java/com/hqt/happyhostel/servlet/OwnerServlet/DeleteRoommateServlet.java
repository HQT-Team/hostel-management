package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.RoommateInfoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteRoommateServlet", value = "/DeleteRoommateServlet")
public class DeleteRoommateServlet extends HttpServlet {
    private static final String SUCCESS = "get-roommate-infor";
    private static final String ERROR = "get-roommate-infor";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            int roommateID = Integer.parseInt(request.getParameter("roommateID"));
            RoommateInfoDAO roommateInfoDAO = new RoommateInfoDAO();
            boolean check = roommateInfoDAO.DeleteRoommateInfo(roommateID);
            if (check){
                url = SUCCESS;
            }

        }catch (Exception e){
            log("Error at DeleteRoommateServlet: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
