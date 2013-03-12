package com.darren.webchat.server;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;
import com.darren.webchat.client.rpc.WebChatService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 */
@SuppressWarnings("serial")
public class WebChatServiceImpl extends RemoteServiceServlet implements WebChatService {

    @Override
    public Message login(String username, String password) {
        User user = new User(username);
        Message message = getUsers().addNewUser(user);
        if(message.getMessageType().equals(MessageType.LOGIN_SUCCESSFUL)) {
            updateSession(user);
        }
        return message;
    }

    @Override
    public void sendMessage(Message message) {
        getUsers().sendMessage(message);
    }

    @Override
    public Message receiveMessage() {
        return getUsers().getMessage((User)getThreadLocalRequest().getSession().getAttribute("user"));
    }

    private void updateSession(User user) {
        int twentySeconds = 20;
        getThreadLocalRequest().getSession().setMaxInactiveInterval(twentySeconds);
        getThreadLocalRequest().getSession().setAttribute("user", user);
    }

    @Override
    public void logout() {
        getThreadLocalRequest().getSession().invalidate();
    }

    private Users getUsers() {
        return (Users) getThreadLocalRequest().getSession().getServletContext().getAttribute("users");

    }
}
