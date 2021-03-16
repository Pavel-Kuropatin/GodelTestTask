package com.kuropatin.library.controllers;

import com.kuropatin.library.repositories.impl.SearchRepositoryImpl;
import com.kuropatin.library.services.SearchService;
import com.kuropatin.library.models.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchRepositoryImpl searchRepositoryImpl, SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Method returns founded books
     * @return search.html page
     */
    @GetMapping
    public String getViewSearch(@ModelAttribute("search") Search search, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "search/search";
        model.addAttribute("books", searchService.findBooks(search));
        return "search/search";
    }
}