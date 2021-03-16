package com.kuropatin.library.repositories;

import com.kuropatin.library.models.entities.Author;
import java.util.List;

public interface AuthorRepository {

    List<Author> getAllAuthors();

    Author getAuthorByAuthorId(long id);

    List<Author> getAuthorsByBookId(long id);

    List<Author> getAuthorsToBeAddedByBookId(long id);

    Author getAuthorByName(String name, String lastName);

    void createAuthor(Author author);

    void updateAuthor(long id, Author author);

    void deleteAuthor(long id);
}