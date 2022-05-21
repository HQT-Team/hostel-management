package com.hqt.happyhostel.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;



public class DispatcherFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        String url;
        try{
            //get site map
            ServletContext context = request.getServletContext();
            Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

            //get resource name
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex+1);

            //get site mapping
            url = siteMap.getProperty(resource);
            if (url != null) {
                RequestDispatcher rd = httpRequest.getRequestDispatcher(url);
                rd.forward(request, response);
            }else {
                chain.doFilter(request, response);
            }

        }catch (Exception t){
           t.printStackTrace();
        }


    }
}
