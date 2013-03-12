package com.darren.webchat.server;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 */
public class WebChatSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User user = (User) httpSessionEvent.getSession().getAttribute("user");
        Users users = (Users) httpSessionEvent.getSession().getServletContext().getAttribute("users");
        users.userLoggedOut(user);
    }

}
