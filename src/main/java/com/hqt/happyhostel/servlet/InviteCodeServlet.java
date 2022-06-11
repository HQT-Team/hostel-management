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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "InviteCodeServlet", value = "/InviteCodeServlet")
public class InviteCodeServlet extends HttpServlet {
    private final String success = "CreateInvitationPage";
    private final String denied = "denied";
    private String url;
    private StringBuilder inviteUrl = new StringBuilder("RenterRegisterPage?inviteCode=");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String roomId = request.getParameter("room_id");
            Account owner = null;
            int ownerId = -1;

            HttpSession session = request.getSession(false);
            if(session != null) owner = (Account) session.getAttribute("USER");

            //check request parameter
            if(owner != null && roomId != null){
                int roomID = Integer.parseInt(roomId);
                ownerId = owner.getAccId();

                //check xem roomID có thuộc ownerID không
                if(HostelOwnerDAO.checkOwnerRoom(ownerId, roomID)){
                    Date endTime = null;
                    Room roomInvite = null;

                    //check xem trên databse có inviteCode của roomId này chưa
                    if(RoomInviteDAO.getRoomInviteById(roomID).getInviteCode() != null){
                        roomInvite = RoomInviteDAO.getRoomInviteById(roomID);
                        inviteUrl = inviteUrl.append(roomInvite.getInviteCode()) ;
                    }else {
                        //Create invite link
                        String inviteCode = RandomStringGenerator.randomInviteCode(5,roomId);
                        inviteUrl = inviteUrl.append(inviteCode) ;

                        //Create QR Code
                        QRCodeWriter barcodeWriter = new QRCodeWriter();
                        BitMatrix bitMatrix = barcodeWriter.encode(inviteUrl.toString(), BarcodeFormat.QR_CODE, 200, 200);
                        BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
                        String QRBase64 =  EncodeBase64Utils.imageToBase64(qrImg);

                        //Create endTime
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Calendar startTime = Calendar.getInstance();
                        long timeInSecs = startTime.getTimeInMillis();
                        endTime = new Date(timeInSecs + (30 * 60 * 1000));

                        RoomInviteDAO.updateRoomInviteCode(roomID, inviteCode, QRBase64, sdf.format(endTime));
                        roomInvite = Room.builder()
                                .roomId(roomID)
                                .inviteCode(inviteCode)
                                .QRCode(QRBase64)
                                .expiredTimeCode(endTime)
                                .build();
                    }
                    request.setAttribute("ROOM_INVITE", roomInvite);
                    request.setAttribute("URL_INVITE", inviteUrl);
                    url = success;
                }else{
                    url = denied;
                }
            }else url = denied;

        }catch (Exception e){
            log("Error at InviteCodeServlet: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
