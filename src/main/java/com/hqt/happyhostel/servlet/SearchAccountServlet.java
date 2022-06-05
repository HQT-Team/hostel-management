package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "searchAccountServlet", value = "/searchAccountServlet")
public class SearchAccountServlet extends HttpServlet {

    private final String SEARCH_PAGE = "searchPage";//de tam mot sua sau

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchBy = request.getParameter("txtSearchBy");
            String keyword = request.getParameter("txtKeyword");
            ArrayList<Account> list = null;
            if( keyword != null && keyword.trim().length() > 0 && searchBy != null ) {
                list = AccountDAO.GetAllBy(searchBy, keyword);
            }else {
                list = AccountDAO.GetAll();
            }

            if(list.size() != 0 && !list.isEmpty()) request.setAttribute("ACC_LIST", list);
            else request.setAttribute("WARNING", "Danh sách các tài khoản đang trống");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
        }
    }
}
