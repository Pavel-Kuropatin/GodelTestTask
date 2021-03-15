package com.kuropatin.library.mappers;

import com.kuropatin.library.models.Author;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.kuropatin.library.models.Author.*;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt(AUTHOR_id));
        author.setFirstName(resultSet.getString(AUTHOR_firstName));
        author.setLastName(resultSet.getString(AUTHOR_lastName));
        author.setBirthDate(resultSet.getString(AUTHOR_birthDate));
        author.setSex(resultSet.getString(AUTHOR_sex));
        return author;
    }
}