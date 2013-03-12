package com.darren.webchat.client.rpc;

import com.darren.webchat.client.model.Message;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 */
public interface WebChatServiceAsync {

    void login(String username, String password, AsyncCallback<Message> callback);

    void receiveMessage(AsyncCallback<Message> callback);
    
   	void sendMessage(Message message, AsyncCallback callback);

    void logout(AsyncCallback callback);

}
