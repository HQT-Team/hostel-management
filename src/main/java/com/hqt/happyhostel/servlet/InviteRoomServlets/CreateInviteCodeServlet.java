package com.hqt.happyhostel.servlet.InviteRoomServlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dao.RoomInviteDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
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


@WebServlet(name = "CreateInviteCodeServlet", value = "/CreateInviteCodeServlet")
public class CreateInviteCodeServlet extends HttpServlet {
    private final String SUCCESS = "invite-code-page";
    private final String FAIL1 = "create-room-account-page";
    private final String FAIL2 = "roomDetail?roomID=";
    private final String ERROR = "error-page";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        String roomId;
        Account owner;
        Room roomInvite = null;
        StringBuilder inviteUrl = new StringBuilder("http://localhost:8080/HappyHostel/invite-code?invite-code=");
        HandlerStatus handlerStatus = null;
        try {

            roomId = request.getParameter("room_id");
            int ownerId;
            String inviteCode;

            HttpSession session = request.getSession(false);
            if (session != null) {
                url = FAIL1;
                owner = (Account) session.getAttribute("USER");
                RoomInviteDAO roomInviteDAO = new RoomInviteDAO();

                // Check request parameter
                if (owner != null && roomId != null) {
                    int roomID = Integer.parseInt(roomId);
                    ownerId = owner.getAccId();

                    // Check xem roomID có thuộc ownerID không
                    if (new HostelOwnerDAO().checkOwnerRoom(ownerId, roomID)) {
                        //Check room status
                        int roomStatus = new RoomDAO().getRoomById(roomID).getRoomStatus();
                        if( roomStatus != 0){
                            // Create invite link
                            inviteCode = RandomStringGenerator.randomInviteCode(5, roomId);
                            inviteUrl = inviteUrl.append(inviteCode);

                            // Create QR Code
                            QRCodeWriter barcodeWriter = new QRCodeWriter();
                            BitMatrix bitMatrix = barcodeWriter.encode(inviteUrl.toString(), BarcodeFormat.QR_CODE, 200, 200);
                            BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
                            String QRBase64 = EncodeBase64Utils.imageToBase64(qrImg);

                            // Create endTime
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Calendar startTime = Calendar.getInstance();
                            long timeInSecs = startTime.getTimeInMillis();
                            Timestamp endTime = new Timestamp(timeInSecs + (30 * 60 * 1000));

                            // Set invite code into database
                            if (roomInviteDAO.updateRoomInviteCode(roomID, inviteCode, QRBase64, sdf.format(endTime))) {
                                new RoomDAO().updateRoomStatus(roomID, -1);
                                roomInvite = roomInviteDAO.getRoomInviteById(roomID);
                                handlerStatus = HandlerStatus.builder().status(true).content("Tạo mã mời mới thành công.").build();
                                url = SUCCESS;
                            }
                        }else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Phòng đã có người thuê. Không thể tạo mã mời mới.").build();
                            url = FAIL2+roomId;
                        }
                    }
                    request.setAttribute("ROOM_INVITE", roomInvite);
                    request.setAttribute("URL_INVITE", inviteUrl);
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            }
        } catch (Exception e) {
            log("Error at InviteCodeServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url))
                response.sendRedirect(url);
            else
                request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
