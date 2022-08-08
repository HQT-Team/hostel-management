package com.hqt.happyhostel.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RenterRegisterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = null;

        String uri = httpRequest.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String resource = uri.substring(lastIndex+1);

        HttpSession session = httpRequest.getSession(false);
        if(session != null){
            String confirmInvite = (String) session.getAttribute("confirm-invite");
            String confirmOTP = (String) session.getAttribute("confirm-OTP");
            String confirmContinue = (String) session.getAttribute("confirm-continue");
            if("verify-renter-page".equals(resource)){
                if(confirmInvite != null) chain.doFilter(request, response);
                else httpResponse.sendRedirect("renter-register-page");
            }
            else if("confirm-room-info-page".equals(resource)){
                if (confirmInvite != null && confirmOTP != null) chain.doFilter(request, response);
                else httpResponse.sendRedirect("renter-register-page");
            }
            else if("input-account-information-page".equals(resource)){
                if (confirmInvite != null && confirmOTP != null && confirmContinue != null) chain.doFilter(request, response);
                else httpResponse.sendRedirect("renter-register-page");
            }
        }else httpResponse.sendRedirect("HomePage");

    }
}
