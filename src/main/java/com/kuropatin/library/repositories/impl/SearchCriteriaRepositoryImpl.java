package com.kuropatin.library.repositories.impl;

import com.kuropatin.library.mappers.BookMapper;
import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.models.utils.SearchCriteria;
import com.kuropatin.library.repositories.SearchCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SearchCriteriaRepositoryImpl implements SearchCriteriaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchCriteriaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findBooks(SearchCriteria searchCriteria, String sex, String sortDirection) {
        String sql = "SELECT * FROM books " +
                     "WHERE LOWER(book_name) LIKE LOWER(?) " +
                     "AND year_of_publication BETWEEN ? AND ? " +
                     "AND LOWER(publisher) LIKE LOWER(?) " +
                     "AND id IN (" +
                         "SELECT book_author.book_id " +
                         "FROM authors, book_author " +
                         "WHERE book_author.author_id = authors.id " +
                         "AND LOWER(authors.first_name) LIKE LOWER(?) " +
                         "AND LOWER(authors.last_name) LIKE LOWER(?) " +
                         "AND CAST(SUBSTRING(authors.birth_date, 7) as int) BETWEEN ? AND ? " +
                         "AND authors.sex LIKE ? " +
                     ") ORDER BY " + searchCriteria.getOrderBy() + " " + sortDirection;
        return jdbcTemplate.query(sql, new BookMapper(), (searchCriteria.getBookName() + "%"), searchCriteria.getBookYearOfPublicationMin(), searchCriteria.getBookYearOfPublicationMax(), (searchCriteria.getBookPublisher() + "%"), (searchCriteria.getAuthorFirstName() + "%"), (searchCriteria.getAuthorLastName() + "%"), searchCriteria.getAuthorYearOfBirthMin(), searchCriteria.getAuthorYearOfBirthMax(), (sex + "%"));
    }
}