package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "profile?type=2";
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("USER");
            String oldPassword = SecurityUtils.hashMd5( request.getParameter("old-password"));

            if (new AccountDAO().getAccountByUsernameAndPassword(account.getUsername(), oldPassword) != null) {
                String newPassword = SecurityUtils.hashMd5( request.getParameter("new-password"));
                boolean resultUpdate = new AccountDAO().updateAccountPass(account.getAccId(), newPassword);
                if (resultUpdate) {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Đổi mật khẩu thành công!").build());
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
                }
            } else {
                request.setAttribute("ERROR", "error");
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Mật khẩu cũ không đúng! Vui lòng kiểm tra lại!").build());
            }
        } catch (Exception e) {
            log("Error at ChangePasswordServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}