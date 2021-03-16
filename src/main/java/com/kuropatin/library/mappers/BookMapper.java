package com.kuropatin.library.mappers;

import com.kuropatin.library.models.entities.Book;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.kuropatin.library.models.entities.Book.*;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt(BOOK_ID));
        book.setName(resultSet.getString(BOOK_NAME));
        book.setYearOfPublication(resultSet.getInt(BOOK_YEAR_OF_PUBLICATION));
        book.setPublisher(resultSet.getString(BOOK_PUBLISHER));
        return book;
    }
}