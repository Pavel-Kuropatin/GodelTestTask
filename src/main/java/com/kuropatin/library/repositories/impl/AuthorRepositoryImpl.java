package com.kuropatin.library.repositories.impl;

import com.kuropatin.library.mappers.AuthorMapper;
import com.kuropatin.library.models.entities.Author;
import com.kuropatin.library.repositories.interfaces.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final JdbcTemplate JDBC_TEMPLATE;

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.JDBC_TEMPLATE = jdbcTemplate;
    }

    @Override
    public List<Author> getAllAuthors() {
        String sql = "SELECT * FROM authors ORDER BY first_name";
        return JDBC_TEMPLATE.query(sql, new AuthorMapper());
    }

    @Override
    public Author getAuthorByAuthorId(long id) {
        String sql = "SELECT * FROM authors WHERE id=?";
        return JDBC_TEMPLATE.query(sql, new AuthorMapper(), id).stream().findAny().orElse(null);
    }

    @Override
    public List<Author> getAuthorsByBookId(long bookId) {
        String sql = "SELECT authors.id, authors.first_name, authors.last_name, authors.birth_date, authors.sex " +
                     "FROM books, authors, book_author " +
                     "WHERE books.id = book_author.book_id AND authors.id = book_author.author_id AND book_author.book_id = ? " +
                     "ORDER BY first_name";
        return JDBC_TEMPLATE.query(sql, new AuthorMapper(), bookId);
    }

    @Override
    public List<Author> getAuthorsToBeAddedByBookId(long bookId) {
        String sql = "SELECT authors.id, authors.first_name, authors.last_name, authors.birth_date, authors.sex " +
                     "FROM authors " +
                     "WHERE authors.id NOT IN (" +
                         "SELECT authors.id " +
                         "FROM authors, books, book_author " +
                         "WHERE books.id = book_author.book_id AND book_author.book_id = books.id AND authors.id = book_author.author_id AND book_author.book_id = ?) " +
                         "AND authors.id IN (" +
                         "SELECT authors.id " +
                         "FROM authors, books " +
                         "WHERE CAST(SUBSTRING (authors.birth_date, 7) AS int) < books.year_of_publication AND books.id = ?)" +
                     "ORDER BY first_name";
        return JDBC_TEMPLATE.query(sql, new AuthorMapper(), bookId, bookId);
    }

    @Override
    public Author getAuthorByName(String firstName, String lastName) {
        String sql = "SELECT * FROM authors WHERE first_name=? AND last_name=?";
        return JDBC_TEMPLATE.query(sql, new AuthorMapper(), firstName, lastName).stream().findAny().orElse(null);
    }

    @Override
    public void createAuthor(Author author) {
        String sql = "INSERT INTO authors VALUES(default, ?, ?, ?, ?)";
        JDBC_TEMPLATE.update(sql, author.getFirstName(), author.getLastName(), author.getBirthDate(), author.getSex());
    }

    @Override
    public void updateAuthor(long id, Author author) {
        String sql = "UPDATE authors SET first_name=?, last_name=?, birth_date=?, sex=? WHERE id=?";
        JDBC_TEMPLATE.update(sql, author.getFirstName(), author.getLastName(), author.getBirthDate(), author.getSex(), id);
    }

    @Override
    public void deleteAuthor(long id) {
        String sql1 = "DELETE FROM books USING book_author WHERE book_author.book_id=books.id AND book_author.author_id=?";
        String sql2 = "DELETE FROM authors WHERE id=?";
        JDBC_TEMPLATE.update(sql1, id);
        JDBC_TEMPLATE.update(sql2, id);
    }
}