package com.hqt.happyhostel.servlet.ContractServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getContractServlet", value = "/getContractServlet")
public class getContractServlet extends HttpServlet {
    private final String SUCCESS_REGISTER = "renter-register-contract-page";
    private final String FAIL_REGISTER = "renter-register-contract-page";
    private final String SUCCESS_VIEW = "contract-page";
    private final String FAIL_VIEW = "contract-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        int renterID = -1;
        try {

            String renterId = ( request.getAttribute("ACC_ID") != null ) ?
                    (String) request.getAttribute("ACC_ID") : request.getParameter("acc_id");

            String isRegister = request.getParameter("isRegister");

            if(renterId != null){
                renterID = Integer.parseInt(renterId);
                //get renter master
                AccountInfo renterMasterInfo = new AccountDAO().getAccountInformationById(renterID);
                //get renter account
                Account renterAccount = new AccountDAO().getAccountById(renterID);
                //get contract
                Contract contract = new ContractDAO().getContractByRenterId(renterID);
                if (contract != null){
                    //get room information
                    Room roomInfo = new RoomDAO().getRoomByRenterId(contract.getRoom_id());
                    //get room infrastructure
                    ArrayList<Infrastructures> roomInfrastructureList = new InfrastructureDAO().getRoomInfrastructures(contract.getRoom_id());
                    //get hostel information
                    Hostel hostelInfo = new HostelDAO().getHostelById(roomInfo.getHostelId());
                    //get hostel owner information
                    AccountInfo hostelOwnerInfo = new AccountDAO().getAccountInformationById(contract.getHostelOwnerId());

                    HttpSession session = request.getSession(true);
                    if (session != null){
                        session.setAttribute("CONTRACT", contract);
                        session.setAttribute("CONTRACT_ROOM", roomInfo);
                        session.setAttribute("CONTRACT_ROOM_INFRASTRUCTURE_LIST", roomInfrastructureList);
                        session.setAttribute("CONTRACT_HOSTEL", hostelInfo);
                        session.setAttribute("CONTRACT_OWNER", hostelOwnerInfo);
                        session.setAttribute("CONTRACT_RENTER", renterMasterInfo);
                        session.setAttribute("RENTER_ACCOUNT", renterAccount);
                    }


                    if(isRegister != null) url = SUCCESS_REGISTER;
                    else url = SUCCESS_VIEW;
                }else {
                    HandlerStatus handlerStatus = HandlerStatus.builder().status(false).content("Không thể tìm thấy hợp đồng này").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                    if(isRegister != null) url = FAIL_REGISTER;
                    else url = FAIL_VIEW;
                }
            }
        }catch (Exception e){
            log("Error at InviteCodeServlet: " + e.toString());
        }finally {
            if(ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
