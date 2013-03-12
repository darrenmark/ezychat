package com.darren.webchat.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

import java.util.Iterator;

/**
 */
public class ConcreteMainView implements MainView {

    private Viewport viewport;
    private ContentPanel contentPanel;

    @Override
    public void go(HasWidgets widgets) {
        widgets.add(asWidget());
    }

    @Override
    public void add(Widget widget) {
        getContentPanel().add(widget);
        getContentPanel().forceLayout();
    }

    @Override
    public void clear() {
        getContentPanel().clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return getContentPanel().iterator();
    }

    @Override
    public boolean remove(Widget widget) {
        return getContentPanel().remove(widget);
    }

    @Override
    public Widget asWidget() {
        getViewport().add(getContentPanel());
        return getViewport();
    }

    private Viewport getViewport() {
        if(viewport == null) {
            viewport = new Viewport();
        }
        return viewport;
    }

    private ContentPanel getContentPanel() {
        if(contentPanel == null) {
            contentPanel = new ContentPanel();
            contentPanel.setHeaderVisible(false);
            contentPanel.setBodyStyle("backgroundColor:white");
        }
        return contentPanel;
    }

}
