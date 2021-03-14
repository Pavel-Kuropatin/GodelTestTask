package com.kuropatin.library.controllers;

import com.kuropatin.library.repository.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @return representation of page located in WEB-INF/views/
 * @redirect:/ redirects to the specified path from server root
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final BookRepositoryImpl bookDAO;

    @Autowired
    public SearchController(BookRepositoryImpl bookDAO) {
        this.bookDAO = bookDAO;
    }
}