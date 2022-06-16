package com.hqt.happyhostel.servlet;

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


@WebServlet(name = "InviteCodeServlet", value = "/InviteCodeServlet")
public class InviteCodeServlet extends HttpServlet {
    private final String success = "invite-code-page";
    private final String denied = "denied";
    private String url;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String roomId = request.getParameter("room_id");
            Account owner = null;
            int ownerId = -1;
            StringBuilder inviteUrl = new StringBuilder("RenterRegisterPage?inviteCode=");

            HttpSession session = request.getSession(false);
            if (session != null) owner = (Account) session.getAttribute("USER");

            RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

            //check request parameter
            if (owner != null && roomId != null) {
                int roomID = Integer.parseInt(roomId);
                ownerId = owner.getAccId();

                //check xem roomID có thuộc ownerID không
                if (new HostelOwnerDAO().checkOwnerRoom(ownerId, roomID)) {
                    Timestamp endTime = null;
                    Room roomInvite = null;

                    //check xem trên databse có inviteCode của roomId này chưa
                    if (roomInviteDAO.getRoomInviteById(roomID).getInviteCode() != null) {
                        roomInvite = roomInviteDAO.getRoomInviteById(roomID);
                        inviteUrl = inviteUrl.append(roomInvite.getInviteCode());
                    } else {
                        //Create invite link
                        String inviteCode = RandomStringGenerator.randomInviteCode(5, roomId);
                        inviteUrl = inviteUrl.append(inviteCode);

                        //Create QR Code
                        QRCodeWriter barcodeWriter = new QRCodeWriter();
                        BitMatrix bitMatrix = barcodeWriter.encode(inviteUrl.toString(), BarcodeFormat.QR_CODE, 200, 200);
                        BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
                        String QRBase64 = EncodeBase64Utils.imageToBase64(qrImg);

                        //Create endTime
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Calendar startTime = Calendar.getInstance();
                        long timeInSecs = startTime.getTimeInMillis();
                        endTime = new Timestamp(timeInSecs + (30 * 60 * 1000));

                        if (roomInviteDAO.updateRoomInviteCode(roomID, inviteCode, QRBase64, sdf.format(endTime))) {
                            new RoomDAO().updateRoomStatus(roomID, 0);
                            roomInvite = roomInviteDAO.getRoomInviteById(roomID);
                        } else url = denied;

                    }
                    request.setAttribute("ROOM_INVITE", roomInvite);
                    request.setAttribute("URL_INVITE", inviteUrl);
                    url = success;
                } else {
                    url = denied;
                }
            } else url = denied;

        } catch (Exception e) {
            log("Error at InviteCodeServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
