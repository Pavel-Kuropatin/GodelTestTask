package com.kuropatin.library.controllers;

import com.kuropatin.library.services.SearchCriteriaService;
import com.kuropatin.library.models.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchCriteriaController {

    private final SearchCriteriaService searchCriteriaService;
    private static final String SEARCH_HTML = "search/search";
    private static final String MODEL_ATTRIBUTE_SEARCH = "search";
    private static final String MODEL_ATTRIBUTE_BOOKS = "books";

    @Autowired
    public SearchCriteriaController(SearchCriteriaService searchCriteriaService) {
        this.searchCriteriaService = searchCriteriaService;
    }

    /**
     * Method returns founded books
     * @return search.html page
     */
    @GetMapping
    public String getViewSearch(@ModelAttribute(MODEL_ATTRIBUTE_SEARCH) SearchCriteria searchCriteria, Model model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute(MODEL_ATTRIBUTE_BOOKS, searchCriteriaService.findBooks(searchCriteria));
        }
        return SEARCH_HTML;
    }
}