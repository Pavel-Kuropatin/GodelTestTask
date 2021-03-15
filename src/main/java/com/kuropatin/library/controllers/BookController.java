package com.kuropatin.library.controllers;

import com.kuropatin.library.models.Book;
import com.kuropatin.library.repository.impl.AuthorRepositoryImpl;
import com.kuropatin.library.repository.impl.BookRepositoryImpl;
import com.kuropatin.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepositoryImpl bookRepositoryImpl;
    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final BookService bookService;
    private final String pathVariableId = "id";
    private final String modelAttributeBook = "book";
    private final String modelAttributeBooks = "books";
    private final String modelAttributeAuthors = "authors";
    private final String modelAttributeAuthorsToBeAdded = "authorsToBeAdded";

    @Autowired
    public BookController(BookRepositoryImpl bookRepositoryImpl, AuthorRepositoryImpl authorRepositoryImpl, BookService bookService) {
        this.bookRepositoryImpl = bookRepositoryImpl;
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.bookService = bookService;
    }

    /**
     * Method adds to model list of all existing books
     * @return books.html page
     */
    @GetMapping
    public String getViewBooks(Model model) {
        model.addAttribute(modelAttributeBooks, bookRepositoryImpl.getAllBooks());
        return "book/books";
    }

    /**
     * Method adds to model book entity, list of book authors
     * @return book.html page
     */
    @GetMapping("/{id}")
    public String getViewBookById(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeBook, bookRepositoryImpl.getBookByBookId(id));
        model.addAttribute(modelAttributeAuthors, authorRepositoryImpl.getAuthorsByBookId(id));
        return "book/book";
    }

    /**
     * Method adds to model list of authors to be added as book authors
     * @return bookadd.html page
     */
    @GetMapping("/add")
    public String getViewBookAdd(@ModelAttribute(modelAttributeBook) Book book, Model model) {
        model.addAttribute(modelAttributeAuthors, authorRepositoryImpl.getAllAuthors());
        return "book/bookadd";
    }

    /**
     * Method adds to model book entity information which should be edited
     * @return bookedit.html page
     */
    @GetMapping("/{id}/edit")
    public String getViewBookEdit(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeBook, bookRepositoryImpl.getBookByBookId(id));
        return "book/bookedit";
    }

    /**
     * Method adds to model book entity, list of book authors, list of authors to be added as book authors
     * @return bookaddauthor.html page
     */
    @GetMapping("/{id}/add-author")
    public String getViewBookAuthorAdd(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeBook, bookRepositoryImpl.getBookByBookId(id));
        model.addAttribute(modelAttributeAuthors, authorRepositoryImpl.getAuthorsByBookId(id));
        model.addAttribute(modelAttributeAuthorsToBeAdded, authorRepositoryImpl.getAuthorsToBeAddedByBookId(id));
        return "book/bookaddauthor";
    }

    /**
     * Method adds to model book entity, list of book authors
     * @return bookremoveauthor.html page
     */
    @GetMapping("/{id}/remove-author")
    public String getViewBookAuthorRemove(@PathVariable(pathVariableId) long id, Model model) {
        model.addAttribute(modelAttributeBook, bookRepositoryImpl.getBookByBookId(id));
        model.addAttribute(modelAttributeAuthors, authorRepositoryImpl.getAuthorsByBookId(id));
        return "book/bookremoveauthor";
    }

    /**
     * Method creates connection between book and author
     * @return redirects to /books/{id} on successful author adding
     */
    @PostMapping("/{id}/add-author")
    public String getViewBookOnAuthorAdd(@ModelAttribute(modelAttributeBook) Book book) {
        bookService.addBookAuthor(book);
        return "redirect:/books/" + book.getId();
    }

    /**
     * Method creates book and connection between book and author
     * @return bookadd.html page if entered information is not valid
     *         bookadd.html page if book with entered name already exists
     *         redirects to /books/{id} on successful creation
     */
    @PostMapping("/{id}")
    public String getViewBooksOnCreate(@ModelAttribute(modelAttributeBook) @Valid Book book,  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/bookadd";
        if (bookService.isBookExists(book.getName()))
            return "book/bookadd";
        bookService.createBook(book);
        return "redirect:/books/" + bookService.getBookId(book);
    }

    /**
     * Method updates book information
     * @return bookedit.html page if entered information is not valid
     *         redirects to /books/{id} on successful update
     */
    @PatchMapping("/{id}")
    public String getViewBooksOnUpdate(@ModelAttribute(modelAttributeBook) @Valid Book book, @PathVariable(pathVariableId) long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/bookedit";
        bookService.updateBook(id, book);
        return "redirect:/books/" + id;
    }

    /**
     * Method removes author from book authors
     * @return redirects to /books/{id} if entered information is not valid
     *         redirects to /books/{id} on successful remove
     */
    @DeleteMapping("/{id}/remove-author")
    public String getViewBookOnAuthorRemove(@ModelAttribute(modelAttributeBook) Book book, @PathVariable(pathVariableId) long id, Model model) {
        if (bookService.isLastAuthor(id))
            return "redirect:/books/" + id;
        bookService.removeBookAuthor(book);
        return "redirect:/books/" + id;
    }

    /**
     * Method deletes book and it's links to authors
     * @return redirects to /books
     */
    @DeleteMapping("/{id}")
    public String getViewBooksOnDelete(@PathVariable(pathVariableId) long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}