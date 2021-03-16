package com.kuropatin.library.controllers;

import com.kuropatin.library.models.entities.Book;
import com.kuropatin.library.services.AuthorService;
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

    private final BookService bookService;
    private final AuthorService authorService;
    private static final String PATH_VARIABLE_ID = "id";
    private static final String MODEL_ATTRIBUTE_BOOK = "book";
    private static final String MODEL_ATTRIBUTE_BOOKS = "books";
    private static final String MODEL_ATTRIBUTE_AUTHORS = "authors";
    private static final String MODEL_ATTRIBUTE_AUTHORS_TO_BE_ADDED = "authorsToBeAdded";
    private static final String BOOKS_HTML = "book/books";
    private static final String BOOK_HTML = "book/book";
    private static final String BOOK_ADD_HTML = "book/bookadd";
    private static final String BOOK_EDIT_HTML = "book/bookedit";
    private static final String BOOK_ADD_AUTHOR_HTML = "book/bookaddauthor";
    private static final String BOOK_REMOVE_AUTHOR_HTML = "book/bookremoveauthor";
    private static final String REDIRECT_TO_BOOKS = "redirect:/books/";

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Method adds to model list of all existing books
     * @return books.html page
     */
    @GetMapping
    public String getViewBooks(Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOKS, bookService.getAllBooks());
        return BOOKS_HTML;
    }

    /**
     * Method adds to model book entity, list of book authors
     * @return book.html page
     */
    @GetMapping("/{id}")
    public String getViewBookById(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, bookService.getBookByBookId(id));
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS, authorService.getAuthorsByBookId(id));
        return BOOK_HTML;
    }

    /**
     * Method adds to model list of authors to be added as book authors
     * @return bookadd.html page
     */
    @GetMapping("/add")
    public String getViewBookAdd(@ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS, authorService.getAllAuthors());
        return BOOK_ADD_HTML;
    }

    /**
     * Method adds to model book entity information which should be edited
     * @return bookedit.html page
     */
    @GetMapping("/{id}/edit")
    public String getViewBookEdit(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, bookService.getBookByBookId(id));
        return BOOK_EDIT_HTML;
    }

    /**
     * Method adds to model book entity, list of book authors, list of authors to be added as book authors
     * @return bookaddauthor.html page
     */
    @GetMapping("/{id}/add-author")
    public String getViewBookAuthorAdd(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, bookService.getBookByBookId(id));
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS, authorService.getAuthorsByBookId(id));
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS_TO_BE_ADDED, authorService.getAuthorsToBeAddedByBookId(id));
        return BOOK_ADD_AUTHOR_HTML;
    }

    /**
     * Method adds to model book entity, list of book authors
     * @return bookremoveauthor.html page
     */
    @GetMapping("/{id}/remove-author")
    public String getViewBookAuthorRemove(@PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_BOOK, bookService.getBookByBookId(id));
        model.addAttribute(MODEL_ATTRIBUTE_AUTHORS, authorService.getAuthorsByBookId(id));
        return BOOK_REMOVE_AUTHOR_HTML;
    }

    /**
     * Method creates connection between book and author
     * @return redirects to /books/{id} on successful author adding
     */
    @PostMapping("/{id}/add-author")
    public String getViewBookOnAuthorAdd(@ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book) {
        bookService.addBookAuthor(book);
        return REDIRECT_TO_BOOKS + book.getId();
    }

    /**
     * Method creates book and connection between book and author
     * @return bookadd.html page if entered information is not valid
     *         bookadd.html page if book with entered name already exists
     *         redirects to /books/{id} on successful creation
     */
    @PostMapping("/{id}")
    public String getViewBooksOnCreate(@ModelAttribute(MODEL_ATTRIBUTE_BOOK) @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return BOOK_ADD_HTML;
        if (bookService.isBookExists(book.getName()))
            return BOOK_ADD_HTML;
        bookService.createBook(book);
        return REDIRECT_TO_BOOKS + bookService.getBookId(book);
    }

    /**
     * Method updates book information
     * @return bookedit.html page if entered information is not valid
     *         redirects to /books/{id} on successful update
     */
    @PatchMapping("/{id}")
    public String getViewBooksOnUpdate(@ModelAttribute(MODEL_ATTRIBUTE_BOOK) @Valid Book book, @PathVariable(PATH_VARIABLE_ID) long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return BOOK_EDIT_HTML;
        bookService.updateBook(id, book);
        return REDIRECT_TO_BOOKS + id;
    }

    /**
     * Method removes author from book authors
     * @return redirects to /books/{id} if entered information is not valid
     *         redirects to /books/{id} on successful remove
     */
    @DeleteMapping("/{id}/remove-author")
    public String getViewBookOnAuthorRemove(@ModelAttribute(MODEL_ATTRIBUTE_BOOK) Book book, @PathVariable(PATH_VARIABLE_ID) long id, Model model) {
        if (bookService.isLastAuthor(id))
            return REDIRECT_TO_BOOKS + id;
        bookService.removeBookAuthor(book);
        return REDIRECT_TO_BOOKS + id;
    }

    /**
     * Method deletes book and it's links to authors
     * @return redirects to /books
     */
    @DeleteMapping("/{id}")
    public String getViewBooksOnDelete(@PathVariable(PATH_VARIABLE_ID) long id) {
        bookService.deleteBook(id);
        return REDIRECT_TO_BOOKS;
    }
}