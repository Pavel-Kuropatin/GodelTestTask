package com.kuropatin.library.controllers;

import com.kuropatin.library.repository.impl.AuthorRepositoryImpl;
import com.kuropatin.library.models.Author;
import com.kuropatin.library.repository.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @return representation of page located in WEB-INF/views/
 * @redirect:/ redirects to the specified path from server root
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final BookRepositoryImpl bookRepositoryImpl;

    @Autowired
    public AuthorController(AuthorRepositoryImpl authorRepositoryImpl, BookRepositoryImpl bookRepositoryImpl) {
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    @GetMapping
    public String getViewAuthors(Model model) {
        model.addAttribute("authors", authorRepositoryImpl.getAllAuthors());
        return "author/authors";
    }

    @GetMapping("/{id}")
    public String getViewAuthorById(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorRepositoryImpl.getAuthorByAuthorId(id));
        model.addAttribute("books", bookRepositoryImpl.getBooksByAuthorId(id));
        return "author/author";
    }

    @GetMapping("/add")
    public String getViewAuthorAdd(@ModelAttribute("author") Author author) {
        return "author/authoradd";
    }

    @PostMapping("/{id}")
    public String getViewAuthorsOnCreate(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "author/authoradd";
        authorRepositoryImpl.createAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String getViewAuthorEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("author", authorRepositoryImpl.getAuthorByAuthorId(id));
        return "author/authoredit";
    }

    @PatchMapping("/{id}")
    public String getViewAuthorsOnUpdate(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "author/authoredit";
        authorRepositoryImpl.updateAuthor(id, author);
        Author authorForId = authorRepositoryImpl.getAuthorByName(author.getFirstName());
        return "redirect:/authors/" + authorForId.getId();
    }

    @DeleteMapping("/{id}")
    public String getViewAuthorsOnDelete(@PathVariable("id") int id) {
        authorRepositoryImpl.deleteAuthor(id);
        return "redirect:/authors";
    }
}