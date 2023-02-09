/*
package com.librarycommon.controller;

import com.librarycommon.dao.BookRepository;
import com.librarycommon.entity.Book;
import com.librarycommon.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(required = false) String page,@RequestParam(required = false) String size) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getAllBooks()");
        }
        List<Book> allBooks = null;
        try {
            allBooks = bookService.findAllBooks(page,size);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception inside getAllBooks()",e);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("Exit from getAllBooks()");
        }
        return allBooks;
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable(name = "bookId") Long bookId) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getBook()");
        }
        return bookService.findBookById(bookId);
    }
}
*/
