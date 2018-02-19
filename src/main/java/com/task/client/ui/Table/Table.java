package com.task.client.ui.Table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
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

import java.util.List;


public class Table extends Composite {
    interface TableUiBinder extends UiBinder<HTMLPanel, Table> {
    }

    private static TableUiBinder ourUiBinder = GWT.create(TableUiBinder.class);

    private final BookServiceAsync bookService = Helper.getBookService();

    private static ListDataProvider<Book> dataProvider;

    @UiField
    EditCard editCard;

    @UiField
    Button addButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;

    /**
     * здесь пока не нашел красивого решения
     * дело в том, что mainTable управляется провайдером
     * устанавливая provided = true я не инициализирую таблицу,
     * поэтому делаю это в конструкторе
     */
    @UiField(provided = true)
    static CellTable<Book> mainTable;


    public Table() {
        mainTable = new CellTable<>();
        dataProvider = initTable(mainTable);
        initWidget(ourUiBinder.createAndBindUi(this));

    }


    private ListDataProvider<Book> initTable(CellTable<Book> table) {

        ListDataProvider<Book> dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(table);

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


    @UiHandler("addButton")
    void initAddButton(ClickEvent e) {
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
    void initEditButton(ClickEvent e) {
        int row = mainTable.getKeyboardSelectedRow();
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
    void initDeleteButton(ClickEvent e) {
        int row = mainTable.getKeyboardSelectedRow();
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
    
    public static ListDataProvider<Book> getDataProvider() {
        return dataProvider;
    }
}