package com.darren.webchat.client;

import com.darren.webchat.client.model.UserModel;
import com.darren.webchat.client.rpc.WebChatService;
import com.darren.webchat.client.rpc.WebChatServiceAsync;
import com.google.gwt.core.client.GWT;

public class ConcreteWebChatFactory implements WebChatFactory {
	private LoginPresenter chatPresenter;
	private LoginView chatView;
	private WebChatEventBus webChatEventBus;
    private WebChatServiceAsync webChatService;
    private MainView mainView;
    private MainApplicationPresenter mainApplicationPresenter;
    private MainApplicationView mainApplicationView;
    private Receiver receiver;
    private ChatWindowPresenter chatWindowPresenter;

    public LoginPresenter getLoginPresenter() {
		if(chatPresenter == null) {
			chatPresenter = new ConcreteLoginPresenter(this);
		}
		return chatPresenter;
	}

	public LoginView getLoginView() {
		if(chatView == null) {
			chatView = new ConcreteLoginView(this);
		}
		return chatView;
	}

	public WebChatEventBus getWebChatEventBus() {
		if(webChatEventBus == null) {
			webChatEventBus = new ConcreteWebChatEventBus();
            webChatEventBus.addLoginEventListener(getMainApplicationPresenter());
            webChatEventBus.addMessageReceivedEventListener(getMainApplicationPresenter());
            webChatEventBus.addMessageReceivedEventListener(getChatWindowPresenter());
            webChatEventBus.addLogoutEventListener(getChatWindowPresenter());
		}
		return webChatEventBus;
	}

    @Override
    public WebChatServiceAsync getWebChatService() {
        if(webChatService == null) {
            webChatService = GWT.create(WebChatService.class);
        }
        return webChatService;
    }

    @Override
    public MainView getMainView() {
        if(mainView == null) {
            mainView = new ConcreteMainView();
        }
        return mainView;
    }

    @Override
    public MainApplicationPresenter getMainApplicationPresenter() {
        if(mainApplicationPresenter == null) {
            mainApplicationPresenter = new ConcreteMainApplicationPresenter(this);
        }
        return mainApplicationPresenter;
    }

    @Override
    public MainApplicationView getMainApplicationView() {
        if(mainApplicationView == null) {
            mainApplicationView = new ConcreteMainApplicationView(this);
        }
        return mainApplicationView;
    }

    @Override
    public Receiver getReceiver() {
        if(receiver == null) {
            receiver = new ConcreteReceiver(this);
        }
        return receiver;
    }

    @Override
    public ChatWindowPresenter getChatWindowPresenter() {
        if(chatWindowPresenter == null) {
            chatWindowPresenter = new ConcreteChatWindowPresenter(this);
        }
        return chatWindowPresenter;
    }

    @Override
    public ChatWindowView createNewChatWindowView(UserModel userModel) {
        return new ConcreteChatWindowView(userModel, getChatWindowPresenter());
    }
}
