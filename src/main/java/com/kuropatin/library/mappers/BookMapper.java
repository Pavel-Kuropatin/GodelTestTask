package com.kuropatin.library.mappers;

import com.kuropatin.library.models.Book;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.kuropatin.library.models.Book.*;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt(BOOK_id));
        book.setName(resultSet.getString(BOOK_name));
        book.setYearOfPublication(resultSet.getInt(BOOK_yearOfPublication));
        book.setPublisher(resultSet.getString(BOOK_publisher));
        return book;
    }
}