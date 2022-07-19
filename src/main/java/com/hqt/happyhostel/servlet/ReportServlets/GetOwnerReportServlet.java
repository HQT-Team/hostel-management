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
import java.util.ArrayList;
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
        Gson gson = new Gson();
        try {
            HttpSession session = request.getSession();
            Account hostelAccount = (Account)session.getAttribute("USER");
            int hostelOwnerId = hostelAccount.getAccId();

            String hostelId = request.getParameter("hostelId");
            String roomId = request.getParameter("roomId");
            int type = Integer.parseInt(request.getParameter("type"));

            List<Object> list = new ArrayList<>();
            if (type == 0) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", "", "", "");
                    list.add(reportNoReplyDetailList);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<ReportDetail> reportNoReplyDetailList;
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", hostelId, "", "");
                    } else {
                        reportNoReplyDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "0", hostelId, roomId, "");
                    }
                    list.add(reportNoReplyDetailList);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            }
            else if (type == 1) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", "", "", "");
                    list.add(reportProcessDetailList);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<ReportDetail> reportProcessDetailList;
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", hostelId, "", "");
                    } else {
                        reportProcessDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "1", hostelId, roomId, "");
                    }
                    list.add(reportProcessDetailList);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            }
            else if (type == 2) {
                if (hostelId.equals("")) {
                    List<ReportDetail> reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", "", "", "");
                    list.add(reportFinishedDetailList);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    List<ReportDetail> reportFinishedDetailList;
                    if (roomId == null || roomId.equals("")) {
                        reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", hostelId, "", "");
                    } else {
                        reportFinishedDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "2", hostelId, roomId, "");
                    }
                    list.add(reportFinishedDetailList);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
