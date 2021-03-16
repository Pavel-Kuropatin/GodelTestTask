package com.kuropatin.library.repositories;

public interface BookAuthorRepository {

    void createBookAuthor(long bookId, long authorId);

    void deleteBookAuthor(long bookId, long authorId);
}