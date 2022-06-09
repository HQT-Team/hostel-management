//package com.hqt.happyhostel.servlet;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.hqt.happyhostel.utils.EncodeBase64Utils;
//import com.hqt.happyhostel.utils.RandomStringGenerator;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Calendar;
//import java.util.Date;
//
//@WebServlet(name = "InviteCodeServlet", value = "/InviteCodeServlet")
//public class InviteCodeServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String roomId = request.getParameter("room_id");
//            String hostelId = request.getParameter("hostel_id");
//            String endTimeStr = request.getParameter("startTime");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//            int ownerId = Integer.parseInt(request.getParameter("owner_id"));
//            if(/*roomID khong thuoc hostel cua owner*/){
//                //tra ve url loi
//            }else{
//               //check xem trên databse có endDate của inviteCode của roomId này chưa
//
//                //nếu có rồi thì  gửi attribute END_DATE = endDate tren database
//                if(/*database.endTime != null*/){
////                    request.setAttribute("END_TIME",//database.endTime);
//                }
////
//                //chưa có thì tạo startDate = now sau đó endDate = startDate + 30p rồi gửi attribute END_DATE = endDate
//                else {
//                    Calendar startTime = Calendar.getInstance();
//                    long timeInSecs = startTime.getTimeInMillis();
//                    Date endTime = new Date(timeInSecs + (30 * 60 * 1000));
//                    request.setAttribute("END_TIME", sdf.format(endTime));
//                }
//
//
//                String inviteCode = RandomStringGenerator.randomInviteCode(5,roomId);
//                InviteCodeDAO.CreateInviteCode(roomId, inviteCode);
//
//
//                String inviteUrl = "RenterRegisterPage?invitecode="+inviteCode;
//
//                QRCodeWriter barcodeWriter = new QRCodeWriter();
//                BitMatrix bitMatrix = barcodeWriter.encode(inviteUrl, BarcodeFormat.QR_CODE, 200, 200);
//
//                BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
//                String base =  EncodeBase64Utils.imageToBase64(qrImg);
//                System.out.println(base);
//
//                request.setAttribute("INVITE_CODE", inviteCode);
//                request.setAttribute("QR_BASE64", base);
//            }
//
//        }catch (Exception e){
//            log("Error at InviteCodeServlet: " + e.toString());
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
