package com.hqt.happyhostel.servlets.ProposeServlets.Owner;

import com.hqt.happyhostel.dao.ProposeDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SendProposeServlet", value = "/SendProposeServlet")
public class SendProposeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HandlerStatus handlerStatus;
            HttpSession session = request.getSession();
            Account ownerAccount = (Account) session.getAttribute("USER");
            String proposeContent = request.getParameter("propose-content").trim();
            if (!proposeContent.isEmpty()) {
                boolean insertStatus = new ProposeDAO().insertNewPropose(proposeContent, ownerAccount.getAccId());
                if (insertStatus) {
                    handlerStatus = HandlerStatus.builder()
                            .status(true)
                            .content("Gừi đề xuất/ý kiến tới quản trị viên thành công! Cảm ơn vì những đóng góp, phản hồi từ bạn!").build();
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Gửi đề xuất thất bại! Vui lòng thử lại trong chốc lát!").build();
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Không thể gửi đề xuất nếu bạn không nhập gì cả!").build();
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("propose").forward(request, response);
        }
    }
}
