package com.hqt.happyhostel.servlets.RenterServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetHostelInforServlet", value = "/GetHostelInforServlet")
public class GetHostelInforServlet extends HttpServlet {
    public static final String ERROR = "hostel-renter-page";
    public static final String SUCCESS = "hostel-renter-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        Account acc;
        List<Infrastructures> infrastructures;
        List<ServiceInfo> serviceInfo;
        Information accInfo;
        try {
            HttpSession session = req.getSession();
            acc = (Account) session.getAttribute("USER");
            int renterId = acc.getAccId();

            HostelDAO hostelDAO = new HostelDAO();
            InformationDAO informationDAO = new InformationDAO();
            req.setAttribute("uri", req.getRequestURI());
            //Get Hostel
            Hostel hostel = hostelDAO.getHostelByRenterId(renterId);
            if (hostel != null) {
                req.setAttribute("HOSTEL", hostel);
                url = SUCCESS;
            }
            //Get Hostel Owner Info
            Information accountInfo = informationDAO.getHostelOwnerInfoByRenterId(renterId);
            if (accountInfo != null) {
                req.setAttribute("ACCOUNT_INFOR", accountInfo);
                url = SUCCESS;
            }
            //Get Room Info
            List<RoommateInfo> roommateInfos = new RoommateInfoDAO().getListRoommatesOfAnAccount(renterId);

            int numberOfMembers = roommateInfos.size();
            req.setAttribute("NUM_OF_MEMBERS", numberOfMembers);


            Room roomInfo = new RoomDAO().getRoomInfoByRenterId(renterId);
            if (roomInfo != null) {
                req.setAttribute("ROOM_INFOR", roomInfo);
                url = SUCCESS;
            }

            //Get Infrastructure
            infrastructures = new InfrastructureDAO().getRoomInfrastructures(roomInfo.getRoomId());
            if (infrastructures.size() > 0) {
                req.setAttribute("INFRASTRUCTURES", infrastructures);
                url = SUCCESS;
            }

            //Get Service
            serviceInfo = new ServiceInfoDAO().getServicesOfHostel(roomInfo.getHostelId());
            if (serviceInfo != null) {
                req.setAttribute("SERVICES", serviceInfo);
                url = SUCCESS;
            }

            //Get Account Infor
            accInfo = new InformationDAO().getAccountInformationById(renterId);
            if (accInfo != null) {
                req.setAttribute("ACC_INFO", accInfo);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PAGE", "hostel-renter-page");
        } catch (Exception e) {
            log("Error at GetHostelInforServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
