package com.task.client.event.TableEvents;


import com.google.gwt.event.shared.GwtEvent;

public class AddBookEvent extends GwtEvent<AddBookEventHandler> {

    public static Type<AddBookEventHandler> TYPE = new Type<>();

    @Override
    public Type<AddBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddBookEventHandler addBookEventHandler) {
        addBookEventHandler.onAddBookEvent(this);
    }
}
