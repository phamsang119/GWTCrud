package com.task.client.event.TableEvents;

import com.google.gwt.event.shared.GwtEvent;

public class DoubleClickEvent extends GwtEvent<DoubleClickEventHandler> {

    private final String description;

    public DoubleClickEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Type<DoubleClickEventHandler> TYPE = new Type<>();

    public Type<DoubleClickEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(DoubleClickEventHandler handler) {
        handler.onDoubleClick(this);
    }
}
