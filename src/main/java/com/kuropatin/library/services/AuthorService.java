package com.kuropatin.library.services;

import com.kuropatin.library.models.entities.Author;
import com.kuropatin.library.repositories.impl.AuthorRepositoryImpl;
import com.kuropatin.library.repositories.impl.BookAuthorRepositoryImpl;
import com.kuropatin.library.repositories.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final BookRepositoryImpl bookRepositoryImpl;
    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final BookAuthorRepositoryImpl bookAuthorRepositoryImpl;

    @Autowired
    public AuthorService(BookRepositoryImpl bookRepositoryImpl, AuthorRepositoryImpl authorRepositoryImpl, BookAuthorRepositoryImpl bookAuthorRepositoryImpl) {
        this.bookRepositoryImpl = bookRepositoryImpl;
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.bookAuthorRepositoryImpl = bookAuthorRepositoryImpl;
    }

    public List<Author> getAllAuthors() {
        return authorRepositoryImpl.getAllAuthors();
    }

    public Author getAuthorByAuthorId(long id) {
        return authorRepositoryImpl.getAuthorByAuthorId(id);
    }

    public List<Author> getAuthorsByBookId(long bookId) {
        return authorRepositoryImpl.getAuthorsByBookId(bookId);
    }

    public List<Author> getAuthorsToBeAddedByBookId(long bookId) {
        return authorRepositoryImpl.getAuthorsToBeAddedByBookId(bookId);
    }

    public void createAuthor(Author author) {
        authorRepositoryImpl.createAuthor(author);
    }

    public void updateAuthor(long id, Author author) {
        authorRepositoryImpl.updateAuthor(id, author);
    }

    public void deleteAuthor(long id) {
        authorRepositoryImpl.deleteAuthor(id);
    }

    public long getAuthorId(Author author) {
        Author authorForId = authorRepositoryImpl.getAuthorByName(author.getFirstName(), author.getLastName());
        return authorForId.getId();
    }

    public boolean isAuthorExists(Author author) {
        Author authorForValidation = authorRepositoryImpl.getAuthorByName(author.getFirstName(), author.getLastName());
        return authorForValidation != null;
    }
}
