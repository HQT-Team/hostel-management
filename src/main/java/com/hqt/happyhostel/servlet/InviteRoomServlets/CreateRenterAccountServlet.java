package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;
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
        Account owner = null;
        String url = null;
        int ownerId = -1;
        int roomID = -1;
        url = ERROR;
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String roomId = req.getParameter("room_id");
                owner = (Account) session.getAttribute("USER");

                //check request parameter
                if (owner != null && roomId != null) {
                    roomID = Integer.parseInt(roomId);
                    ownerId = owner.getAccId();

                    //check xem roomID có thuộc ownerID không
                    if (new HostelOwnerDAO().checkOwnerRoom(ownerId, roomID)) {
                        Room room = new RoomDAO().getRoomById(roomID);
                        if (room.getRoomStatus() == 1) {
                            String username = req.getParameter("room-username");
                            String email = req.getParameter("room-email");
                            String price = req.getParameter("room-fee");
                            String deposit = req.getParameter("room-deposit");
                            String startDate = req.getParameter("room-startdate");
                            String endDate = req.getParameter("room-enddate");
                            int roomElectric = Integer.parseInt(req.getParameter("room-electric"));
                            int roomWater = Integer.parseInt(req.getParameter("room-water"));
                            if (!accountDAO.isExistUsername(username)) {
                                if (!new InformationDAO().isExistEmail(email)) {
                                    String password = SecurityUtils.hashMd5(RandomStringGenerator.randomPassword(12, username));
                                    Information information = Information.builder()
                                            .email(email)
                                            .build();

                                    AccountInfo accountInfo = new AccountInfo(information);

                                    Account renterAccount = Account.builder()
                                            .accId(0)
                                            .username(username)
                                            .password(password)
                                            .status(0)
                                            .role(2)
                                            .roomId(Integer.parseInt(roomId))
                                            .accountInfo(accountInfo).build();

                                    int renterId = accountDAO.createRenterAccount(renterAccount);
                                    if (renterId > 0) {
                                        boolean updateConsumeResult = new ConsumeDAO().updateConsumeNumber(Consume.builder()
                                                .numberElectric(roomElectric)
                                                .numberWater(roomWater)
                                                .status(0)
                                                .roomID(Integer.parseInt(roomId)).build());
                                        if (updateConsumeResult) {
                                            req.setAttribute("contract_room_id", roomId);
                                            req.setAttribute("contract_room_price", price);
                                            req.setAttribute("contract_startDate", startDate);
                                            req.setAttribute("contract_endDate", endDate);
                                            req.setAttribute("contract_deposit", deposit);
                                            req.setAttribute("contract_renterId", renterId);
                                            req.setAttribute("contract_hostelOwnerId", owner.getAccId());
                                            url = SUCCESS;
                                        } else {
                                            url = FAIL;
                                            req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                                        }
                                    } else {
                                        url = FAIL;
                                        req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                                    }
                                } else {
                                    // username has been existed
                                    url = FAIL;
                                    req.setAttribute("ERROR_TYPE", "email");
                                    req.setAttribute("ERROR", "Email đã tồn tại trong hệ thống!");
                                    req.setAttribute("username", username);
                                    req.setAttribute("price", price);
                                    req.setAttribute("deposit", deposit);
                                    req.setAttribute("startDate", startDate);
                                    req.setAttribute("endDate", endDate);
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
                        } else url = SUCCESS;
                    }
                }


            }
        } catch (Exception e) {
            log("Error at CreateRenterAccountServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
