package com.kuropatin.library.repository.interfaces;

import com.kuropatin.library.models.Book;
import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks();

    Book getBookByBookId(int id);

    List<Book> getBooksByAuthorId(int authorId);

    Book getBookByName(String name);

    void createBook(Book book);

    void updateBook(int id, Book book);

    void deleteBook(int id);
}