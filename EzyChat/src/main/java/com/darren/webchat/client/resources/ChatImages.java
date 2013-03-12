package com.darren.webchat.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 */
public interface ChatImages extends ClientBundle {

    public ChatImages INSTANCE = GWT.create(ChatImages.class);

    @Source("user.png")
    ImageResource user();
}
