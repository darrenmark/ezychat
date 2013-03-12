package com.darren.webchat.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 */
public enum MessageType implements IsSerializable {
    SESSION_TIMED_OUT,
    INTERNAL_ERROR,
    CHAT_MESSAGE,
    NEW_USER_LOGGED_IN,
    USER_LOGGED_OUT,
    NO_MESSAGE,
    LOGIN_SUCCESSFUL,
    LOGIN_FAILED,
    SYSTEM


}
