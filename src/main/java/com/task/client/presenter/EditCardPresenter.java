package com.task.client.presenter;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.task.client.BookServiceAsync;
import com.task.client.event.EditWindowEvents.CancelEditWindowEvent;
import com.task.client.event.EditWindowEvents.SaveBookEvent;
import com.task.server.domain.Book;

import java.util.Date;

public class EditCardPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getBookName();

        HasValue<String> getDescription();

        HasValue<Date> getDate();

        HasValue<Double> getPrice();

        Widget asWidget();
    }

    private Book book;
    private final BookServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditCardPresenter(BookServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.book = new Book();
        this.display = display;
        bind();
    }

    public EditCardPresenter(BookServiceAsync rpcService, HandlerManager eventBus, Display display, int id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getBook(id, new AsyncCallback<Book>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }

            @Override
            public void onSuccess(Book result) {
                book = result;
                EditCardPresenter.this.display.getBookName().setValue(book.getBookName());
                EditCardPresenter.this.display.getDescription().setValue(book.getDescription());
                EditCardPresenter.this.display.getDate().setValue(book.getPublishedDate());
                EditCardPresenter.this.display.getPrice().setValue(book.getPrice());
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(event -> doSave());
        this.display.getCancelButton().addClickHandler(event -> eventBus.fireEvent(new CancelEditWindowEvent()));
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }

    private void doSave() {
        book.setBookName(display.getBookName().getValue());
        book.setDescription(display.getDescription().getValue());
        book.setPublishedDate(display.getDate().getValue());
        book.setPrice(display.getPrice().getValue());

        rpcService.save(book, new AsyncCallback<Book>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Error updating contact");
            }

            @Override
            public void onSuccess(Book book) {
                eventBus.fireEvent(new SaveBookEvent(book));
            }
        });

    }
}
