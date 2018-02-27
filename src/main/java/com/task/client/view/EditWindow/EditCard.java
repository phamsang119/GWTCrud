package com.task.client.view.EditWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.task.client.presenter.EditCardPresenter;
import com.task.client.view.MyDateBox;

import java.util.Date;


public class EditCard extends Composite implements EditCardPresenter.Display {


    interface EditCardUiBinder extends UiBinder<HTMLPanel, EditCard> {
    }

    private static EditCardUiBinder ourUiBinder = GWT.create(EditCardUiBinder.class);

    @UiField
    Button save;
    @UiField
    Button cancel;
    @UiField
    TextBox bookNameBox;
    @UiField
    TextArea descriptionArea;
    @UiField
    MyDateBox pubDateBox;
    @UiField
    DoubleBox priceBox;

    public EditCard() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


    @Override
    public HasClickHandlers getSaveButton() {
        return save;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancel;
    }

    @Override
    public HasValue<String> getBookName() {
        return bookNameBox;
    }

    @Override
    public HasValue<String> getDescription() {
        return descriptionArea;
    }

    @Override
    public HasValue<Date> getDate() {
        return pubDateBox;
    }

    @Override
    public HasValue<Double> getPrice() {
        return priceBox;
    }
}