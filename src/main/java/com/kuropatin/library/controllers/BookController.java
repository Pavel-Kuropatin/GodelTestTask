package com.kuropatin.library.controllers;

import com.kuropatin.library.repository.impl.AuthorRepositoryImpl;
import com.kuropatin.library.repository.impl.BookAuthorRepositoryImpl;
import com.kuropatin.library.repository.impl.BookRepositoryImpl;
import com.kuropatin.library.models.Book;
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
@RequestMapping("/books")
public class BookController {

    private final BookRepositoryImpl bookRepositoryImpl;
    private final AuthorRepositoryImpl authorRepositoryImpl;
    private final BookAuthorRepositoryImpl bookAuthorRepositoryImpl;

    @Autowired
    public BookController(BookRepositoryImpl bookRepositoryImpl, AuthorRepositoryImpl authorRepositoryImpl, BookAuthorRepositoryImpl bookAuthorRepositoryImpl) {
        this.bookRepositoryImpl = bookRepositoryImpl;
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.bookAuthorRepositoryImpl = bookAuthorRepositoryImpl;
    }

    @GetMapping
    public String getViewBooks(Model model) {
        model.addAttribute("books", bookRepositoryImpl.getAllBooks());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String getViewBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookRepositoryImpl.getBookByBookId(id));
        model.addAttribute("authors", authorRepositoryImpl.getAuthorsByBookId(id));
        return "book/book";
    }

    @GetMapping("/add")
    public String getViewBookAdd(Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("authors", authorRepositoryImpl.getAllAuthors());
        return "book/bookadd";
    }

    @GetMapping("/{id}/edit")
    public String getViewBookEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookRepositoryImpl.getBookByBookId(id));
        return "book/bookedit";
    }

    @GetMapping("/{id}/add-author")
    public String getViewBookAuthorAdd(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookRepositoryImpl.getBookByBookId(id));
        model.addAttribute("authors", authorRepositoryImpl.getAuthorsByBookId(id));
        model.addAttribute("authorsToBeAdded", authorRepositoryImpl.getAuthorsToBeAddedByBookId(id));
        return "book/bookaddauthor";
    }

    @PostMapping("/{id}/add-author")
    public String getViewBooksOnAuthorAdd(@ModelAttribute("book") @Valid Book book,  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/bookadd";
        if (book.getBookAuthorId() == 0) {
            return "book/bookadd";
        }
        bookAuthorRepositoryImpl.createBookAuthor(book.getId(), book.getBookAuthorId());
        return "redirect:/books/" + book.getId();
    }

    @PostMapping("/{id}")
    public String getViewBooksOnCreate(@ModelAttribute("book") @Valid Book book,  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/bookadd";
        if (book.getBookAuthorId() == 0)
            return "book/bookadd";
        Book bookForValidation = bookRepositoryImpl.getBookByName(book.getName());
        if (bookForValidation != null)
            return "book/bookadd";
        bookRepositoryImpl.createBook(book);
        Book bookForId = bookRepositoryImpl.getBookByName(book.getName());
        bookAuthorRepositoryImpl.createBookAuthor(bookForId.getId(), book.getBookAuthorId());
        return "redirect:/books/" + bookForId.getId();
    }

    @PatchMapping("/{id}")
    public String getViewBooksOnUpdate(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/bookedit";
        bookRepositoryImpl.updateBook(id, book);
        Book bookForId = bookRepositoryImpl.getBookByName(book.getName());
        return "redirect:/books/" + bookForId.getId();
    }

    @DeleteMapping("/{id}")
    public String getViewBooksOnDelete(@PathVariable("id") int id) {
        bookRepositoryImpl.deleteBook(id);
        return "redirect:/books";
    }
}