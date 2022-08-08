package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ProposeDAO;
import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Propose;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "AdminDashboard", value = "/AdminDashboard")
public class AdminDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "AdminDashBoardPage";
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        try {
            //Get list account of owner with role id is 1
            List<Account> totalAccountOwner = accountDAO.GetAccountsByRole(1);
            request.setAttribute("totalAccountOwner", totalAccountOwner.size() == 0 ? 0 : totalAccountOwner.size());

            //Get current month and year
            LocalDate currentdate = LocalDate.now();
            String currentMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
            String currentYear = String.valueOf(currentdate.getYear());
            String DateNow = currentMonth + "/" + currentYear;
            request.setAttribute("DateNow", DateNow);

            //Get list account owner in recent month
            List<Account> totalNewAccountInRecentMonth = accountDAO.GetAccountsByRoleInRecentMonth(1);
            request.setAttribute("totalNewAccountInRecentMonth", totalNewAccountInRecentMonth.size() == 0 ? 0 : totalNewAccountInRecentMonth.size());

            //Get list propose is waitting and accept
            List<Propose> proposeList = new ProposeDAO().getAllPropose();
            double proposeListWaiting = 0, proposeListAccepted = 0, percenProposeListAccepted = 0;
            if (proposeList.size() != 0) {
                for (Propose p : proposeList) {
                    if (p.getStatus() == 0) {
                        proposeListWaiting++;
                    }
                }
                for (Propose p : proposeList) {
                    if (p.getStatus() == 1) {
                        proposeListAccepted++;
                    }
                }
                percenProposeListAccepted = (proposeListAccepted / proposeList.size()) * 100;
                String percenTemp = String.valueOf(percenProposeListAccepted).substring(0, 3);
                percenProposeListAccepted = Double.parseDouble(percenTemp);
            }
            request.setAttribute("proposeListWaiting", (int) proposeListWaiting);
            request.setAttribute("percenProposeListAccepted", percenProposeListAccepted);

            session.setAttribute("CURRENT_PAGE", "dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
