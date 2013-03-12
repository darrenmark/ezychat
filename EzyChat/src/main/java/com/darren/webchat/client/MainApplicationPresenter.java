package com.darren.webchat.client;

/**
 */
public interface MainApplicationPresenter extends WebChatEventBus.OnLoginEventListener, WebChatEventBus.OnMessageReceivedEventListener {

    public String getUserName();
}
