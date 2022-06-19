package com.hqt.happyhostel.servlet.RenterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Contract;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.utils.RandomStringGenerator;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateRenterAccountServlet", value = "/CreateRenterAccountServlet")
public class CreateRenterAccountServlet extends HttpServlet {
    private final String SUCCESS = "create-contract";
    private final String FAIL = "create-room-account";
    private final String ERROR = "error-page";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RoomInviteDAO roomInviteDAO = new RoomInviteDAO();
        AccountDAO accountDAO = new AccountDAO();
        ContractDAO contractDAO = new ContractDAO();
        String url = null;
        try {
            String roomId = req.getParameter("room_id");
            if (roomInviteDAO.getRoomInviteById(Integer.parseInt(roomId)).getInviteCode() == null){
                String username = req.getParameter("room-username");
                String price = req.getParameter("room-fee");
                String deposit = req.getParameter("room-deposit");
                String startDate = req.getParameter("room-startdate");
                String endDate = req.getParameter("room-enddate");
                if (!accountDAO.isExistUsername(username)) {
                    String password = SecurityUtils.hashMd5(RandomStringGenerator.randomPassword(12, username));
                    Account renterAccount = Account.builder()
                            .username(username)
                            .password(password)
                            .status(1)
                            .role(2)
                            .roomId(Integer.parseInt(roomId))
                            .build();

                    int renterId = accountDAO.createRenterAccount(renterAccount);
                    if (renterId > 0) {

                        HttpSession session = req.getSession(false);
                        if (session != null) {
                            Account owner = (Account) session.getAttribute("USER");
                            if (owner != null){
                                req.setAttribute("contract_room_id", roomId);
                                req.setAttribute("contract_room_price", price);
                                req.setAttribute("contract_startDate", startDate);
                                req.setAttribute("contract_endDate", endDate);
                                req.setAttribute("contract_deposit", deposit);
                                req.setAttribute("contract_renterId", renterId);
                                req.setAttribute("contract_hostelOwnerId", owner.getAccId());
                                url = SUCCESS;
                            }else url = ERROR;
                        }
                    } else {
                        url = FAIL;
                        req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                    }
                } else {
                    // username has been existed
                    url = FAIL;
                    req.setAttribute("ERROR_TYPE", "username");
                    req.setAttribute("ERROR", "Tài khoản đã tồn tại trong hệ thống!");
                    req.setAttribute("username", username);
                    req.setAttribute("price", price);
                    req.setAttribute("deposit", deposit);
                    req.setAttribute("startDate", startDate);
                    req.setAttribute("endDate", endDate);
                }
            }else url = SUCCESS;


        } catch (Exception e) {
            log("Error at CreateRenterAccountServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
