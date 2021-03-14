package com.kuropatin.library.repository.interfaces;

import com.kuropatin.library.models.Author;
import java.util.List;

public interface AuthorRepository {

    List<Author> getAllAuthors();

    Author getAuthorByAuthorId(int id);

    List<Author> getAuthorsByBookId(int id);

    List<Author> getAuthorsToBeAddedByBookId(int id);

    Author getAuthorByName(String name);

    void createAuthor(Author author);

    void updateAuthor(int id, Author author);

    void deleteAuthor(int id);
}