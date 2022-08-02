package com.hqt.happyhostel.servlets.ProposeServlets.Admin;

import com.hqt.happyhostel.dao.ProposeDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HandleProposeServlet", value = "/HandleProposeServlet")
public class HandleProposeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HandlerStatus handlerStatus = null;
            HttpSession session = request.getSession();
            Account adminAccount = (Account) session.getAttribute("USER");

            int proposeId = Integer.parseInt(request.getParameter("proposeId"));
            String replyMsg = request.getParameter("proposeReply");
            int changeStatus = Integer.parseInt(request.getParameter("changeStatus"));
            boolean updateResult = new ProposeDAO().updatePropose(proposeId, replyMsg, changeStatus, adminAccount.getAccId());
            String msg = "";
            switch (changeStatus) {
                case 1:
                    msg = "Phê duyệt";
                    break;
                case -1:
                    msg = "Từ chối";
                    break;
            }
            if (updateResult) {
                handlerStatus = HandlerStatus.builder()
                        .status(true)
                        .content(msg + " đề xuất/ý kiến thành công!").build();
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! " + msg + " đề xuất/ý kiến thất bại!").build();
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-propose").forward(request, response);
        }
    }
}
