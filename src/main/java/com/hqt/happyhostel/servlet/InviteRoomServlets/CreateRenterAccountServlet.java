package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;
import com.hqt.happyhostel.utils.RandomStringGenerator;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CreateRenterAccountServlet", value = "/CreateRenterAccountServlet")
public class CreateRenterAccountServlet extends HttpServlet {
    private final String SUCCESS = "create-contract";
    private final String FAIL = "create-room-account-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AccountDAO accountDAO = new AccountDAO();
        HandlerStatus handlerStatus = null;
        Account owner;
        String url;
        int ownerId;
        int roomID;
        url = ERROR;
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                roomID = (int) session.getAttribute("current_room_id");
                owner = (Account) session.getAttribute("USER");

                //check request parameter
                if (owner != null && roomID > 0) {
                    ownerId = owner.getAccId();

                    //check xem roomID có thuộc ownerID không
                    if (new HostelOwnerDAO().checkOwnerRoom(ownerId, roomID)) {
                        url = FAIL;
                        Room room = new RoomDAO().getRoomById(roomID);

                        //Check room status
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
                                            .roomId(roomID)
                                            .accountInfo(accountInfo).build();

                                    int renterId = accountDAO.createRenterAccount(renterAccount);
                                    if (renterId > 0) {
                                        boolean updateConsumeResult = new ConsumeDAO().updateConsumeNumber(Consume.builder()
                                                .numberElectric(roomElectric)
                                                .numberWater(roomWater)
                                                .status(0)
                                                .roomID(roomID).build());
                                        if (updateConsumeResult) {
                                            handlerStatus = HandlerStatus.builder().status(true).content("Tạo tài khoản thành công").build();
                                            req.setAttribute("contract_room_id", roomID);
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
                                        handlerStatus = HandlerStatus.builder().status(false).content("Đã có lỗi xảy ra, vui lòng thử lại sau!").build();
                                    }
                                } else {
                                    // username has been existed
                                    handlerStatus = HandlerStatus.builder().status(false).content("Email đã tồn tại trong hệ thống!").build();
                                    req.setAttribute("username", username);
                                    req.setAttribute("price", price);
                                    req.setAttribute("deposit", deposit);
                                    req.setAttribute("startDate", startDate);
                                    req.setAttribute("endDate", endDate);
                                }

                            } else {
                                // username has been existed
                                handlerStatus = HandlerStatus.builder().status(false).content("Tài khoản đã tồn tại trong hệ thống!").build();
                                req.setAttribute("username", username);
                                req.setAttribute("price", price);
                                req.setAttribute("deposit", deposit);
                                req.setAttribute("startDate", startDate);
                                req.setAttribute("endDate", endDate);
                            }
                        } else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Tài khoản cho phòng này đã tồn tại trong hệ thống!").build();
                            url = FAIL;
                        }
                    }
                }
                req.setAttribute("RESPONSE_MSG", handlerStatus);
            }
        } catch (Exception e) {
            log("Error at CreateRenterAccountServlet: " + e);
        } finally {
            if (ERROR.equalsIgnoreCase(url)) resp.sendRedirect(url);
            else req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
