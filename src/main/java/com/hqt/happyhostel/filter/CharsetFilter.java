package com.hqt.happyhostel.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        // Set the default response content type and encoding
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}
