package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdateProfileServlet", value = "/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    public static final String ERROR = "HostelRenterProfilePage";
    public static final String SUCCESS = "HostelRenterProfilePage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        req.setCharacterEncoding("UTF-8");
        Account acc = new Account();
        Information accountInfos = new Information();
        try {
            HttpSession session = req.getSession();
            acc = (Account) session.getAttribute("USER");
            AccountInfo accountInfor = new AccountDAO().getAccountInformationById(acc.getAccId());
            int accId = acc.getAccId();
            String profileName = !req.getParameter("new-name").equals("") ? req.getParameter("new-name") : accountInfor.getInformation().getFullname();
            String profileEmail = !req.getParameter("new-email").equals("") ? req.getParameter("new-email") : accountInfor.getInformation().getEmail();
            String profileBirthday = !req.getParameter("new-birthday").equals("") ? req.getParameter("new-birthday") : accountInfor.getInformation().getBirthday();
            String profilePhone = !req.getParameter("new-phone").equals("") ? req.getParameter("new-phone") : accountInfor.getInformation().getPhone();
            String profileAddress = !req.getParameter("new-address").equals("") ? req.getParameter("new-address") : accountInfor.getInformation().getAddress();
            String profileCCCD = !req.getParameter("new-cccd").equals("") ? req.getParameter("new-cccd") : accountInfor.getInformation().getCccd();
            accountInfos = Information.builder().fullname(profileName).email(profileEmail).birthday(profileBirthday).phone(profilePhone).address(profileAddress).cccd(profileCCCD).build();
            HostelDAO dao = new HostelDAO();
            boolean checkUpdateProfile = new InformationDAO().updateProfileByAccId(accountInfos, accId);
            if (checkUpdateProfile) {
                url = SUCCESS;
            } else {
                session.setAttribute("Error", "Somethings Wrong!");
            }
        } catch (Exception e) {
            log("Error at UpdateHostel: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
