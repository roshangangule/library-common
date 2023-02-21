package com.librarycommon.controller;

import com.librarycommon.service.BookService;
import com.librarycommon.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<ResponseVo> getAllBooks(@RequestParam(required = false) String page, @RequestParam(required = false) String size) {
        if (log.isDebugEnabled()) {
            log.info("Inside getAllBooks()");
        }
        ResponseVo responseVo = bookService.getBooks(page,size);
        if (log.isDebugEnabled()) {
            log.info("Exit from getAllBooks()");
        }
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseVo> getBook(@PathVariable(name = "bookId") Long bookId) {
        if (log.isDebugEnabled()) {
            log.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookById(bookId);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
    @GetMapping("/search/title")
    public ResponseEntity<ResponseVo> getBookByTitle(
            @RequestParam(name = "title") String title,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size
    ) {
        if (log.isDebugEnabled()) {
            log.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookByTitle(title,page,size);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
    @GetMapping("/search/category")
    public ResponseEntity<ResponseVo> getBookByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size
    ) {
        if (log.isDebugEnabled()) {
            log.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookByCategory(category,page,size);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
}
