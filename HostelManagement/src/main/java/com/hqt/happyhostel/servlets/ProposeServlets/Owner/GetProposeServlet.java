package com.hqt.happyhostel.servlets.ProposeServlets.Owner;

import com.hqt.happyhostel.dao.ProposeDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Propose;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetProposeServlet", value = "/GetProposeServlet")
public class GetProposeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account ownerAccount = (Account) session.getAttribute("USER");
            List<Propose> proposeList = new ProposeDAO().getAllProposeBySenderId(ownerAccount.getAccId());
            request.setAttribute("proposeList", proposeList);
            session.setAttribute("CURRENT_PAGE", "propose");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("propose-page").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
