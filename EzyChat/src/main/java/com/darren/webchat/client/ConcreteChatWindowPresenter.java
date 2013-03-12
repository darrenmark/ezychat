package com.darren.webchat.client;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;
import com.darren.webchat.client.model.UserModel;
import com.google.gwt.user.client.Window;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class ConcreteChatWindowPresenter implements ChatWindowPresenter {
     private WebChatFactory webChatFactory;
    private Map<UserModel, ChatWindowView> userViewMap = new HashMap<UserModel, ChatWindowView>();

    public ConcreteChatWindowPresenter(WebChatFactory webChatFactory) {
        this.webChatFactory = webChatFactory;
    }

    @Override
    public void showChatWindow(UserModel userModel) {
        if(!userViewMap.containsKey(userModel)) {
            userViewMap.put(userModel, webChatFactory.createNewChatWindowView(userModel));
        }
        userViewMap.get(userModel).showChatWindow();
    }

    @Override
    public void hideChatWindow(UserModel userModel) {
        if(userViewMap.containsKey(userModel)) {
            userViewMap.get(userModel).hideChatWindow();
        }
    }

    @Override
    public void sendMessage(String toUser, String text) {
        Message message = new Message();
        message.setSendTo(toUser);
        message.setFrom(webChatFactory.getMainApplicationPresenter().getUserName());
        message.setData(text);
        message.setMessageType(MessageType.CHAT_MESSAGE);
        webChatFactory.getWebChatService().sendMessage(message, new Callback() {
            @Override
            public void onSuccess(Object o) {
                //Do nothing
            }
        });
    }

    @Override
    public void onMessageReceived(Message message) {
        if(message.getMessageType() == MessageType.CHAT_MESSAGE) {
            UserModel userModel = new UserModel(message.getFrom());
            showChatWindow(userModel);
            userViewMap.get(userModel).showMessage(message.getFrom() +": " + message.getData());
        }
        if(message.getMessageType() == MessageType.USER_LOGGED_OUT) {
            hideChatWindow(new UserModel(message.getFrom()));
        }
    }

    @Override
    public void onLogout() {
        for(ChatWindowView chatWindowView: userViewMap.values()) {
            chatWindowView.hideChatWindow();
        }
        userViewMap.clear();
    }

    @Override
    public String getUserName() {
        return webChatFactory.getMainApplicationPresenter().getUserName();
    }
}
