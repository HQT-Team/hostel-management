package com.hqt.happyhostel.servlet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Room;
import com.hqt.happyhostel.utils.EncodeBase64Utils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "InviteCodeServlet", value = "/InviteCodeServlet")
public class InviteCodeServlet extends HttpServlet {
    private String url = "CreateInvitationPage";
    private StringBuilder inviteUrl = new StringBuilder("RenterRegisterPage?inviteCode=");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StringBuilder inviteUrl = new StringBuilder("RenterRegisterPage?inviteCode=");
            String roomId = request.getParameter("room_id");
            int roomID = Integer.parseInt(roomId);

            String hostelId = request.getParameter("hostel_id");
            int hostelID = Integer.parseInt(hostelId);

            String endTimeStr = request.getParameter("startTime");
            Date endTime = null;
            Room roomInvite = null;
            int ownerId = Integer.parseInt(request.getParameter("owner_id"));
            if(//*roomID khong thuoc hostel cua owner*/){
                url = "HostelOwnerPage";
            }else{
               //check xem trên databse có inviteCode của roomId này chưa
                if(RoomDAO.getRoomInviteById(roomID).getInviteCode() != null){
                    roomInvite = RoomDAO.getRoomInviteById(roomID);
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

                    RoomDAO.updateRoomInviteCode(roomID, inviteCode, QRBase64, sdf.format(endTime));
                    roomInvite = Room.builder()
                            .roomId(roomID)
                            .hostelId(hostelID)
                            .inviteCode(inviteCode)
                            .QRCode(QRBase64)
                            .expiredTimeCode(endTime)
                            .build();
                }
                request.setAttribute("ROOM_INVITE", roomInvite);
                request.setAttribute("URL_INVITE", inviteUrl);
            }

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
