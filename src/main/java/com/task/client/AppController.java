package com.task.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.task.client.event.EditWindowEvents.CancelEditWindowEvent;
import com.task.client.event.EditWindowEvents.SaveBookEvent;
import com.task.client.event.TableEvents.AddBookEvent;
import com.task.client.event.TableEvents.DeleteBookEvent;
import com.task.client.event.TableEvents.EditBookEvent;
import com.task.client.presenter.EditCardPresenter;
import com.task.client.presenter.Presenter;
import com.task.client.presenter.TablePresenter;
import com.task.client.view.EditWindow.EditCard;
import com.task.client.view.Table.Table;


public class AppController implements ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private final BookServiceAsync rpcService;
    private HasWidgets container;

    public AppController(BookServiceAsync rpcService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddBookEvent.TYPE,
                addBookEvent -> doAddNewContact());

        eventBus.addHandler(EditBookEvent.TYPE,
                editBookEvent -> doEditBook(editBookEvent.getId()));

        eventBus.addHandler(DeleteBookEvent.TYPE, this::doDeleteBook);

        eventBus.addHandler(CancelEditWindowEvent.TYPE, editWindowEvent -> doCancelEvent());
        eventBus.addHandler(SaveBookEvent.TYPE, saveBookEvent -> doSaveBookEvent());
    }

    private void doDeleteBook(DeleteBookEvent event)
    {
        History.newItem("list");
        rpcService.delete(event.getId(), new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("cannot delete book with id "+event.getId());
            }

            @Override
            public void onSuccess(Boolean aBoolean) {
            }
        });
        Presenter presenter = new TablePresenter(rpcService,eventBus, new Table());
        presenter.go(container);
    }
    private void doAddNewContact() {
        History.newItem("add");
    }

    private void doEditBook(int id) {
        History.newItem("edit", false);
        Presenter presenter = new EditCardPresenter(rpcService, eventBus, new EditCard(), id);
        presenter.go(container);
    }

    private void doCancelEvent() {
        History.newItem("list");
    }

    private void doSaveBookEvent() {
        History.newItem("list");
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("list");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            switch (token) {
                case "list":
                    presenter = new TablePresenter(rpcService, eventBus, new Table());
                    break;
                case "add":
                    presenter = new EditCardPresenter(rpcService, eventBus, new EditCard());
                    break;
                case "edit":
                    presenter = new EditCardPresenter(rpcService, eventBus, new EditCard());
                    break;
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
