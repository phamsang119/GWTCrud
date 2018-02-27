package com.task.client.event.EditWindowEvents;


import com.google.gwt.event.shared.GwtEvent;
import com.task.server.domain.Book;

public class SaveBookEvent extends GwtEvent<SaveBookEventHandler> {

    public static Type<SaveBookEventHandler> TYPE = new Type<>();

    private final Book book;

    public SaveBookEvent(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public Type<SaveBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SaveBookEventHandler saveBookEventHandler) {
        saveBookEventHandler.onSaveBookEvent(this);
    }
}
