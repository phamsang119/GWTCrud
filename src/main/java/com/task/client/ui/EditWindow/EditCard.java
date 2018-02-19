package com.task.client.ui.EditWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.task.client.BookServiceAsync;
import com.task.client.ui.MyDateBox;
import com.task.client.ui.Service.Helper;
import com.task.client.ui.Table.Table;
import com.task.server.domain.Book;

import java.util.Date;


public class EditCard extends Composite {
    interface EditCardUiBinder extends UiBinder<HTMLPanel, EditCard> {
    }

    private static EditCardUiBinder ourUiBinder = GWT.create(EditCardUiBinder.class);

    private final BookServiceAsync bookService = Helper.getBookService();

    private final ListDataProvider<Book> dataProvider = Table.getDataProvider();

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

    private int id;
    private int selectedRow;


    public EditCard() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("save")
    void initSaveButton(ClickEvent e) {
        save.addClickHandler(clickEvent ->
        {
            Book book;
            // если добавляем новую книгу - присваиваем новый id
            if (id == -1) id = Helper.getIncrementId();
            //если редактируем - используем старый
            book = new Book(id, bookNameBox.getValue(), descriptionArea.getValue(),
                    pubDateBox.getValue(), priceBox.getValue());

            if (dataProvider.getList().contains(book)) {

                bookService.save(book, new AsyncCallback<Book>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("book wasn't edited");
                    }

                    @Override
                    public void onSuccess(Book book) {
                        dataProvider.getList().set(selectedRow, book);
                        Window.alert("edited");
                    }
                });
            } else {
                book.setId(Helper.getIncrementId());
                bookService.save(book, new AsyncCallback<Book>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("book wasn't saved");
                    }

                    @Override
                    public void onSuccess(Book book) {
                        dataProvider.getList().add(book);
                        Window.alert("saved");
                    }
                });
            }

            this.setVisible(false);
        });

    }

    @UiHandler("cancel")
    void initCancelButton(ClickEvent e) {
        cancel.addClickHandler(clickEvent -> this.setVisible(false));
    }


    /**
     * setters
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        bookNameBox.setText(bookName);
    }

    public void setDescription(String description) {
        descriptionArea.setText(description);
    }


    public void setPubDate(Date pubDate) {
        pubDateBox.setValue(pubDate);
    }

    public void setPrice(double price) {
        priceBox.setValue(price);
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
}