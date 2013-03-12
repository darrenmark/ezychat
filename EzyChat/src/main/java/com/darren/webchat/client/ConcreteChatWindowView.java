package com.darren.webchat.client;

import com.darren.webchat.client.model.UserModel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 */
public class ConcreteChatWindowView implements ChatWindowView {
    Dialog chatDialog;
    private UserModel userModel;
    private BorderLayoutContainer borderLayoutContainer;
    private TextArea messageBoard;
    private TextArea sendTextArea;
    private TextButton sendButton;
    private ChatWindowPresenter chatWindowPresenter;

    public ConcreteChatWindowView(UserModel userModel, ChatWindowPresenter chatWindowPresenter) {
        this.userModel = userModel;
        this.chatWindowPresenter = chatWindowPresenter;
    }

    @Override
    public void showChatWindow() {
        getChatDialog().show();

    }

    @Override
    public void hideChatWindow() {
        getChatDialog().hide();
    }

    @Override
    public void showMessage(String message) {
        addMessageToBoard(message);
    }

    @Override
    public Widget asWidget() {
        return getChatDialog();
    }

    @Override
    public UserModel getUser() {
        return userModel;
    }

    private Dialog getChatDialog() {
        if(chatDialog == null) {
            chatDialog = new Dialog();
            chatDialog.setHeadingText(userModel.getName());
            chatDialog.add(getBorderLayoutContainer());
            chatDialog.getButtonBar().clear();
            chatDialog.addButton(getSendButton());
            chatDialog.setWidth(300);
            chatDialog.setHeight(200);
        }
        return chatDialog;
    }


    private BorderLayoutContainer getBorderLayoutContainer() {
        if(borderLayoutContainer == null) {
            borderLayoutContainer = new BorderLayoutContainer();
            BorderLayoutContainer.BorderLayoutData northData = new BorderLayoutContainer.BorderLayoutData(0.75);
            northData.setMargins(new Margins(0, 0, 5, 0));
            borderLayoutContainer.setNorthWidget(getMessageBoard(), northData);
            borderLayoutContainer.setSouthWidget(getSendTextArea(), new BorderLayoutContainer.BorderLayoutData(0.25));

        }
        return borderLayoutContainer;
    }

    private TextArea getMessageBoard() {
        if(messageBoard == null) {
            messageBoard = new TextArea();
            messageBoard.setReadOnly(true);
        }
        return messageBoard;
    }

    private TextArea getSendTextArea() {
        if(sendTextArea == null) {
            sendTextArea = new TextArea();
        }
        return sendTextArea;
    }

    private TextButton getSendButton() {
        if(sendButton == null) {
            sendButton = new TextButton("Send");
            sendButton.addSelectHandler(getSendSelectHandler());
        }
        return sendButton;
    }

    private SelectEvent.SelectHandler getSendSelectHandler() {
        return  new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                String messageSent = getSendTextArea().getCurrentValue();
                chatWindowPresenter.sendMessage(userModel.getName(), messageSent);
                getSendTextArea().clear();
                addMessageToBoard(chatWindowPresenter.getUserName() + ": " + messageSent);
            }
        };
    }

    private void addMessageToBoard(String message) {
        String currentText = getMessageBoard().getText() + message + "\n" ;
        getMessageBoard().setText(currentText);
        getMessageBoard().setCursorPos(getMessageBoard().getText().length()-1);

    }
}
