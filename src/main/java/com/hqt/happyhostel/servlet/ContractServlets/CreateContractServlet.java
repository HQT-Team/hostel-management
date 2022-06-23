package com.hqt.happyhostel.servlet.ContractServlets;

import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dto.Contract;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateContractServlet", value = "/CreateContractServlet")
public class CreateContractServlet extends HttpServlet {

    private final String SUCCESS = "createInvite";
    private final String FAIL = "create-room-account-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        try {
            String roomId =(String) req.getAttribute("contract_room_id");;
            String price =(String) req.getAttribute("contract_room_price");
            String deposit =(String) req.getAttribute("contract_deposit");
            String startDate =(String) req.getAttribute("contract_startDate");
            String endDate =(String) req.getAttribute("contract_endDate");
            int renterId =(int) req.getAttribute("contract_renterId");
            int ownerId =(int) req.getAttribute("contract_hostelOwnerId");



                Contract contract = Contract.builder()
                        .room_id(Integer.parseInt(roomId))
                        .price(Integer.parseInt(price))
                        .startDate(startDate)
                        .expiration(endDate)
                        .deposit(Integer.parseInt(deposit))
                        .renterId(renterId)
                        .hostelOwnerId(ownerId)
                        .status(1)
                        .build();
                req.setAttribute("room_id", roomId);
                ContractDAO contractDAO = new ContractDAO();
                if(contractDAO.addContract(contract)) url = SUCCESS;
                else url = FAIL;

        }catch (Exception e){
           e.printStackTrace();
        }finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
