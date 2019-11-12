package com.task.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.task.Shared.model.Book;

import java.util.List;


@RemoteServiceRelativePath("book")
public interface BookService extends RemoteService {
    List<Book> list();

    Book save(Book book);

    boolean delete(int id);

    Book getBook(int id);


}
