package com.hqt.happyhostel.filter;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;



public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        try {
            //get resource name
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex+1);

            //get authentication properties
            ServletContext context = request.getServletContext();
            Properties authProperties = (Properties) context.getAttribute("AUTHORIZATION_lIST");
            HttpSession session = httpRequest.getSession(false);

            //check resource authentication
            String rule = (String) authProperties.getProperty(resource);


            if(rule != null && !"all".equals(rule)){
                Account acc = (Account) session.getAttribute("USER");
                if ((acc.getRole() == 0 && !"admin".equals(rule)) ||(acc.getRole() == 1 && !"hostel_owner".equals(rule)) || acc.getRole() == 2 && !"hostel_renter".equals(rule))
                    httpResponse.sendRedirect("denied");
                else chain.doFilter(request, response);
            }
            else chain.doFilter(request, response);


        }catch (Throwable t) {
            t.getMessage();
        }
    }
}
