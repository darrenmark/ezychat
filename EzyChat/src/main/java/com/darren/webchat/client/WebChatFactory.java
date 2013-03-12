package com.darren.webchat.client;

import com.darren.webchat.client.model.UserModel;
import com.darren.webchat.client.rpc.WebChatServiceAsync;

public interface WebChatFactory {

	WebChatEventBus getWebChatEventBus();

	LoginPresenter getLoginPresenter();
	
	LoginView getLoginView();

    WebChatServiceAsync getWebChatService();

    MainView getMainView();

    MainApplicationPresenter getMainApplicationPresenter();

    MainApplicationView getMainApplicationView();

    Receiver getReceiver();

    ChatWindowPresenter getChatWindowPresenter();

    ChatWindowView createNewChatWindowView(UserModel userModel);
}
