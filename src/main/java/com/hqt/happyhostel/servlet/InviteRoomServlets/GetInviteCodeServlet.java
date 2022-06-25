package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetInviteCodeServlet", value = "/GetInviteCodeServlet")
public class GetInviteCodeServlet extends HttpServlet {
    private final String SUCCESS = "invite-code-page";
    private final String FAIL = "roomDetail";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        String url = ERROR;
        int roomID;
        Account owner;
        Room roomInvite = null;
        StringBuilder inviteUrl = new StringBuilder("http://localhost:8080/HappyHostel/invite-code?invite-code=");
        try {
            int ownerId;
            String inviteCode;

            HttpSession session = request.getSession(false);
            if (session != null) {
                owner = (Account) session.getAttribute("USER");
                RoomInviteDAO roomInviteDAO = new RoomInviteDAO();
                roomID = (int) session.getAttribute("current_room_id");
                //check request parameter
                if (owner != null && roomID > 0) {
                    url = FAIL;
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
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
