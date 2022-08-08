package com.hqt.happyhostel.servlets.RecoverPasswordServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.EncodeBase64Utils;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecoverPasswordResetServlet", value = "/RecoverPasswordResetServlet")
public class RecoverPasswordResetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HandlerStatus handlerStatus;
        String url = "error-page";
        try {
            String data = request.getParameter("data");

            if (data != null) {
                String decodeString = EncodeBase64Utils.decodeStringBase64(data);
                String[] decodeStringArr = decodeString.split("&");
                if (decodeStringArr.length > 0) {
                    String element1 = decodeStringArr[0];
                    String element2 = decodeStringArr[1];

                    int accountId;
                    try {
                        accountId = Integer.parseInt(element1.split("=")[1]);
                    } catch (Exception e) {
                        handlerStatus = HandlerStatus.builder()
                                .status(false)
                                .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                        request.setAttribute("RESPONSE_MSG", handlerStatus);
                        return;
                    }

                    String recoverCode;
                    try {
                        recoverCode = element2.split("=")[1];
                    } catch (Exception e) {
                        handlerStatus = HandlerStatus.builder()
                                .status(false)
                                .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                        request.setAttribute("RESPONSE_MSG", handlerStatus);
                        return;
                    }

                    boolean checkResult = new AccountDAO().checkAccountRequestRecoverPassword(accountId, recoverCode);
                    if (checkResult) {
                        url = "recover-password-result-reset";
                        request.setAttribute("ACCOUNT_ID", accountId);
                        request.setAttribute("RECOVER_CODE", recoverCode);
                    } else {
                        url = "recover-password";
                        handlerStatus = HandlerStatus.builder()
                                .status(false)
                                .content("Đường dẫn này đã hết hạn! Vui lòng gửi lại yêu cầu khôi phục mật khẩu mới!").build();
                        request.setAttribute("RESPONSE_MSG", handlerStatus);
                    }
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                request.setAttribute("RESPONSE_MSG", handlerStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "recover-password-result-reset";
        HandlerStatus handlerStatus;
        try {
            int accountId;
            try {
                accountId = Integer.parseInt(request.getParameter("accountId"));
            } catch (Exception e) {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                request.setAttribute("RESPONSE_MSG", handlerStatus);
                return;
            }

            String password = request.getParameter("password");
            if (password != null) {
                String passwordHashed = SecurityUtils.hashMd5(password);
                boolean updatePasswordResult = new AccountDAO().updateAccountPass(accountId, passwordHashed);
                if (updatePasswordResult) {
                    String userMail = new AccountDAO().getAccountInformationById(accountId).getInformation().getEmail();
                    new AccountDAO().addKeyAndExpirationTimeForPasswordRecoveryRequest(null, null, accountId);
                    new MailUtils().sendMailConfirmChangePassword(userMail);
                    handlerStatus = HandlerStatus.builder()
                            .status(true)
                            .content("Cập nhật mật khẩu mới thành công!").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                request.setAttribute("RESPONSE_MSG", handlerStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
