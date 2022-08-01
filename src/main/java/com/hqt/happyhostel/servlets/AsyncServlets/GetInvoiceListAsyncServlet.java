package com.hqt.happyhostel.servlets.AsyncServlets;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InvoiceDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetInvoiceListAsyncServlet", value = "/GetInvoiceListAsyncServlet")
public class GetInvoiceListAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        Account acc;
        RoomDAO roomDAO = new RoomDAO();
        HostelDAO hostelDAO = new HostelDAO();
        try {
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();

            String hostelId = request.getParameter("hostelId");
            String roomId = request.getParameter("roomId");
            int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));

            List<Object> list = new ArrayList<>();
            Map<Integer, Room> RoomsOfBills = new HashMap<>();
            Map<Integer, Hostel> HostelOfBills = new HashMap<>();
            if (type == 0) {
                List<Bill> invoices = new InvoiceDAO().getInvoiceListByOwnerAccountID(accountId, 0);
                if (hostelId.equals("")) {
                    for (Bill bill: invoices) {
                        Room room = roomDAO.getRoomById(bill.getRoomID());
                        Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                        RoomsOfBills.put(bill.getBillID(), room);
                        HostelOfBills.put(bill.getBillID(), hostel);
                    }
                    list.add(invoices);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<Bill> invoicesById = new ArrayList<>();
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    } else {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId) && bill.getRoomID() == Integer.parseInt(roomId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    }
                    list.add(invoicesById);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            } else if (type == 1) {
                List<Bill> invoices = new InvoiceDAO().getInvoiceListByOwnerAccountID(accountId, 1);
                if (hostelId.equals("")) {
                    for (Bill bill: invoices) {
                        Room room = roomDAO.getRoomById(bill.getRoomID());
                        Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                        RoomsOfBills.put(bill.getBillID(), room);
                        HostelOfBills.put(bill.getBillID(), hostel);
                    }
                    list.add(invoices);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<Bill> invoicesById = new ArrayList<>();
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    } else {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId) && bill.getRoomID() == Integer.parseInt(roomId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    }
                    list.add(invoicesById);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            }
            session.setAttribute("CURRENT_PAGE", "invoice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
