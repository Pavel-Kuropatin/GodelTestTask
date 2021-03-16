package com.kuropatin.library.services;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.repositories.impl.SearchRepositoryImpl;
import com.kuropatin.library.models.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

    private final SearchRepositoryImpl searchRepositoryImpl;

    @Autowired
    public SearchService(SearchRepositoryImpl searchRepositoryImpl) {
        this.searchRepositoryImpl = searchRepositoryImpl;
    }

    public List<Book> findBooks(Search search) {
        return searchRepositoryImpl.findBooks(search);
    }
}
