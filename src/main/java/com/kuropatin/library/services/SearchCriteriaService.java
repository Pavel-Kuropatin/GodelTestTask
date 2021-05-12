package com.kuropatin.library.services;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.models.utils.SearchCriteria;
import com.kuropatin.library.repositories.impl.SearchCriteriaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.kuropatin.library.models.entities.Author.*;
import static com.kuropatin.library.models.utils.SearchCriteria.*;

@Service
public class SearchCriteriaService {

    private final SearchCriteriaRepositoryImpl searchRepositoryImpl;

    @Autowired
    public SearchCriteriaService(SearchCriteriaRepositoryImpl searchRepositoryImpl) {
        this.searchRepositoryImpl = searchRepositoryImpl;
    }

    public List<Book> findBooks(SearchCriteria searchCriteria) {
        String sex;
        if (searchCriteria.getAuthorSex().equals(SEX_MALE) || searchCriteria.getAuthorSex().equals(SEX_FEMALE)) {
            sex = searchCriteria.getAuthorSex();
        } else sex = "";
        String sortDirection;
        if (searchCriteria.getOrderBy().equals(ORDER_BY_BOOK_NAME) || searchCriteria.getOrderBy().equals(ORDER_BY_BOOK_PUBLISHER)) {
            sortDirection = SEARCH_ASCENDING;
        } else sortDirection = SEARCH_DESCENDING;
        return searchRepositoryImpl.findBooks(searchCriteria, sex, sortDirection);
    }
}