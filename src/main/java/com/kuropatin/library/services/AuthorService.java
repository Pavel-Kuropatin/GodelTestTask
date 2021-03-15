package com.kuropatin.library.services;

import com.kuropatin.library.models.Author;
import com.kuropatin.library.repository.impl.AuthorRepositoryImpl;
import com.kuropatin.library.repository.impl.BookAuthorRepositoryImpl;
import com.kuropatin.library.repository.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
