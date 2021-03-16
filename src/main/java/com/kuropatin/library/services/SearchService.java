package com.kuropatin.library.services;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.repositories.SearchRepositoryImpl;
import com.kuropatin.library.models.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.kuropatin.library.models.entities.Author.*;
import static com.kuropatin.library.models.utils.Search.*;

@Service
public class SearchService {

    private final SearchRepositoryImpl searchRepositoryImpl;

    @Autowired
    public SearchService(SearchRepositoryImpl searchRepositoryImpl) {
        this.searchRepositoryImpl = searchRepositoryImpl;
    }

    public List<Book> findBooks(Search search) {
        String sex;
        if (search.getAuthorSex().equals(SEX_MALE))
            sex = "AND authors.sex LIKE '" + SEX_MALE + "'";
        else if (search.getAuthorSex().equals(SEX_FEMALE))
            sex = "AND authors.sex LIKE '" + SEX_FEMALE + "'";
        else sex = "";
        String sortDirection;
        if (search.getSortBy().equals(SORT_BY_BOOK_NAME) || search.getSortBy().equals(SORT_BY_BOOK_PUBLISHER))
            sortDirection = SEARCH_ASCENDING;
        else sortDirection = SEARCH_DESCENDING;
        return searchRepositoryImpl.findBooks(search, sex, sortDirection);
    }
}
