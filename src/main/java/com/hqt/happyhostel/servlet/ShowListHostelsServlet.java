package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Hostels;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShowListHostelsServlet", value = "/ShowListHostelsServlet")
public class ShowListHostelsServlet extends HttpServlet {
    public static final String ERROR = "error.jsp";
    public static final String SUCCESS = "list-hostel-page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        try {
            HostelDAO hostelDAO = new HostelDAO();
            List<Hostels> listHostel = hostelDAO.getListHostels();
            if (listHostel.size() > 0) {
                req.setAttribute("LIST_HOSTEL", listHostel);
                url = SUCCESS;
            }
            HttpSession session = req.getSession();
            session.setAttribute("CURRENT_PAGE", "hostel");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
