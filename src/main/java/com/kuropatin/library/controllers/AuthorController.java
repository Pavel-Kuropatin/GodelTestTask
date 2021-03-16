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
    private static final String PATH_VARIABLE_ID = "id";
    private static final String MODEL_ATTRIBUTE_AUTHOR = "author";
    private static final String MODEL_ATTRIBUTE_AUTHORS = "authors";
    private static final String MODEL_ATTRIBUTE_BOOKS = "books";
    private static final String AUTHORS_HTML = "author/authors";
    private static final String AUTHOR_HTML = "author/author";
    private static final String AUTHOR_EDIT_HTML = "author/authoredit";
    private static final String AUTHOR_ADD_HTML = "author/authoradd";
    private static final String REDIRECT_TO_AUTHORS = "redirect:/authors/";

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
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS, authorService.getAllAuthors());
        return AUTHORS_HTML;
    }

    /**
     * Method adds to model author entity, list of all books by this author
     * @return book.html page
     */
    @GetMapping("/{id}")
    public String getViewAuthorById(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_AUTHOR, authorService.getAuthorByAuthorId(id));
        model.addAttribute(MODEL_ATTRIBUTE_BOOKS, bookService.getBooksByAuthorId(id));
        return AUTHOR_HTML;
    }

    /**
     * Method returns authoradd.html page
     */
    @GetMapping("/add")
    public String getViewAuthorAdd(@ModelAttribute(MODEL_ATTRIBUTE_AUTHOR) Author author) {
        return AUTHOR_ADD_HTML;
    }

    /**
     * Method adds to model author entity information which should be edited
     * @return authoredit.html page
     */
    @GetMapping("/{id}/edit")
    public String getViewAuthorEdit(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_AUTHOR, authorService.getAuthorByAuthorId(id));
        return AUTHOR_EDIT_HTML;
    }

    /**
     * Method creates author
     * @return authoradd.html page if entered information is not valid
     *         authoradd.html page if author that has same name already exists
     *         redirects to /authors/{id} on successful creation
     */
    @PostMapping("/{id}")
    public String getViewAuthorsOnCreate(@ModelAttribute(MODEL_ATTRIBUTE_AUTHOR) @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || authorService.isAuthorExists(author))
            return AUTHOR_ADD_HTML;
        authorService.createAuthor(author);
        return REDIRECT_TO_AUTHORS + authorService.getAuthorId(author);
    }

    /**
     * Method updates author information
     * @return authoredit.html page if entered information is not valid
     *         redirects to /authors/{id} on successful update
     */
    @PatchMapping("/{id}")
    public String getViewAuthorsOnUpdate(@ModelAttribute(MODEL_ATTRIBUTE_AUTHOR) @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return AUTHOR_EDIT_HTML;
        authorService.updateAuthor(author.getId(), author);
        return REDIRECT_TO_AUTHORS + author.getId();
    }

    /**
     * Method deletes author and all books by this author
     * @return redirects to /authors
     */
    @DeleteMapping("/{id}")
    public String getViewAuthorsOnDelete(@PathVariable(PATH_VARIABLE_ID) long id) {
        authorService.deleteAuthor(id);
        return REDIRECT_TO_AUTHORS;
    }
}