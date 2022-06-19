package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "CheckInviteServlet", value = "/CheckInviteServlet")
public class CheckInviteServlet extends HttpServlet {
    private final String SUCCESS = "send-otp";
    private final String FAIL = "invite-page";
    private final String ERROR = "error-page";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String  url = ERROR;
        String inviteCode = request.getParameter("invite_code");
        HandlerStatus handlerStatus = null;
        try {
            if(inviteCode != null){
                url = FAIL;
                RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

                if(roomInviteDAO.checkRoomInviteCode(inviteCode) == false )
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã mời không hợp lệ").build();
                else if (roomInviteDAO.checkRoomInviteCodeExpiredTime(inviteCode) == false)
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã mời đã hết hạn. Vui lòng liên hệ chủ trọ để cung cấp mã mời mới !").build();
                else {
                    int renterId = roomInviteDAO.getAccountIdByInviteCode(inviteCode);
                    String userEmail = new AccountDAO().getAccountInformationById(renterId).getInformation().getEmail();
                    request.setAttribute("USER_EMAIL", userEmail);
                    url = SUCCESS;
                    request.setAttribute("ACCOUNT_ID", renterId);
                }

                request.setAttribute("RESPONSE_MSG", handlerStatus);
                request.setAttribute("INVITE_CODE", inviteCode);
            }

        }catch (Exception e){
            log("Error at CheckInviteServlet: " + e.toString());
        }finally {
            if(ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
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
