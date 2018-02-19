package com.task.server.domain;

import com.task.client.ui.Service.Helper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "book")
public class Book implements Serializable {

    @Id
    private int id;

    private String bookName;

    private String description;

    private Date publishedDate;

    private double price;

    public Book() {
    }

    public Book(int id, String bookName, String description, Date publishedDate, double price) {
        this.id = id;
        this.bookName = bookName;
        this.description = description;
        this.publishedDate = publishedDate;
        this.price = price;
    }

    public Book(String bookName, String description, Date publishedDate, double price) {
        this.id = Helper.getIncrementId();
        this.bookName = bookName;
        this.description = description;
        this.publishedDate = publishedDate;
        this.price = price;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", description='" + description + '\'' +
                ", publishedDate=" + publishedDate +
                ", price=" + price +
                '}';
    }
}
