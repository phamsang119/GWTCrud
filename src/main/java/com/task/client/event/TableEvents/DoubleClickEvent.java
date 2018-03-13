package com.task.client.event.TableEvents;

import com.google.gwt.event.shared.GwtEvent;

public class DoubleClickEvent extends GwtEvent<DoubleClickEventHandler> {

    private final String description;

    private final String author;

    public DoubleClickEvent(String description, String author) {
        this.description = description;
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public static Type<DoubleClickEventHandler> TYPE = new Type<>();

    public Type<DoubleClickEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(DoubleClickEventHandler handler) {
        handler.onDoubleClick(this);
    }
}
