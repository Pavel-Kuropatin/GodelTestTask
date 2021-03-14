package com.kuropatin.library.repository.impl;

import com.kuropatin.library.models.Author;
import com.kuropatin.library.models.Book;
import com.kuropatin.library.repository.interfaces.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

    private final JdbcTemplate JDBC_TEMPLATE;

    @Autowired
    public BookAuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.JDBC_TEMPLATE = jdbcTemplate;
    }

    @Override
    public void createBookAuthor(long bookId, long authorId) {
        String sql = "INSERT INTO book_author VALUES(?, ?)";
        JDBC_TEMPLATE.update(sql, bookId, authorId);
    }

    @Override
    public void updateBookAuthor(Book book, Author author) {

    }
}
