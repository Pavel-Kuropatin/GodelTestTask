package com.kuropatin.library.models;

import javax.validation.constraints.*;

public class Book {

    /** @final constants define the names of fields in the database */
    public static final String BOOK_id = "id";
    public static final String BOOK_name = "book_name";
    public static final String BOOK_yearOfPublication = "year_of_publication";
    public static final String BOOK_publisher = "publisher";

    private long id;

    @NotEmpty(message = "Book name should not be empty")
    @Size(max = 255, message = "Name should be between 1 and 255 characters")
    private String name;

    @Min(value = 1, message = "Year of publication should be more that 0")
    @Max(value = 2021, message = "Enter valid year")
    private int yearOfPublication;

    @NotEmpty(message = "Publisher name should not be empty")
    @Size(max = 255, message = "Publisher mame should be between 1 and 255 characters")
    private String publisher;

    /** Variable for specifying the book author and validation, does not exist in the database */
    private long bookAuthorId = 0;

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(final int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public long getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(long bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }
}