package com.task.client.ui.Table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.task.client.BookServiceAsync;
import com.task.client.ui.EditWindow.EditCard;
import com.task.client.ui.Service.Helper;
import com.task.server.domain.Book;


public class TableHeader extends Composite {
    interface TableHeaderUiBinder extends UiBinder<HTMLPanel, TableHeader> {
    }

    private static TableHeaderUiBinder ourUiBinder = GWT.create(TableHeaderUiBinder.class);

    /**
     * PRC service.
     */
    private final BookServiceAsync bookService = Helper.getBookService();

    /**
     * Data Provider for main table
     */
    private final ListDataProvider<Book> dataProvider = Table.getDataProvider();

    /**
     * Main table
     */
    private final CellTable<Book> table = Table.getMainTable();


    @UiField
    EditCard editCard;

    @UiField
    Button addButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;

    public TableHeader() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    void handleAddClick(ClickEvent e) {
        if (editCard.isVisible()) {
            editCard.setVisible(false);
        } else {
            editCard.setId(-1);
            editCard.setBookName("");
            editCard.setDescription("");
            editCard.setPubDate(null);
            editCard.setPrice(0);
            editCard.setVisible(true);
        }
    }


    @UiHandler("editButton")
    void handleEditClick(ClickEvent e) {
        int row = table.getKeyboardSelectedRow();
        Book book = dataProvider.getList().get(row);
        editCard.setId(book.getId());
        editCard.setBookName(book.getBookName());
        editCard.setDescription(book.getDescription());
        editCard.setPubDate(book.getPublishedDate());
        editCard.setPrice(book.getPrice());

        editCard.setSelectedRow(row);

        editCard.setVisible(true);
    }

    @UiHandler("deleteButton")
    void handleDeleteClick(ClickEvent e) {
        int row = table.getKeyboardSelectedRow();
        Book book = dataProvider.getList().get(row);
        bookService.delete(book, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("can't remove row with number " + row + " " + book.getId());
            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                dataProvider.getList().remove(row);
                Window.alert("removed row with number " + row);
            }
        });
    }


}