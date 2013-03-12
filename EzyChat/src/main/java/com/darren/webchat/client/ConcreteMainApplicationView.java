package com.darren.webchat.client;

import com.darren.webchat.client.model.UserModel;
import com.darren.webchat.client.resources.ChatImages;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.*;
import com.sencha.gxt.widget.core.client.tree.Tree;

import java.util.Arrays;

/**
 */
public class ConcreteMainApplicationView implements MainApplicationView {
    private WebChatFactory webChatFactory;
    private ContentPanel mainContentPanel;
    private NorthSouthContainer northSouthContainer;
    private HorizontalLayoutContainer horizontalLayoutContainer;
    private AccordionLayoutContainer accordionLayoutContainer;
    private AccordionLayoutContainer.AccordionLayoutAppearance appearance;
    private TreeStore<UserModel> store;
    private Tree<UserModel, String> tree;
    private ContentPanel onlineUsersContentPanel;
    private ContentPanel offlineUsersContentPanel;
    private MenuBar menuBar;
    private Menu chatMenu;
    private Menu contactsMenu;
    private MenuItem logoutMenuItem;
    private MenuItem addAContactMenuItem;
    private IconProvider<UserModel> iconProvider;

    public ConcreteMainApplicationView(WebChatFactory webChatFactory) {
        this.webChatFactory = webChatFactory;
    }

    @Override
    public Widget asWidget() {
        return getHorizontalLayoutContainer();
    }



    private HorizontalLayoutContainer getHorizontalLayoutContainer() {
        if (horizontalLayoutContainer == null) {
            horizontalLayoutContainer = new HorizontalLayoutContainer();
            horizontalLayoutContainer.add(getNorthSouthContainer());
        }
        return horizontalLayoutContainer;
    }

    private NorthSouthContainer getNorthSouthContainer() {
        if (northSouthContainer == null) {
            northSouthContainer = new NorthSouthContainer();
            northSouthContainer.setNorthWidget(getMenuBar());
            northSouthContainer.setSouthWidget(getAccordionLayoutContainer());
        }
        return northSouthContainer;
    }

    private AccordionLayoutContainer getAccordionLayoutContainer() {
        if (accordionLayoutContainer == null) {
            accordionLayoutContainer = new AccordionLayoutContainer();
            accordionLayoutContainer.setExpandMode(AccordionLayoutContainer.ExpandMode.SINGLE_FILL);
            accordionLayoutContainer.setWidth(200);
            accordionLayoutContainer.setHeight(300);
            accordionLayoutContainer.add(getOnlineUsersContentPanel());
            accordionLayoutContainer.add(getOfflineUsersContentPanel());
            accordionLayoutContainer.setActiveWidget(getOnlineUsersContentPanel());
        }
        return accordionLayoutContainer;
    }

    private ContentPanel getOnlineUsersContentPanel() {
        if (onlineUsersContentPanel == null) {
            onlineUsersContentPanel = createContentPanel("Online Users");
            onlineUsersContentPanel.add(getTree());
        }
        return onlineUsersContentPanel;
    }

    private ContentPanel getOfflineUsersContentPanel() {
        if (offlineUsersContentPanel == null) {
            offlineUsersContentPanel = createContentPanel("Offline Users");
        }
        return offlineUsersContentPanel;
    }

    private ContentPanel createContentPanel(String headingText) {
        ContentPanel contentPanel = new ContentPanel(getAppearance());
        contentPanel.setHeadingText(headingText);
        return contentPanel;
    }


    private AccordionLayoutContainer.AccordionLayoutAppearance getAppearance() {
        if (appearance == null) {
            appearance = GWT.<AccordionLayoutContainer.AccordionLayoutAppearance>create(AccordionLayoutContainer.AccordionLayoutAppearance.class);
        }
        return appearance;
    }

    @Override
    public void addNewActiveUser(String username) {
        getStore().add(new UserModel(username));
        getStore().commitChanges();
    }

    @Override
    public void removeUser(String username) {
        UserModel userModel = getStore().findModelWithKey(username);
        getStore().remove(userModel);
        getStore().commitChanges();
        Info.display("Logged off", "User " + username + " logged of.");
    }

    @Override
    public void removeAllUsers() {
        getStore().clear();
        getStore().commitChanges();
    }

    private TreeStore<UserModel> getStore() {
        if (store == null) {
            store = new TreeStore<UserModel>(new ModelKeyProvider<UserModel>() {
                @Override
                public String getKey(UserModel item) {
                    return item.getName();
                }
            });
        }
        return store;
    }

    private Tree<UserModel, String> getTree() {
        if (tree == null) {
            tree = new Tree<UserModel, String>(getStore(), new ValueProvider<UserModel, String>() {
                @Override
                public String getValue(UserModel object) {
                    return object.getName();
                }

                @Override
                public void setValue(UserModel object, String value) {
                    object.setName(value);
                }

                @Override
                public String getPath() {
                    return "name";
                }
            });
            tree.setIconProvider(getIconProvider());
            tree.setCell(getTreeCell());
        }
        return tree;
    }

    private IconProvider<UserModel> getIconProvider() {
        if (iconProvider == null) {
            iconProvider = new IconProvider<UserModel>() {
                @Override
                public ImageResource getIcon(UserModel model) {
                    return ChatImages.INSTANCE.user();
                }
            };
        }
        return iconProvider;
    }

    private MenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new MenuBar();
            menuBar.add(new MenuBarItem("Chat", getChatMenu()));
            menuBar.add(new MenuBarItem("Contacts", getContactsMenu()));
        }
        return menuBar;
    }

    private Menu getChatMenu() {
        if (chatMenu == null) {
            chatMenu = new Menu();
            chatMenu.add(getLogoutMenuItem());
            chatMenu.addSelectionHandler(getSelectionHandler());
        }
        return chatMenu;
    }

    private Menu getContactsMenu() {
        if (contactsMenu == null) {
            contactsMenu = new Menu();
            contactsMenu.add(getAddAContactMenuItem());
        }
        return contactsMenu;
    }

    private MenuItem getLogoutMenuItem() {
        if (logoutMenuItem == null) {
            logoutMenuItem = new MenuItem("Logout");
        }
        return logoutMenuItem;
    }

    private MenuItem getAddAContactMenuItem() {
        if (addAContactMenuItem == null) {
            addAContactMenuItem = new MenuItem("Add a Contact");
        }
        return addAContactMenuItem;
    }

    private SelectionHandler<Item> getSelectionHandler() {
        return new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> itemSelectionEvent) {
                if (((MenuItem) itemSelectionEvent.getSelectedItem()).getText().equalsIgnoreCase("Logout")) {
                    webChatFactory.getWebChatService().logout(new Callback() {
                        @Override
                        public void onSuccess(Object o) {
                            webChatFactory.getWebChatEventBus().fireLogoutEvent();
                        }
                    });
                }
            }
        };
    }

    Cell<String> getTreeCell() {
        return new SimpleSafeHtmlCell<String>(SimpleSafeHtmlRenderer.getInstance(), "click") {
            @Override
            public void onBrowserEvent(Cell.Context context, Element parent, String value, NativeEvent event,
                                       ValueUpdater<String> valueUpdater) {
                super.onBrowserEvent(context, parent, value, event, valueUpdater);
                if ("click".equals(event.getType())) {
                    webChatFactory.getChatWindowPresenter().showChatWindow(new UserModel(value));
                }
            }
        };
    }
}
