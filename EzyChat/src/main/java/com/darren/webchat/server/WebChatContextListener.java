package com.darren.webchat.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 */
public class WebChatContextListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("users", new Users());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Users users =  (Users) servletContextEvent.getServletContext().getAttribute("users");
        users.notifyUsersWebChatShuttingDown();
    }
}
