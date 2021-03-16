package com.kuropatin.library.services;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.repositories.impl.SearchRepositoryImpl;
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
        if (search.getAuthorSex().equals(sexMale))
            sex = "AND authors.sex LIKE '" + sexMale + "'";
        else if (search.getAuthorSex().equals(sexFemale))
            sex = "AND authors.sex LIKE '" + sexFemale + "'";
        else sex = "";
        String sortDirection;
        if (search.getSortBy().equals(sortByBookName) || search.getSortBy().equals(sortByPublisher))
            sortDirection = searchAscending;
        else sortDirection = searchDescending;
        return searchRepositoryImpl.findBooks(search, sex, sortDirection);
    }
}
