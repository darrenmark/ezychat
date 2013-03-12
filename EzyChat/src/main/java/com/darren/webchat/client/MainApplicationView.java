package com.darren.webchat.client;

import com.google.gwt.user.client.ui.IsWidget;

/**
 */
public interface MainApplicationView extends IsWidget {

    void addNewActiveUser(String username);

    void removeUser(String username);
    
    void removeAllUsers();

}
