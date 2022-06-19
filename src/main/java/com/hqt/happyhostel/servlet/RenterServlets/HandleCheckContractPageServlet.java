package com.hqt.happyhostel.servlet.RenterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HandleCheckContractPageServlet", value = "/HandleCheckContractPageServlet")
public class HandleCheckContractPageServlet extends HttpServlet {
    private final String SUCCESS = "renter-register-page";
    private final String FAIL = "renter-register-contract-page";
    private final String BACK = "invite-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String accepted = request.getParameter("accepted");
        String accId = request.getParameter("renter_id");
        HandlerStatus handlerStatus = null;
        String url = ERROR;
        try {
            if("continue".equalsIgnoreCase(action) && accId != null){
                if (accepted != null) {
                    url = SUCCESS;
                }else{
                    url = FAIL;
                    handlerStatus = HandlerStatus.builder().status(false).content("Vui lòng nhấn đồng ý với hợp đồng để tiếp tục").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            }else if(!"continue".equalsIgnoreCase(action) && accId != null) url = BACK;
        }catch (Exception e){
            log("Error at HandleCheckContractPageServlet: " + e.toString());
        }finally {
            if(ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
