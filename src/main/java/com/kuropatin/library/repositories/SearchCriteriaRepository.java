package com.kuropatin.library.repositories;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.models.utils.SearchCriteria;

import java.util.List;

public interface SearchCriteriaRepository {

    List<Book> findBooks(SearchCriteria searchCriteria, String sex, String searchDirection);
}