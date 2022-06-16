package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InvalidInviteCodeServlet", value = "/InvalidInviteCodeServlet")
public class InvalidInviteCodeServlet extends HttpServlet {
    private final String success = "CreateInvitationPage";
    private final String denied = "denied";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;

        try {
            String roomId = request.getParameter("room_id");
            Account owner = null;

            HttpSession session = request.getSession(false);
            if (session != null) owner = (Account) session.getAttribute("USER");

            if (roomId != null && owner != null) {
                int roomID = Integer.parseInt(roomId);
                if (new HostelOwnerDAO().checkOwnerRoom(owner.getAccId(), roomID)) {
                    if (new RoomInviteDAO().updateRoomInviteCode(roomID, null, null, null)) {
                        new RoomDAO().updateRoomStatus(roomID, 1);
                        url = success;
                    } else url = denied;
                } else url = denied;
            } else url = denied;
        } catch (Exception e) {
            log("Error at InviteCodeServlet: " + e.toString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
