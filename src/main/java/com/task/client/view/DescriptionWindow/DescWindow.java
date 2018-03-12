package com.task.client.view.DescriptionWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.task.client.presenter.DescWindowPresenter;

public class DescWindow extends Composite implements DescWindowPresenter.Display {

    interface DescWindowUiBinder extends UiBinder<HTMLPanel, DescWindow> {
    }

    private static DescWindowUiBinder ourUiBinder = GWT.create(DescWindowUiBinder.class);

    @UiField
    ScrollPanel scrollPanel;

    @UiField
    TextBox textBox;

    @UiField
    Button cancel;

    public DescWindow() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public TextBox getTextBox() {
        return textBox;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancel;
    }
}