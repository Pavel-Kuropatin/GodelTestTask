package com.kuropatin.library.mappers;

import com.kuropatin.library.models.entities.Author;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.kuropatin.library.models.entities.Author.*;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt(AUTHOR_ID));
        author.setFirstName(resultSet.getString(AUTHOR_FIRST_NAME));
        author.setLastName(resultSet.getString(AUTHOR_LAST_NAME));
        author.setBirthDate(resultSet.getString(AUTHOR_BIRTH_DATE));
        author.setSex(resultSet.getString(AUTHOR_SEX));
        return author;
    }
}