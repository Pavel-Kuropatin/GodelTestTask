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

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final BookRepositoryImpl bookRepositoryImpl;
    private final String pathVariableId = "id";
    private final String modelAttributeAuthor = "author";
    private final String modelAttributeAuthors = "authors";
    private final String modelAttributeBooks = "books";

    @Autowired
    public AuthorController(AuthorRepositoryImpl authorRepositoryImpl, BookRepositoryImpl bookRepositoryImpl) {
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    /**
     * Method adds to model list of all existing authors
     * @return authors.html page
     */
    @GetMapping
    public String getViewAuthors(Model model) {
        model.addAttribute(modelAttributeAuthors, authorRepositoryImpl.getAllAuthors());
        return "author/authors";
    }

    /**
     * Method adds to model author entity, list of all books by this author
     * @return book.html page
     */
    @GetMapping("/{id}")
    public String getViewAuthorById(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeAuthor, authorRepositoryImpl.getAuthorByAuthorId(id));
        model.addAttribute(modelAttributeBooks, bookRepositoryImpl.getBooksByAuthorId(id));
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
        model.addAttribute(modelAttributeAuthor, authorRepositoryImpl.getAuthorByAuthorId(id));
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
        Author authorForValidation = authorRepositoryImpl.getAuthorByName(author.getFirstName(), author.getLastName());
        if (authorForValidation != null)
            return "author/authoradd";
        authorRepositoryImpl.createAuthor(author);
        Author authorForId = authorRepositoryImpl.getAuthorByName(author.getFirstName(), author.getLastName());
        return "redirect:/authors/" + authorForId.getId();
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
        authorRepositoryImpl.updateAuthor(id, author);
        return "redirect:/authors/" + id;
    }

    /**
     * Method deletes author and all books by this author
     * @return redirects to /authors
     */
    @DeleteMapping("/{id}")
    public String getViewAuthorsOnDelete(@PathVariable(pathVariableId) long id) {
        authorRepositoryImpl.deleteAuthor(id);
        return "redirect:/authors";
    }
}