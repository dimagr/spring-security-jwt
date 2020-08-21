package com.example.springsecurityjwt.services;

import com.example.springsecurityjwt.enums.PublisherTypeEnum;
import com.example.springsecurityjwt.models.Author;
import com.example.springsecurityjwt.models.Book;
import com.example.springsecurityjwt.models.Publisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    List<Book> books = new ArrayList<>();

    public List<Book> extractAllBooks(){
        return books;
    }

    public List<Book> extractBooksByName(String bookName){
        return books.stream().filter(b->b.getName().equals(bookName)).collect(Collectors.toList());
    }

    public void createBook(Book book) {
        books.add(new Book(book));
    }
    public void modifyBookByName(String name, Book book){
        books.stream()
                .filter(b -> b.getName().equals(name))
                .forEach(b -> {
                    b.setAuthor(book.getAuthor());
                    b.setPublisher(book.getPublisher());
                });
    }

    public void deleteBookByName(String bookName){
        books.removeIf(b->b.getName().equals(bookName));
    }
}
