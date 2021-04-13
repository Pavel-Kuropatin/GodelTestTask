package com.kuropatin.library.models.utils;

import javax.validation.constraints.Size;
import static com.kuropatin.library.models.entities.Author.SEX_UNDEFINED;
import static com.kuropatin.library.models.entities.Book.*;

public class Search {

    public static final String ORDER_BY_BOOK_NAME = BOOK_NAME;
    public static final String ORDER_BY_YEAR_OF_PUBLICATION = BOOK_YEAR_OF_PUBLICATION;
    public static final String ORDER_BY_BOOK_PUBLISHER = BOOK_PUBLISHER;
    public static final String SEARCH_ASCENDING = "ASC";
    public static final String SEARCH_DESCENDING = "DESC";

    @Size(max = 255, message = "Book name should be less than 255 characters")
    private String bookName = "";

    private int bookYearOfPublicationMin = 1;

    private int bookYearOfPublicationMax = 2021;

    @Size(max = 255, message = "Publisher name should be less than 255 characters")
    private String bookPublisher = "";

    @Size(max = 30, message = "First name should be less than 30 characters")
    private String authorFirstName = "";

    @Size(max = 30, message = "Last name should be less than 30 characters")
    private String authorLastName = "";

    private int authorYearOfBirthMin = 1;

    private int authorYearOfBirthMax = 2021;

    private String authorSex = SEX_UNDEFINED;

    private String orderBy = ORDER_BY_BOOK_NAME;

    public Search() {
        
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookYearOfPublicationMin() {
        return bookYearOfPublicationMin;
    }

    public void setBookYearOfPublicationMin(int bookYearOfPublicationMin) {
        if (bookYearOfPublicationMin < 1) this.bookYearOfPublicationMin = 1;
        else if (bookYearOfPublicationMin > 2021) this.bookYearOfPublicationMin = 1;
        else this.bookYearOfPublicationMin = bookYearOfPublicationMin;
    }

    public int getBookYearOfPublicationMax() {
        return bookYearOfPublicationMax;
    }

    public void setBookYearOfPublicationMax(int bookYearOfPublicationMax) {
        if (bookYearOfPublicationMax > 2021) this.bookYearOfPublicationMax = 2021;
        else if (bookYearOfPublicationMax < this.getBookYearOfPublicationMin()) this.bookYearOfPublicationMax = 2021;
        else this.bookYearOfPublicationMax = bookYearOfPublicationMax;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public int getAuthorYearOfBirthMin() {
        return authorYearOfBirthMin;
    }

    public void setAuthorYearOfBirthMin(int authorYearOfBirthMin) {
        if (authorYearOfBirthMin < 1) this.authorYearOfBirthMin = 1;
        else if (authorYearOfBirthMin > 2021) this.authorYearOfBirthMin = 1;
        else this.authorYearOfBirthMin = authorYearOfBirthMin;
    }

    public int getAuthorYearOfBirthMax() {
        return authorYearOfBirthMax;
    }

    public void setAuthorYearOfBirthMax(int authorYearOfBirthMax) {
        if (authorYearOfBirthMax > 2021) this.authorYearOfBirthMax = 2021;
        else if (authorYearOfBirthMax < this.getAuthorYearOfBirthMin()) this.authorYearOfBirthMax = 2021;
        else this.authorYearOfBirthMax = authorYearOfBirthMax;
    }

    public String getAuthorSex() {
        return authorSex;
    }

    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}