package com.hqt.happyhostel.servlets.RenterRegisterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
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
    private final String FAIL = "input-account-information-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        AccountDAO accountDAO = new AccountDAO();
        HandlerStatus handlerStatus = null;
        try {
            HttpSession session = req.getSession(false);
            if (session != null) {
                url = FAIL;
                String username = (String) session.getAttribute("RENTER_ACCOUNT_USERNAME");
                String fullname = req.getParameter("fullname");
                String password = SecurityUtils.hashMd5(req.getParameter("password"));
                String confirmPassword = SecurityUtils.hashMd5(req.getParameter("confirm-password"));

                if ((username != null && !username.isBlank()) && (fullname != null && !fullname.isBlank()) && (password != null && !password.isBlank()) && (confirmPassword != null && !confirmPassword.isBlank())) {
                    if (!password.equals(confirmPassword)) {
                        // password and password confirm is not equal
                        handlerStatus = HandlerStatus.builder().status(false).content("Mật khẩu không hợp lệ vui lòng kiểm tra lại").build();
                        req.setAttribute("fullname", fullname);
                        req.setAttribute("username", username);
                        req.setAttribute("RESPONSE_MSG", handlerStatus);
                        req.getRequestDispatcher(url).forward(req, resp);
                    } else {
                        int accId = new AccountDAO().getAccountIdByUserName(username);
                        boolean check1 = accountDAO.updateAccountFullName(accId, fullname);
                        boolean check2 = accountDAO.updateAccountPass(accId, password);
                        boolean check3 = accountDAO.updateAccountStatus(accId, 1);

                        if (check1 && check2 && check3) {
                            handlerStatus = HandlerStatus.builder().status(true).content("Cập nhật thông tin tài khoản thành công. Từ giờ bạn đã có thể sử dụng dịch vụ của chúng tôi. Xin cảm ơn.").build();
                            req.setAttribute("RESPONSE_MSG", handlerStatus);

                            //xoa invite code cua Room
                            int roomId = new RoomInviteDAO().getRoomInviteByAccountId(accId);
                            new RoomInviteDAO().updateRoomInviteCode(roomId, null, null, null);

                            //xoa session
                            session.invalidate();
                            url = SUCCESS;
                        } else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Đã có lỗi xảy ra, vui lòng thử lại sau!").build();
                            req.setAttribute("RESPONSE_MSG", handlerStatus);
                        }
                    }
                }
            }

        } catch (Exception e) {
            log("Error at RenterRegisterServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url)) resp.sendRedirect(url);
            else req.getRequestDispatcher(url).forward(req, resp);
        }
    }

}
