package com.darren.webchat.client;


public class ConcreteLoginPresenter implements LoginPresenter, WebChatEventBus.OnLogoutEventListener{
	WebChatFactory chatFactory;
	
	public ConcreteLoginPresenter(WebChatFactory chatFactory) {
		this.chatFactory = chatFactory;
        this.chatFactory.getWebChatEventBus().addLogoutEventListener(this);
	}


    @Override
    public void go() {
        chatFactory.getMainView().clear();
        chatFactory.getMainView().add(chatFactory.getLoginView().asWidget());
    }

    @Override
    public void onLogout() {
        go();
    }
}
