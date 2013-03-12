package com.darren.webchat.client;

import java.util.ArrayList;
import java.util.List;

import com.darren.webchat.client.model.Message;

public class ConcreteWebChatEventBus implements WebChatEventBus {
	
	private List<WebAppLoadedEventListener> webAppLoadedListeners = new ArrayList<WebAppLoadedEventListener>();
	private List<OnLoginEventListener> onLoginEventListeners = new ArrayList<OnLoginEventListener>();
	private List<OnLogoutEventListener> onLogoutEventListeners = new ArrayList<OnLogoutEventListener>();
	private List<OnMessageReceivedEventListener> onMessageReceivedEventListeners = new ArrayList<OnMessageReceivedEventListener>();
	private List<OnMessageSentEventListener> onMessageSentEventListeners = new ArrayList<OnMessageSentEventListener>();
	
	public void addWebAppLoadedEventListener(WebAppLoadedEventListener appLoadedEventListener) {
		webAppLoadedListeners.add(appLoadedEventListener);
	}
	
	public void addLoginEventListener(OnLoginEventListener eventListener) {
		onLoginEventListeners.add(eventListener);
	}

	public void addLogoutEventListener(OnLogoutEventListener eventListener) {
		onLogoutEventListeners.add(eventListener);
	}
	
	public void addMessageReceivedEventListener(OnMessageReceivedEventListener eventListener) {
		onMessageReceivedEventListeners.add(eventListener);
	}
	
	public void addMessageSentEventListener(OnMessageSentEventListener eventListener) {
		onMessageSentEventListeners.add(eventListener);
	}


	public void fireWebAppLoaded() {
		for(WebAppLoadedEventListener listener: webAppLoadedListeners) {
			listener.onLoad();
		}
	}
	
	public void fireLoginEvent(String userName) {
		for(OnLoginEventListener eventListener: onLoginEventListeners) {
			eventListener.onLogin(userName);
		}
	}
	
	public void fireMessageReceivedEvent(Message message) {
		for(OnMessageReceivedEventListener eventListener: onMessageReceivedEventListeners) {
			eventListener.onMessageReceived(message);
		}
	}
	
	public void fireMessageSentEvent(Message message) {
		for(OnMessageSentEventListener eventListener: onMessageSentEventListeners) {
			eventListener.onMessageSend(message);
		}
	}

    @Override
    public void fireLogoutEvent() {
        for(OnLogoutEventListener eventListener: onLogoutEventListeners) {
            eventListener.onLogout();
        }
    }
}
