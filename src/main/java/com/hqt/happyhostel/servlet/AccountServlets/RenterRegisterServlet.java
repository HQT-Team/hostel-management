package com.hqt.happyhostel.servlet.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.Room;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RenterRegisterServlet", value = "/RenterRegisterServlet")
public class RenterRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        AccountDAO accountDAO = new AccountDAO();
        RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

        String url = "registerPage";

        try {
            String registerType = req.getParameter("registertype");
            String accepted = req.getParameter("accepted");
            if(accepted != null){

                    String fullname = req.getParameter("fullname");
                    String username = req.getParameter("username");
                    String email = req.getParameter("email").toLowerCase().trim();
                    String password = SecurityUtils.hashMd5(req.getParameter("password"));
                    String cccd = req.getParameter("cccd");
                    String inviteCode = req.getParameter("inviteCode");


                    if (!accountDAO.isExistUsername(username)) {

                        if (!new InformationDAO().isExistEmail(email)) {
                            Room roomInvite = roomInviteDAO.getRoomIdByInviteCode(inviteCode);
                            Information information = Information.builder()
                                    .fullname(fullname)
                                    .email(email)
                                    .cccd(cccd).build();

                            AccountInfo accountInfo = new AccountInfo(information);

                            Account registerAccount = Account.builder()
                                    .accId(0)
                                    .username(username)
                                    .password(password)
                                    .status(0)
                                    .role(1)
                                    .roomId(roomInvite.getRoomId())
                                    .accountInfo(accountInfo).build();

                            boolean check = accountDAO.addAnAccount(registerAccount);
                            if (check) {
                                req.setAttribute("SUCCESS", "Đăng ký tài khoản thành công! Tài khoản sẽ được quản trị viên xem xét và thông báo kết quả qua email!");
                                req.getRequestDispatcher(url).forward(req, resp);
                            } else {
                                req.setAttribute("ERROR", "Đã có lỗi xảy ra, vui lòng thử lại sau!");
                                req.getRequestDispatcher(url).forward(req, resp);
                            }

                        } else {
                            // email has been existed
                            req.setAttribute("ERROR_TYPE", "email");
                            req.setAttribute("ERROR", "Email đã tồn tại trong hệ thống!");
                            req.setAttribute("fullname", fullname);
                            req.setAttribute("username", username);
                            req.setAttribute("email", email);
                            req.setAttribute("cccd", cccd);
                            req.getRequestDispatcher(url).forward(req, resp);
                        }

                    } else {
                        // username has been existed
                        req.setAttribute("ERROR_TYPE", "username");
                        req.setAttribute("ERROR", "Tài khoản đã tồn tại trong hệ thống!");
                        req.setAttribute("fullname", fullname);
                        req.setAttribute("username", username);
                        req.setAttribute("email", email);
                        req.setAttribute("cccd", cccd);
                        req.getRequestDispatcher(url).forward(req, resp);
                    }
            }else {
                resp.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
        }
    }

}
