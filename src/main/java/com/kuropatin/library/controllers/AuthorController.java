package com.kuropatin.library.controllers;

import com.kuropatin.library.models.entities.Author;
import com.kuropatin.library.services.AuthorService;
import com.kuropatin.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final String pathVariableId = "id";
    private final String modelAttributeAuthor = "author";
    private final String modelAttributeAuthors = "authors";
    private final String modelAttributeBooks = "books";

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    /**
     * Method adds to model list of all existing authors
     * @return authors.html page
     */
    @GetMapping
    public String getViewAuthors(Model model) {
        model.addAttribute(modelAttributeAuthors, authorService.getAllAuthors());
        return "author/authors";
    }

    /**
     * Method adds to model author entity, list of all books by this author
     * @return book.html page
     */
    @GetMapping("/{id}")
    public String getViewAuthorById(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeAuthor, authorService.getAuthorByAuthorId(id));
        model.addAttribute(modelAttributeBooks, bookService.getBooksByAuthorId(id));
        return "author/author";
    }

    /**
     * Method returns authoradd.html page
     */
    @GetMapping("/add")
    public String getViewAuthorAdd(@ModelAttribute(modelAttributeAuthor) Author author) {
        return "author/authoradd";
    }

    /**
     * Method adds to model author entity information which should be edited
     * @return authoredit.html page
     */
    @GetMapping("/{id}/edit")
    public String getViewAuthorEdit(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeAuthor, authorService.getAuthorByAuthorId(id));
        return "author/authoredit";
    }

    /**
     * Method creates author
     * @return authoradd.html page if entered information is not valid
     *         authoradd.html page if author that has same name already exists
     *         redirects to /authors/{id} on successful creation
     */
    @PostMapping("/{id}")
    public String getViewAuthorsOnCreate(@ModelAttribute(modelAttributeAuthor) @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "author/authoradd";
        if (authorService.isAuthorExists(author))
            return "author/authoradd";
        authorService.createAuthor(author);
        return "redirect:/authors/" + authorService.getAuthorId(author);
    }

    /**
     * Method updates author information
     * @return authoredit.html page if entered information is not valid
     *         redirects to /authors/{id} on successful update
     */
    @PatchMapping("/{id}")
    public String getViewAuthorsOnUpdate(@ModelAttribute(modelAttributeAuthor) @Valid Author author, @PathVariable(pathVariableId) long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "author/authoredit";
        authorService.updateAuthor(id, author);
        return "redirect:/authors/" + id;
    }

    /**
     * Method deletes author and all books by this author
     * @return redirects to /authors
     */
    @DeleteMapping("/{id}")
    public String getViewAuthorsOnDelete(@PathVariable(pathVariableId) long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}