package com.darren.webchat.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 */
public abstract class Callback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(Throwable throwable) {
        Window.alert(throwable.getMessage());
    }
}
