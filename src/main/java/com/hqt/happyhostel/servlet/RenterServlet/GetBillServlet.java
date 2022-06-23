package com.hqt.happyhostel.servlet.RenterServlet;


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

@WebServlet(name = "GetBillServlet", value = "/GetBillServlet")
public class GetBillServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-invoice-page";
    private static final String ERROR = "Renter-invoice-page";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String url = ERROR;
          Account account = new Account();
          Bill bill = new Bill();
          try {
              HttpSession session = req.getSession();
              account = (Account) session.getAttribute("USER");
              int accID = account.getAccId();
              BillDAO billDAO = new BillDAO();
              bill = billDAO.getBillByAccountID(accID);
              if (bill!=null){
                  req.setAttribute("BILL", bill);
                  url = SUCCESS;
              }
          }catch (Exception e){
              log("Error at GetBillServlet: " + e.toString());
          }finally {
              req.getRequestDispatcher(url).forward(req,resp);
          }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
