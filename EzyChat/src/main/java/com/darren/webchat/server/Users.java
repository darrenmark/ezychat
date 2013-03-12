package com.darren.webchat.server;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;

import java.util.Map;
import java.util.WeakHashMap;

/**
 */
public class Users {
    private Map<User, UserSession> userSessionMap = new WeakHashMap<User, UserSession>();

    public Message addNewUser(User user) {
        if(userSessionMap.containsKey(user)) {
            return Message.create(MessageType.LOGIN_FAILED,"User has already logged in.");
        }
        notifyNewUserLoggedIn(user);
        userSessionMap.put(user, new UserSession());
        updateNewLoginWithAllActiveUsers(user);
        return Message.create(MessageType.LOGIN_SUCCESSFUL);
    }


    public void userLoggedOut(User user) {
        userSessionMap.remove(user);
        notifyUserLoggedOut(user);
   }

    public void sendMessage(Message message) {
        UserSession userSession = userSessionMap.get(new User(message.getSendTo()));
        if(userSession != null) {
            userSession.sendMessage(message);
        }
    }

    public Message getMessage(User user) {
        UserSession userSession = userSessionMap.get(user);
        if(userSession == null) {
            return Message.create(MessageType.SESSION_TIMED_OUT);
        }
        return userSession.getMessage();

    }

    public void notifyUsersWebChatShuttingDown() {
        for(Map.Entry<User, UserSession> entry: userSessionMap.entrySet()) {
            entry.getValue().sendMessage(Message.create(MessageType.SYSTEM, "WebChat is shutting down."));
        }
    }

    private void notifyNewUserLoggedIn(User user) {
        for(Map.Entry<User, UserSession> entry: userSessionMap.entrySet()) {
            entry.getValue().newUserAdded(user);
        }
    }

    private void notifyUserLoggedOut(User user) {
        for(Map.Entry<User, UserSession> entry: userSessionMap.entrySet()) {
            entry.getValue().userLoggedOut(user);
        }
    }

    private void updateNewLoginWithAllActiveUsers(User user) {
        for(Map.Entry<User, UserSession> entry: userSessionMap.entrySet()) {
            if(!entry.getKey().equals(user)) {
                userSessionMap.get(user).sendMessage(createMessage(MessageType.NEW_USER_LOGGED_IN, entry.getKey().getName()));
            }
        }
    }

    private Message createMessage(MessageType messageType, String from) {
        Message message = Message.create(messageType);
        message.setFrom(from);
        return message;
    }


}
