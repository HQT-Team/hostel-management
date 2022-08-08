package com.hqt.happyhostel.servlets.OwnerServlets;

import com.hqt.happyhostel.dao.BillDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListInvoiceRoomServlet", value = "/GetListInvoiceRoomServlet")
public class GetListInvoiceRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomListBill";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();
            int roomId = ((Room) session.getAttribute("room")).getRoomId();

            List<Bill> listRoomBill = new BillDAO().getListBillByRoomID(roomId);
            request.setAttribute("listRoomBill", listRoomBill);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomListBill";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();
            int roomId = ((Room) session.getAttribute("room")).getRoomId();

            List<Bill> listRoomBill = new BillDAO().getListBillByRoomID(roomId);
            request.setAttribute("listRoomBill", listRoomBill);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
