package com.hqt.happyhostel.filter;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;


public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        try {
            //get resource name
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex+1);

            //get authentication properties
            ServletContext context = request.getServletContext();
            Properties authProperties = (Properties) context.getAttribute("AUTHENTICATION_lIST");
            HttpSession session = httpRequest.getSession(true);

            //get cookie
            Cookie[] c = httpRequest.getCookies();
            String token = null;

            //check resource authentication
            String rule = (String) authProperties.getProperty(resource);
            if(rule != null && rule.equals("restricted")) {
                //CHECK COOKIE
                Account acc = new Account();
                if (c != null) {
                    for (Cookie cookie : c) {
                        if (cookie.getName().equals("selector")) {
                            token = cookie.getValue();
                        }
                    }
                    if (token != null) acc = HostelOwnerDAO.getAccountByToken(token);
                }
                //NO SESSION AND NO COOKIE => LOGIN
                if ((session == null || session.getAttribute("USER") == null) && acc == null) {
                    ((HttpServletResponse) response).sendRedirect("loginPage");
                }
                else {//HAVE SESSION OR COOKIE
                    session.setAttribute("USER", acc);
                    chain.doFilter(request, response);
                }
            }else {
                chain.doFilter(request, response);
            }
        }catch (Throwable t) {
            t.getMessage();
        }

    }
}
