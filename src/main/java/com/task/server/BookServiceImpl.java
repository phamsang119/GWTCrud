package com.task.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.task.client.BookService;
import com.task.server.domain.Book;
import com.task.server.repository.BookRepository;
import com.task.server.service.AppContext;

import java.util.List;

@SuppressWarnings("serial")
public class BookServiceImpl extends RemoteServiceServlet
        implements BookService {

    private final BookRepository bookRepository = AppContext.getBean(BookRepository.class);

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public boolean delete(Book book) {
        this.bookRepository.delete(book.getId());
        return true;
    }

}
