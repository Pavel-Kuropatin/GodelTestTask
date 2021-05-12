package com.kuropatin.library.repositories.impl;

import com.kuropatin.library.repositories.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookAuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createBookAuthor(long bookId, long authorId) {
        String sql = "INSERT INTO book_author VALUES(?, ?)";
        jdbcTemplate.update(sql, bookId, authorId);
    }

    @Override
    public void deleteBookAuthor(long bookId, long authorId) {
        String sql = "DELETE FROM book_author WHERE book_id=? AND author_id=?";
        jdbcTemplate.update(sql, bookId, authorId);
    }
}
