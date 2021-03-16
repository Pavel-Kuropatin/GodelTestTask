package com.kuropatin.library.repositories;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.models.utils.Search;
import java.util.List;

public interface SearchRepository {

    List<Book> findBooks(Search search, String sex, String searchDirection);
}