package com.darren.webchat.client;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;

/**
 */
public class ConcreteMainApplicationPresenter implements MainApplicationPresenter {
    WebChatFactory webChatFactory;
    private String userName;

    public ConcreteMainApplicationPresenter(WebChatFactory webChatFactory) {
        this.webChatFactory = webChatFactory;
    }


    @Override
    public void onLogin(String userName) {
        this.userName = userName;
        webChatFactory.getMainView().clear();
        webChatFactory.getMainApplicationView().removeAllUsers();
        webChatFactory.getMainView().add(webChatFactory.getMainApplicationView().asWidget());
        webChatFactory.getReceiver().start();
    }

    @Override
    public void onMessageReceived(Message messsage) {
        if(messsage.getMessageType() == MessageType.NEW_USER_LOGGED_IN) {
            webChatFactory.getMainApplicationView().addNewActiveUser(messsage.getFrom());
        }
        if(messsage.getMessageType() == MessageType.USER_LOGGED_OUT) {
            webChatFactory.getMainApplicationView().removeUser(messsage.getFrom());
        }

    }

    @Override
    public String getUserName() {
        return userName;
    }
}
