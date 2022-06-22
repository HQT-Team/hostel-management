package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Room;
import com.hqt.happyhostel.utils.EncodeBase64Utils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "GetInviteCodeServlet", value = "/GetInviteCodeServlet")
public class GetInviteCodeServlet extends HttpServlet {
    private final String SUCCESS = "invite-code-page";
    private final String FAIL = "createInvite";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        String roomId = null;
        Account owner = null;
        Room roomInvite = null;
        StringBuilder inviteUrl = new StringBuilder("RenterRegisterPage?inviteCode=");
        try {

            roomId = request.getParameter("room_id");
            int ownerId = -1;
            String inviteCode = null;

            HttpSession session = request.getSession(false);
            if (session != null) {
                owner = (Account) session.getAttribute("USER");
                RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

                //check request parameter
                if (owner != null && roomId != null) {
                    url = FAIL;
                    int roomID = Integer.parseInt(roomId);
                    ownerId = owner.getAccId();

                    //check xem roomID có thuộc ownerID không
                    if (new HostelOwnerDAO().checkOwnerRoom(ownerId, roomID)) {
                        roomInvite = roomInviteDAO.getRoomInviteById(roomID);
                        inviteCode = roomInvite.getInviteCode();

                        //check xem trên databse có inviteCode của roomId này chưa
                        if (roomInvite != null && inviteCode != null) {
                            inviteUrl = inviteUrl.append(roomInvite.getInviteCode());
                            url = SUCCESS;
                        }
                    }
                    request.setAttribute("ROOM_INVITE", roomInvite);
                    request.setAttribute("URL_INVITE", inviteUrl);
                }
            }

        } catch (Exception e) {
            log("Error at GetInviteCodeServlet: " + e.toString());
        } finally {
            if (owner != null && roomId != null) request.getRequestDispatcher(url).forward(request, response);
            else response.sendRedirect(url);
        }
    }
}
