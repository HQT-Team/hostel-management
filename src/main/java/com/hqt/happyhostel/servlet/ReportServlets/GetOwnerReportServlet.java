package com.hqt.happyhostel.servlet.ReportServlets;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.ReportDetailDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.ReportDetail;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetOwnerReportServlet", value = "/GetOwnerReportServlet")
public class GetOwnerReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "owner-report-page";
        int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));
        try {
            HttpSession session = request.getSession();
            Account hostelAccount = (Account)session.getAttribute("USER");
            int hostelOwnerId = hostelAccount.getAccId();

            List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
            List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
            List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

            List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);

            session.setAttribute("CURRENT_PAGE", "report");
            request.setAttribute("TYPE", type);
            request.setAttribute("HOSTEL_LIST", hostelList);
            request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
            request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
            request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "owner-report-page";
        try {
            HttpSession session = request.getSession();
            Account hostelAccount = (Account)session.getAttribute("USER");
            int hostelOwnerId = hostelAccount.getAccId();

            String hostelId = request.getParameter("hostelId");
            String roomId = request.getParameter("roomId");
            int type = Integer.parseInt(request.getParameter("type"));

            if (type == 0) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                    List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                    List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                    List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);

                    session.setAttribute("CURRENT_PAGE", "report");
                    request.setAttribute("TYPE", type);
                    request.setAttribute("STATE", "Filter");
                    request.setAttribute("HOSTEL_LIST", hostelList);
                    request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                    request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                    request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                } else {
                    if (roomId == null || roomId.equals("")) {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", hostelId, "", "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    } else {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", hostelId, roomId, "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("CURRENT_ROOM", Integer.parseInt(roomId));
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    }
                }
            } else if (type == 1) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                    List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                    List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                    List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);

                    session.setAttribute("CURRENT_PAGE", "report");
                    request.setAttribute("TYPE", type);
                    request.setAttribute("STATE", "Filter");
                    request.setAttribute("HOSTEL_LIST", hostelList);
                    request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                    request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                    request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                } else {
                    if (roomId == null || roomId.equals("")) {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", hostelId, "", "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    } else {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", hostelId, roomId, "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("CURRENT_ROOM", Integer.parseInt(roomId));
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    }
                }
            } else if (type == 2) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                    List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                    List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");

                    List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);

                    session.setAttribute("CURRENT_PAGE", "report");
                    request.setAttribute("TYPE", type);
                    request.setAttribute("STATE", "Filter");
                    request.setAttribute("HOSTEL_LIST", hostelList);
                    request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                    request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                    request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                } else {
                    if (roomId == null || roomId.equals("")) {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", hostelId, "", "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    } else {
                        List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                        List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                        List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", hostelId, roomId, "");

                        List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);
                        List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));

                        session.setAttribute("CURRENT_PAGE", "report");
                        request.setAttribute("TYPE", type);
                        request.setAttribute("STATE", "Filter");
                        request.setAttribute("HOSTEL_LIST", hostelList);
                        request.setAttribute("CURRENT_HOSTEL", Integer.parseInt(hostelId));
                        request.setAttribute("ROOM_LIST", roomList);
                        request.setAttribute("CURRENT_ROOM", Integer.parseInt(roomId));
                        request.setAttribute("REPORT_NO_REPLY_DETAIL_LIST", reportNoReplyDetailList);
                        request.setAttribute("REPORT_PROCESS_DETAIL_LIST", reportProcessDetailList);
                        request.setAttribute("REPORT_FINISHED_DETAIL_LIST", reportFinishedDetailList);
                    }
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
