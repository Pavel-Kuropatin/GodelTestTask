package com.kuropatin.library.repository.interfaces;

import com.kuropatin.library.models.Author;
import com.kuropatin.library.models.Book;

public interface BookAuthorRepository {

    //void createBookAuthor(Book book, List<Author> authorList);

    void createBookAuthor(long bookId, long authorId);

    //void updateBookAuthor(Book book, List<Author> authorList);

    void updateBookAuthor(Book book, Author author);
}