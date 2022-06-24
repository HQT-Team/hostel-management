package com.hqt.happyhostel.servlet.RenterRegisterServlets;

import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckInviteServlet", value = "/CheckInviteServlet")
public class CheckInviteServlet extends HttpServlet {
    private final String SUCCESS = "send-otp";
    private final String FAIL = "renter-register-page";
    private final String ERROR = "error-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String url = ERROR;
        String inviteCode = request.getParameter("invite-code");
        HandlerStatus handlerStatus = null;
        try {
            if (inviteCode != null) {
                url = FAIL;
                RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

                if (!roomInviteDAO.checkRoomInviteCode(inviteCode))
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã mời không hợp lệ").build();
                else if (!roomInviteDAO.checkRoomInviteCodeExpiredTime(inviteCode))
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã mời đã hết hạn. Vui lòng liên hệ chủ trọ để cung cấp mã mời mới !").build();
                else {
                    int renterId = roomInviteDAO.getAccountIdByInviteCode(inviteCode);
                    HttpSession session = request.getSession(true);
                    if (session != null) {
                        session.setAttribute("confirm-invite", "OK");
                        request.setAttribute("ACCOUNT_ID", renterId);
                        url = SUCCESS;
                    }
                }

                request.setAttribute("RESPONSE_MSG", handlerStatus);
            }

        } catch (Exception e) {
            log("Error at CheckInviteServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
