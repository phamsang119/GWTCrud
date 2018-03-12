package com.task.client.view.DescriptionWindow;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.task.client.presenter.DescWindowPresenter;

public class DescWindow extends Composite implements DescWindowPresenter.Display {

    interface DescWindowUiBinder extends UiBinder<HTMLPanel, DescWindow> {
    }

    private static DescWindowUiBinder ourUiBinder = GWT.create(DescWindowUiBinder.class);


    @UiField
    TextArea textArea;

    @UiField
    Button cancel;

    public DescWindow() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public TextArea getTextArea() {
        return textArea;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancel;
    }
}