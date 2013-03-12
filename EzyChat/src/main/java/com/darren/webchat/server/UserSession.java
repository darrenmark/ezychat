package com.darren.webchat.server;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 */
public class UserSession  {
    private BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);

    public void newUserAdded(User user) {
        Message message = new Message();
        message.setFrom(user.getName());
        message.setMessageType(MessageType.NEW_USER_LOGGED_IN);
        queue.add(message);
    }

    public void userLoggedOut(User user) {
        Message message = new Message();
        message.setFrom(user.getName());
        message.setMessageType(MessageType.USER_LOGGED_OUT);
        queue.add(message);
    }

    public void sendMessage(Message message) {
        queue.add(message);
    }

    public Message getMessage() {
        try {
            Message message = queue.poll(10, TimeUnit.SECONDS);
            if(message == null) {
                return Message.create(MessageType.NO_MESSAGE);
            } else {
                return message;
            }
        } catch (InterruptedException e) {
            return Message.create(MessageType.INTERNAL_ERROR);
        }
    }
}
