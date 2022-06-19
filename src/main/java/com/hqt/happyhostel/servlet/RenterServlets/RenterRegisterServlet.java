package com.hqt.happyhostel.servlet.RenterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.*;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RenterRegisterServlet", value = "/RenterRegisterServlet")
public class RenterRegisterServlet extends HttpServlet {
    private final String SUCCESS = "renter-register-page";
    private final String FAIL = "renter-register-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        AccountDAO accountDAO = new AccountDAO();
        Account renterAcc = new Account();
        HandlerStatus handlerStatus = null;
        try {
            String username = req.getParameter("username");
            String fullname = req.getParameter("fullname");
            String password = SecurityUtils.hashMd5(req.getParameter("password"));
            String passwordConfirm = SecurityUtils.hashMd5(req.getParameter("password_confirm"));
            if ((username != null && !username.isBlank()) && (fullname != null && !fullname.isBlank()) && (password != null && !password.isBlank()) && (passwordConfirm != null && !passwordConfirm.isBlank())) {
                url = FAIL;
                if (accountDAO.isExistUsername(username)) {
                    // username has been existed
                    handlerStatus = HandlerStatus.builder().status(false).content("Tài khoản đã tồn tại trong hệ thống!").build();
//                    req.setAttribute("ERROR_TYPE", "username");
//                    req.setAttribute("ERROR", "Tài khoản đã tồn tại trong hệ thống!");
                    req.setAttribute("fullname", fullname);
                    req.setAttribute("username", username);
                    req.setAttribute("RESPONSE_MSG", handlerStatus);
                    req.getRequestDispatcher(url).forward(req, resp);

                } else {
                    if (!password.equals(passwordConfirm)) {
                        // password and password confirm is not equal
                        handlerStatus = HandlerStatus.builder().status(false).content("Mật khẩu không hợp lệ vui lòng kiểm tra lại").build();
//                    req.setAttribute("ERROR_TYPE", "email");
//                    req.setAttribute("ERROR", "Email đã tồn tại trong hệ thống!");
                        req.setAttribute("fullname", fullname);
                        req.setAttribute("username", username);
                        req.setAttribute("RESPONSE_MSG", handlerStatus);
                        req.getRequestDispatcher(url).forward(req, resp);
                    } else {
                        int accId = accountDAO.getAccountIdByUserName(username);
                        boolean check1 = accountDAO.updateAccountFullName(accId, fullname);
                        boolean check2 = accountDAO.updateAccountPass(accId, password);

                        if (check1 && check2) {
                            req.setAttribute("SUCCESS", "Đăng ký tài khoản thành công! Tài khoản sẽ được quản trị viên xem xét và thông báo kết quả qua email!");
                            req.getRequestDispatcher(url).forward(req, resp);
                        } else {
                            req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                            req.getRequestDispatcher(url).forward(req, resp);
                        }
                    }

                }
            }
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
        }
    }

}
