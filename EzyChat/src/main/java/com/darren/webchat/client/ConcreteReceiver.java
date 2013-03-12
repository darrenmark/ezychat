package com.darren.webchat.client;

import com.darren.webchat.client.model.Message;

/**
 */
public class ConcreteReceiver implements Receiver, WebChatEventBus.OnLogoutEventListener {
    private boolean started = false;
    private WebChatFactory webChatFactory;

    public ConcreteReceiver(WebChatFactory webChatFactory) {
        this.webChatFactory = webChatFactory;
        webChatFactory.getWebChatEventBus().addLogoutEventListener(this);
    }

    @Override
    public void start() {
        started = true;
        webChatFactory.getWebChatService().receiveMessage(new Callback<Message>() {
            @Override
            public void onSuccess(Message message) {
                webChatFactory.getWebChatEventBus().fireMessageReceivedEvent(message);
                if (started) {
                    start();
                }
            }
        });
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void onLogout() {
        stop();
    }
}
