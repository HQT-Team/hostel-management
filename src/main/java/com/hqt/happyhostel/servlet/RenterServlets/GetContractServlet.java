package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.ContractDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Contract;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "GetContractServlet", value = "/GetContractServlet")
public class GetContractServlet extends HttpServlet {
    public static final String ERROR = "Renter-contract";
    public static final String SUCCESS = "Renter-contract";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        Account acc;
        try {
            HttpSession session = req.getSession();
            acc = (Account)session.getAttribute("USER");
            int accId = acc.getAccId();
            InformationDAO infoDao = new InformationDAO();
            //Get Renter
            Information renterInfo = infoDao.getAccountInformationById(accId);
            if (renterInfo!=null){
                req.setAttribute("RENTER_INFO", renterInfo);
                url = SUCCESS;
            }
            //Get Hostel Owner
            Information ownerInfo = infoDao.getHostelOwnerInforByRenterId(accId);
            if (ownerInfo!=null){
                req.setAttribute("OWNER_INFO", ownerInfo);
                url = SUCCESS;
            }
            //Get Hostel Address
            HostelDAO hostelDAO = new HostelDAO();
            Hostel hostel = hostelDAO.getHostelByRenterId(accId);
            if (hostel!=null){
                req.setAttribute("HOSTEL", hostel);
                url = SUCCESS;
            }
            //Get Contract Information
            ContractDAO contractDAO = new ContractDAO();
            Contract contract = contractDAO.getContractByRenterId(accId);
            if (contract!=null){
                req.setAttribute("CONTRACT", contract);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PAGE", "hostel-renter-page");
        }catch (Exception e){
            log("Error at GetContractServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
