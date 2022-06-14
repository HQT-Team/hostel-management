package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "GetHostelInforServlet", value = "/GetHostelInforServlet")
public class GetHostelInforServlet extends HttpServlet {
    public static final String ERROR = "hostel-renter-page";
    public static final String SUCCESS = "hostel-renter-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        Account acc = new Account();
        List<Infrastructures> infrastructures = new ArrayList<>();
        List<ServiceInfo> serviceInfo = new ArrayList<>();
        Information accInfo = new Information();
        try {
            HttpSession session = req.getSession();
            acc = (Account)session.getAttribute("USER");
            int renterId = acc.getAccId();
            //Get Hostel
            HostelDAO hostelDAO = new HostelDAO();
            InformationDAO informationDAO = new InformationDAO();
            Hostel hostel = hostelDAO.getHostelByRenterId(renterId);
            if (hostel!=null){
                req.setAttribute("HOSTEL", hostel);
                url = SUCCESS;
            }
            //Get Hostel Owner Info
            Information accountInfo = informationDAO.getHostelOwnerInforByRenterId(renterId);
            if (accountInfo!=null){
                req.setAttribute("ACCOUNT_INFOR", accountInfo);
                url = SUCCESS;
            }
            //Get Room Info
            Room roomInfo = RoomDAO.getHostelRoomInforByRenterId(renterId);
            if (roomInfo!=null){
                req.setAttribute("ROOM_INFOR", roomInfo);
                url = SUCCESS;
            }

            //Get Infrastructure
            infrastructures = InfrastructureDAO.getHostelInfrastructuresByRenterId(renterId);
            if (infrastructures.size() > 0){
                req.setAttribute("INFRASTRUCTURES", infrastructures);
                url = SUCCESS;
            }
            //Get Service
            serviceInfo = ServicesDAO.getHostelServicesByRenterId(renterId);
            if (serviceInfo!=null){
                req.setAttribute("SERVICES", serviceInfo);
                url = SUCCESS;
            }
            //Get Account Infor
            accInfo = new InformationDAO().getAccountInformationById(renterId);
            if (accInfo!=null){
                req.setAttribute("ACC_INFO", accInfo);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PAGE", "hostel-renter-page");
        }catch (Exception e){
            log("Error at GetHostelInforServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}
