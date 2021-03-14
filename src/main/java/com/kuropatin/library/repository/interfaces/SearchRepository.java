package com.kuropatin.library.repository.interfaces;

import com.kuropatin.library.models.Book;

import java.util.List;

public interface SearchRepository {

    List<Book> findAllBooks();

    List<Book> findBookByName(String name);

    List<Book> findBookByAuthor(String name);

    List<Book> findBookByYearOfPublication(String name);

    List<Book> findBookByPublisher(String name);

    List<Book> findBookByAuthorsSex(String name);

    List<Book> findBookByAuthorBirthDate(String name);
}