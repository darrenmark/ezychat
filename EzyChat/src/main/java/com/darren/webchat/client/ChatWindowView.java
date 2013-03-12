package com.darren.webchat.client;

import com.darren.webchat.client.model.UserModel;
import com.google.gwt.user.client.ui.IsWidget;

/**
 */
public interface ChatWindowView extends IsWidget {

    void showChatWindow();

    void hideChatWindow();

    void showMessage(String message);

    UserModel getUser();

}
