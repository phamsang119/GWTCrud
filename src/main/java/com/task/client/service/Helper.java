package com.task.client.service;


import com.google.gwt.core.client.GWT;
import com.task.client.BookService;
import com.task.client.BookServiceAsync;

public class Helper {

    private static int id = 0;

    private static final BookServiceAsync bookService = GWT.create(BookService.class);

    private Helper() {
    }

    public static int getIncrementId() {
        return id++;
    }


    public static BookServiceAsync getBookService() {
        return bookService;
    }
}