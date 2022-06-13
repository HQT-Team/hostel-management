package com.hqt.happyhostel.servlet.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dao.InformationDAO;
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
    private final String success = "createInvite";
    private final String fail = "create-room-account";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String url = null;
        try {
            String roomId = req.getParameter("room_id");
            String username = req.getParameter("username");
            String price = req.getParameter("price");
            String deposit = req.getParameter("deposit");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            if (!AccountDAO.isExistUsername(username)) {
                String password = SecurityUtils.hashMd5(RandomStringGenerator.randomPassword(12, username));
                Account renterAccount = Account.builder()
                        .username(username)
                        .password(password)
                        .status(0)
                        .role(2)
                        .build();

                int renterId = AccountDAO.createRenterAccount(renterAccount);
                if (renterId > 0) {

                    HttpSession session = req.getSession(false);
                    if (session != null) {
                        Account owner = (Account) session.getAttribute("USER");

                        Contract contract = Contract.builder()
                                .room_id(Integer.parseInt(roomId))
                                .price(Integer.parseInt(price))
                                .startDate(startDate)
                                .expiration(endDate)
                                .deposit(Integer.parseInt(deposit))
                                .renterId(renterId)
                                .hostelOwnerId(owner.getAccId())
                                .build();
                        url = success;
//                    req.setAttribute("SUCCESS", "Đăng ký tài khoản thành công! Tài khoản sẽ được quản trị viên xem xét và thông báo kết quả qua email!");
                        if (ContractDAO.addContract(contract)) url = success;
                        else url = fail;
                    }

                } else {
                    url = fail;
                    req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                }
            } else {
                // username has been existed
                url = fail;
                req.setAttribute("ERROR_TYPE", "username");
                req.setAttribute("ERROR", "Tài khoản đã tồn tại trong hệ thống!");
                req.setAttribute("username", username);
                req.setAttribute("price", price);
                req.setAttribute("deposit", deposit);
                req.setAttribute("startDate", startDate);
                req.setAttribute("endDate", endDate);
            }
        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
