package com.kuropatin.library.repository.interfaces;

import com.kuropatin.library.models.Author;
import com.kuropatin.library.models.Book;

public interface BookAuthorRepository {

    void createBookAuthor(long bookId, long authorId);

    void deleteBookAuthor(long bookId, long authorId);
}