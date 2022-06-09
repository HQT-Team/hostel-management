//package com.hqt.happyhostel.servlet;
//
//import com.hqt.happyhostel.utils.RandomStringGenerator;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "InviteCodeServlet", value = "/InviteCodeServlet")
//public class InviteCodeServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String roomId = request.getParameter("room_id");
//            String hostelId = request.getParameter("hostel_id");
//            int ownerId = Integer.parseInt(request.getParameter("owner_id"));
//            if(/*roomID khong thuoc hostel cua owner*/){
//                //tra ve url loi
//            }else{
//                String inviteCode = RandomStringGenerator.randomInviteCode(5,hostelId,roomId);
//                InviteCodeDAO.CreateInviteCode(roomId, inviteCode);
//                request.setAttribute("INVITE_CODE", inviteCode);
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
