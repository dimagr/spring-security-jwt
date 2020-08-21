package com.example.springsecurityjwt.models;

import java.io.Serializable;

public class Book {
    private String name;
    private Author author;
    private Publisher publisher;

    public Book() {
    }

    public Book(Book book) {
        this.name = book.name;
        this.author = book.author;
        this.publisher = book.publisher;
    }

    public Book(String name, Author author, Publisher publisher) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
