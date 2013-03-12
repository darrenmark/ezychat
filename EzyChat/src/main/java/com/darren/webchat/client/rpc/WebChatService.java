package com.darren.webchat.client.rpc;

import com.darren.webchat.client.model.Message;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 */
@RemoteServiceRelativePath("webChatService")
public interface WebChatService extends RemoteService {

    Message login(String username, String password);

    void sendMessage(Message message);

    Message receiveMessage();

    void logout();
}
