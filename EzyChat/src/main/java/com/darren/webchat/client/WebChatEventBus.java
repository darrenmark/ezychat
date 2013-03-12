package com.darren.webchat.client;

import java.util.List;

import com.darren.webchat.client.model.Message;

public interface WebChatEventBus {
	public interface WebAppLoadedEventListener {
		void onLoad();
	}

	public interface OnLoginEventListener {
		void onLogin(String userName);
	}
	
	public interface OnLogoutEventListener {
		void onLogout();
	}
	
	public interface OnMessageReceivedEventListener {
		void onMessageReceived(Message messsages);
	}


	public interface OnMessageSentEventListener {
		void onMessageSend(Message messsage);
	}


    void addWebAppLoadedEventListener(WebAppLoadedEventListener appLoadedEventListener);

    void addLoginEventListener(OnLoginEventListener eventListener);

    void addLogoutEventListener(OnLogoutEventListener eventListener);

    void addMessageReceivedEventListener(OnMessageReceivedEventListener eventListener);

    void addMessageSentEventListener(OnMessageSentEventListener eventListener);

    void fireWebAppLoaded();

    void fireLoginEvent(String userName);

    void fireMessageReceivedEvent(Message message);

    void fireMessageSentEvent(Message message);

    void fireLogoutEvent();

}
