package com.kuropatin.library.repositories;

import com.kuropatin.library.mappers.BookMapper;
import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.models.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    private final JdbcTemplate JDBC_TEMPLATE;

    @Autowired
    public SearchRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.JDBC_TEMPLATE = jdbcTemplate;
    }

    @Override
    public List<Book> findBooks(Search search, String sex, String searchDirection) {
        String sql = "SELECT * FROM books " +
                     "WHERE LOWER(book_name) LIKE LOWER('" + search.getBookName() + "%') " +
                     "AND year_of_publication BETWEEN ? AND ? " +
                     "AND LOWER(publisher) LIKE LOWER('" + search.getBookPublisher() + "%') " +
                     "AND id IN (" +
                         "SELECT book_author.book_id " +
                         "FROM authors, book_author " +
                         "WHERE book_author.author_id = authors.id " +
                         "AND LOWER(authors.first_name) LIKE LOWER('" + search.getAuthorFirstName() + "%') " +
                         "AND LOWER(authors.last_name) LIKE LOWER('" + search.getAuthorLastName() + "%') " +
                         "AND CAST(SUBSTRING(authors.birth_date, 7) as int) BETWEEN ? AND ? " +
                         sex +
                     ") ORDER BY " + search.getSortBy() + " " + searchDirection;
        return JDBC_TEMPLATE.query(sql, new BookMapper(), search.getBookYearOfPublicationMin(), search.getBookYearOfPublicationMax(), search.getAuthorYearOfBirthMin(), search.getAuthorYearOfBirthMax());
    }
}