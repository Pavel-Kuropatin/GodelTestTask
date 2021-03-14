package com.kuropatin.library.models;

public class BookAuthor {
    private long bookId;
    private long authorId;

    public BookAuthor() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(final long bookId) {
        this.bookId = bookId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(final long authorId) {
        this.authorId = authorId;
    }
}