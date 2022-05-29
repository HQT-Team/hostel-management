package com.hqt.happyhostel.listener;

import com.hqt.happyhostel.utils.PropertiesFileHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        ServletContext context = sce.getServletContext();
        String siteMapLocation = context.getInitParameter("SITEMAP_PROPERTIES_FILE_LOCATION");
        String authentication = context.getInitParameter("AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        String authorization = context.getInitParameter("AUTHORIZATION_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty = PropertiesFileHelper.getProperties(context, siteMapLocation);
        Properties authenticationProperties = PropertiesFileHelper.getProperties(context, authentication);
        Properties authorizationProperties = PropertiesFileHelper.getProperties(context, authorization);
        context.setAttribute("SITE_MAP", siteMapProperty);
        context.setAttribute("AUTHENTICATION_lIST", authenticationProperties);
        context.setAttribute("AUTHORIZATION_lIST", authorizationProperties);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
