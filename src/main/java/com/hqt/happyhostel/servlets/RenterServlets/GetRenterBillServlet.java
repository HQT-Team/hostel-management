package com.hqt.happyhostel.servlets.RenterServlets;


import com.hqt.happyhostel.dao.BillDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Bill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetRenterBillServlet", value = "/GetRenterBillServlet")
public class GetRenterBillServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-invoice-page";
    private static final String ERROR = "Renter-invoice-page";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");
            int accID = account.getAccId();

            BillDAO billDAO = new BillDAO();
            List<Bill> billList = billDAO.getBllListByRenterID(accID);
            req.setAttribute("uri", req.getRequestURI());
            if (billList.size() > 0){
                req.setAttribute("BILL_LIST", billList);
                url = SUCCESS;
            }

        }catch (Exception e){
            log("Error at GetRenterBillServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
