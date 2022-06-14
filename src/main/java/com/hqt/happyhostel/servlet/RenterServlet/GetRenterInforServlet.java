package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "GetRenterInforServlet", value = "/GetRenterInforServlet")
public class GetRenterInforServlet extends HttpServlet {
    public static final String ERROR = "Renter-profile";
    public static final String SUCCESS = "Renter-profile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        Account acc = new Account();
        List<Infrastructures> infrastructures = new ArrayList<>();
        List<ServiceInfo> serviceInfo = new ArrayList<>();
        Information accInfo = new Information();
        try {
            HttpSession session = req.getSession();
            acc = (Account)session.getAttribute("USER");
            int renterId = acc.getAccId();
            HostelDAO hostelDAO = new HostelDAO();

            //Get Account Infor
            accInfo = new InformationDAO().getAccountInformationById(renterId);
            if (accInfo!=null){
                req.setAttribute("ACC_INFO", accInfo);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PAGE", "hostel-renter-page");
        }catch (Exception e){
            log("Error at GetHostelInforServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
