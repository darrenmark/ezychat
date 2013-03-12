package com.darren.webchat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class WebChat implements EntryPoint {

	@Override
	public void onModuleLoad() {
		WebChatFactory chatFactory = new ConcreteWebChatFactory();
		chatFactory.getMainView().go(RootPanel.get());
        chatFactory.getLoginPresenter().go();
		
	}

}
