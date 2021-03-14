package com.kuropatin.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @return start page representation of page located in WEB-INF/views/
 */
@Controller
@RequestMapping("/")
public class StartPageController {

    @GetMapping
    public String getView(Model model) {
        return "startpage";
    }
}