package com.task.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.task.client.ui.Table.Table;

/**
 * Entry point
 */
public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new Table());
    }
}
