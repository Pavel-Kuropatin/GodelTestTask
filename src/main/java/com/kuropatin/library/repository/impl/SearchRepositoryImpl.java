package com.kuropatin.library.repository.impl;

import com.kuropatin.library.models.Book;
import com.kuropatin.library.repository.interfaces.SearchRepository;

import java.util.List;

public class SearchRepositoryImpl implements SearchRepository {

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public List<Book> findBookByName(String name) {
        return null;
    }

    @Override
    public List<Book> findBookByAuthor(String name) {
        return null;
    }

    @Override
    public List<Book> findBookByYearOfPublication(String name) {
        return null;
    }

    @Override
    public List<Book> findBookByPublisher(String name) {
        return null;
    }

    @Override
    public List<Book> findBookByAuthorsSex(String name) {
        return null;
    }

    @Override
    public List<Book> findBookByAuthorBirthDate(String name) {
        return null;
    }
}