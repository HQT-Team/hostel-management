package com.hqt.happyhostel.servlet.RenterRegisterServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getContractServlet", value = "/getContractServlet")
public class getContractServlet extends HttpServlet {
    private final String SUCCESS = "confirm-room-info-page";
    private final String FAIL = "confirm-room-info-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        int renterID = -1;
        try {
            renterID = (int) request.getAttribute("ACCOUNT_ID");

            if (renterID > 0) {
                //get renter master
                AccountInfo renterMasterInfo = new AccountDAO().getAccountInformationById(renterID);
                //get renter account
                String renterAccountUsername = new AccountDAO().getAccountById(renterID).getUsername();
                //get contract
                Contract contract = new ContractDAO().getContractByRenterId(renterID);
                if (contract != null) {
                    //get room information
                    Room roomInfo = new RoomDAO().getRoomById(contract.getRoom_id());
                    //get room infrastructure
                    ArrayList<Infrastructures> roomInfrastructureList = new InfrastructureDAO().getRoomInfrastructures(contract.getRoom_id());
                    //get hostel information
                    Hostel hostelInfo = new HostelDAO().getHostelById(roomInfo.getHostelId());

                    //get room services
                    List<ServiceInfo> serviceInfoList = new RoomDAO().getServicesOfHostel(hostelInfo.getHostelID());

                    //get hostel owner information
                    AccountInfo hostelOwnerInfo = new AccountDAO().getAccountInformationById(contract.getHostelOwnerId());
                    HttpSession session = request.getSession(true);
                    if (session != null) {
                        session.setAttribute("CONTRACT", contract);
                        session.setAttribute("CONTRACT_ROOM", roomInfo);
                        session.setAttribute("CONTRACT_ROOM_INFRASTRUCTURE_LIST", roomInfrastructureList);
                        session.setAttribute("CONTRACT_HOSTEL", hostelInfo);
                        session.setAttribute("CONTRACT_OWNER", hostelOwnerInfo);
                        session.setAttribute("CONTRACT_RENTER", renterMasterInfo);
                        session.setAttribute("RENTER_ACCOUNT_USERNAME", renterAccountUsername);
                        session.setAttribute("CONTRACT_SERVICES_LIST", serviceInfoList);
                    }
                    url = SUCCESS;
                } else {
                    HandlerStatus handlerStatus = HandlerStatus.builder().status(false).content("Không thể tìm thấy hợp đồng này").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                    url = FAIL;

                }
            }
        } catch (Exception e) {
//
            e.printStackTrace();
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
