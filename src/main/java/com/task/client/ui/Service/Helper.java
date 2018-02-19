package com.task.client.ui.Service;


import com.google.gwt.core.client.GWT;
import com.task.client.BookService;
import com.task.client.BookServiceAsync;

public class Helper {


    private static int id = 0;

    private Helper() {
    }

    public static int getIncrementId() {
        return id++;
    }

    private static final BookServiceAsync bookService = GWT.create(BookService.class);

    public static BookServiceAsync getBookService() {
        return bookService;
    }
}