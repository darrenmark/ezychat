package com.darren.webchat.client;

import com.darren.webchat.client.model.Message;
import com.darren.webchat.client.model.MessageType;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

import java.util.Iterator;

public class ConcreteLoginView implements LoginView {
    private WebChatFactory chatFactory;
    TextField login;
    TextField password;
    private FramedPanel formPanel;
    private CenterLayoutContainer centerLayoutContainer;

    public ConcreteLoginView(WebChatFactory chatFactory) {
        this.chatFactory = chatFactory;
    }


    @Override
    public Widget asWidget() {
        return getCenterLayoutContainer();
    }

    private CenterLayoutContainer getCenterLayoutContainer() {
        if(centerLayoutContainer == null) {
            centerLayoutContainer = new CenterLayoutContainer ();
            centerLayoutContainer.setStyleName("");
            centerLayoutContainer.add(getForm());
        }
        return centerLayoutContainer;
    }

    private FramedPanel getForm() {
        if (formPanel == null) {
            formPanel = new FramedPanel();
            formPanel.setHeadingText("Login");
            formPanel.setWidth(300);
            formPanel.setHeight(150);
            formPanel.setBodyStyle("background: none; padding: 5px");
            VerticalLayoutContainer container = new VerticalLayoutContainer();
            formPanel.add(container);
            container.add(getField(getLoginField(), "Login ID"), new VerticalLayoutContainer.VerticalLayoutData(1, -1));
            container.add(getField(getPasswordField(), "Password"), new VerticalLayoutContainer.VerticalLayoutData(1, -1));
            formPanel.addButton(getLoginButton());
        }
        return formPanel;

    }

    private FieldLabel getField(TextField field, String label) {
        field.setAllowBlank(false);
        return new FieldLabel(field, label);
    }


    private TextField getLoginField() {
        if (login == null) {
            login = new TextField();
            login.setAllowBlank(false);
        }
        return login;
    }

    private TextField getPasswordField() {
        if (password == null) {
            password = new TextField();
            password.setAllowBlank(false);
        }
        return password;
    }

    private TextButton getLoginButton() {
        TextButton loginButton = new TextButton("Login");
        loginButton.addSelectHandler(onLoginClickedHandler());
        return loginButton;
    }

    private SelectEvent.SelectHandler onLoginClickedHandler() {
        return new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                chatFactory.getWebChatService().login(getLoginField().getValue(), getPasswordField().getValue(), new Callback<Message>() {
                    @Override
                    public void onSuccess(Message message) {
                        if (message.getMessageType() == MessageType.LOGIN_SUCCESSFUL) {
                            chatFactory.getWebChatEventBus().fireLoginEvent(login.getValue());
                        } else {
                            Window.alert(message.getData());
                        }
                    }
                });
            }
        };
    }
}
