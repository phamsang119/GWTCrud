package com.task.client.event.DescWindowEvents;

import com.google.gwt.event.shared.GwtEvent;

public class CancelDescEvent extends GwtEvent<CancelDescEventHandler> {
    public static Type<CancelDescEventHandler> TYPE = new Type<>();

    public Type<CancelDescEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(CancelDescEventHandler handler) {
        handler.onCancelDesc(this);
    }
}
