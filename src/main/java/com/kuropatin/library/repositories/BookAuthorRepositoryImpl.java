package com.kuropatin.library.repositories;

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
    public void deleteBookAuthor(long bookId, long authorId) {
        String sql = "DELETE FROM book_author WHERE book_id=? AND author_id=?";
        JDBC_TEMPLATE.update(sql, bookId, authorId);
    }
}
