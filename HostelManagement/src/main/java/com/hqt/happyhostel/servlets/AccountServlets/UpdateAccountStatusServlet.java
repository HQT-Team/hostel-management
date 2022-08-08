package com.hqt.happyhostel.servlets.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateAccountStatusServlet", value = "/UpdateAccountStatusServlet")
public class UpdateAccountStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("error-page");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-owner-account";

        AccountDAO accountDAO = new AccountDAO();

        try {
            int id = Integer.parseInt(request.getParameter("owner_id"));
            int status = Integer.parseInt(request.getParameter("status"));
            AccountInfo accountInf = accountDAO.getAccountInformationById(id);
            String domain = "http://localhost:8080/HappyHostel/loginPage";
            boolean check = false;
            switch (status) {
                case 0:
                    check = accountDAO.updateAccountStatus(id, 1);
                    new MailUtils().SendMailConfirmActiveOwnerAccount((accountInf.getInformation().getEmail()), domain);
                    break;
                case 1:
                    check = accountDAO.updateAccountStatus(id, -1);
                    new MailUtils().SendMailBlockOwnerAccount(accountInf.getInformation().getEmail());
                    break;
                case -1:
                    check = accountDAO.updateAccountStatus(id, 1);
                    new MailUtils().SendMailUnblockOwnerAccount(accountInf.getInformation().getEmail(), domain);
                    break;
            }
            if (check) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Cập nhật trạng thái tài khoản thành công.").build());
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Cập nhật trạng thái tài khoản thất bại.").build());
            }
        } catch (Exception e) {
            log("Error at DashboardServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
