package com.task.client.ui.Table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.task.client.BookServiceAsync;
import com.task.client.ui.Service.Helper;
import com.task.server.domain.Book;

import java.util.List;


public class Table extends Composite {
    interface TableUiBinder extends UiBinder<HTMLPanel, Table> {
    }

    private static TableUiBinder ourUiBinder = GWT.create(TableUiBinder.class);

    /**
     * PRC service.
     */
    private final BookServiceAsync bookService = Helper.getBookService();


    @UiField
    TableHeader header;

    @UiField(provided = true)
    static CellTable<Book> mainTable;

    private static ListDataProvider<Book> dataProvider;

    public Table() {

        mainTable = new CellTable<>();
        dataProvider = createTable(mainTable);
        initWidget(ourUiBinder.createAndBindUi(this));


        TextColumn<Book> nameColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getBookName();
            }
        };
        mainTable.addColumn(nameColumn, "Book Name");

        TextColumn<Book> descCol = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getDescription();
            }
        };
        mainTable.addColumn(descCol, "Description");

        TextColumn<Book> pubDate = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getPublishedDate().toString().substring(0, 10);
            }
        };
        mainTable.addColumn(pubDate, "Published Date");

        TextColumn<Book> price = new TextColumn<Book>() {
            @Override
            public String getValue(Book object) {
                return object.getPrice() + "";
            }
        };
        mainTable.addColumn(price, "Price");


    }


    private ListDataProvider<Book> createTable(CellTable<Book> table) {

        ListDataProvider<Book> dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(table);

        this.bookService.list(new AsyncCallback<List<Book>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("error", throwable);
            }

            @Override
            public void onSuccess(List<Book> books) {

                dataProvider.getList().addAll(books);

            }
        });
        return dataProvider;
    }



    public static CellTable<Book> getMainTable() {
        return mainTable;
    }

    public static ListDataProvider<Book> getDataProvider() {
        return dataProvider;
    }
}