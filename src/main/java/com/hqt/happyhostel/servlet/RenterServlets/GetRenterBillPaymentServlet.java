package com.hqt.happyhostel.servlet.RenterServlets;


import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetRenterPaymentBillServlet", value = "/GetRenterPaymentBillServlet")
public class GetRenterBillPaymentServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-payment";
    private static final String ERROR = "Renter-payment";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");
            int accID = account.getAccId();

            BillDAO billDAO = new BillDAO();
            int billID = ( req.getAttribute("billID") != null) ? (int) req.getAttribute("billID") : Integer.parseInt(req.getParameter("billID")) ;
            BillDetail billDetail = new BillDAO().getBillDetail(billID);

            Bill bill = billDAO.getRenterBillByID(billID);
            req.setAttribute("RESPONSE_MSG", req.getAttribute("RESPONSE_MSG"));
            if (bill != null){
                req.setAttribute("BILL", bill);
                //get number electric and water
                int consumeIDStart = billDetail.getConsumeIDStart();
                int consumeIDEnd = billDetail.getConsumeIDEnd();
                Consume consumeStart = new ConsumeDAO().getConsumeByID(consumeIDStart);
                Consume consumeEnd = new ConsumeDAO().getConsumeByID(consumeIDEnd);
                req.setAttribute("CONSUME_START", consumeStart);
                req.setAttribute("CONSUME_END", consumeEnd);
                HandlerStatus handlerStatus = (HandlerStatus) req.getAttribute("RESPONSE_MSG");
                req.setAttribute("RESPONSE_MSG", handlerStatus);
                url = SUCCESS;
            }
            //Get service
            HostelDAO hostelDAO = new HostelDAO();
            Hostel hostel = hostelDAO.getHostelByRenterId(accID);
            int hostelID = hostel.getHostelID();
            int billDetailID = billDetail.getBillDetailID();
            ServiceInfoDAO serviceInfoDao = new ServiceInfoDAO();
            List<ServiceInfo> serviceInfoList = serviceInfoDao.getServiceOfBill(billDetailID, hostelID);
            if (serviceInfoList.size() > 0) {
                req.setAttribute("LIST_SERVICES", serviceInfoList);
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
