package com.darren.webchat.client;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.UserModel;

import java.util.HashMap;
import java.util.Map;

/**
 */

public interface ChatWindowPresenter extends WebChatEventBus.OnMessageReceivedEventListener, WebChatEventBus.OnLogoutEventListener {

    void showChatWindow(UserModel userModel);

    void hideChatWindow(UserModel userModel);

    void sendMessage(String toUser, String message);

    String getUserName();
}
