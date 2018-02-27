package com.task.client.event.EditWindowEvents;


import com.google.gwt.event.shared.GwtEvent;

public class CancelEditWindowEvent extends GwtEvent<CancelEditWindowEventHandler> {

    public static Type<CancelEditWindowEventHandler> TYPE = new Type<>();

    @Override
    public Type<CancelEditWindowEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CancelEditWindowEventHandler cancelEditWindowEventHandler) {
        cancelEditWindowEventHandler.onCancelEvent(this);
    }
}
