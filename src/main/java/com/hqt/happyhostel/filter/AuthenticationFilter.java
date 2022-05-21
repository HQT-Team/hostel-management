package com.hqt.happyhostel.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
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
            HttpSession session = httpRequest.getSession(false);
            //check resource authentication
            String rule = (String) authProperties.getProperty(resource);
            if(rule != null && rule.equals("restricted")) {
                if (session == null || session.getAttribute("USER") == null) {
                    ((HttpServletResponse) response).sendRedirect("loginPage");
                } else {
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
