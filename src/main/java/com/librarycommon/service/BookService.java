package com.librarycommon.service;

import com.librarycommon.exception.LibraryCommonException;
import com.librarycommon.utility.LibraryCommonUtility;
import com.librarycommon.dao.BookRepository;
import com.librarycommon.entity.Book;
import com.librarycommon.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public ResponseVo getBooks(String page, String size) {
        if (log.isDebugEnabled()) {
            log.info("Inside findAllBooks()");
        }
        ResponseVo responseVo = null;
        List<Book> book = null;
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,String> pageMap = new HashMap<>();

        try {
            if (page!= null && size != null) {
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                Page<Book> pagedResult = bookRepository.findAll(paging);
                pageMap.put("totalElements",String.valueOf(pagedResult.getTotalElements()));
                pageMap.put("totalPages",String.valueOf(LibraryCommonUtility.getTotalPages(Integer.parseInt(size), pagedResult.getTotalElements())));

                if(pagedResult.hasContent()) {
                    book = pagedResult.getContent();
                } else {
                    book = new ArrayList<>();
                }
            } else {
                book = bookRepository.findAll();
            }
            dataMap.put("books", book);
            dataMap.put("page",pageMap);
            responseVo = new ResponseVo();
            responseVo.setData(dataMap);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
               log.error("Exception inside findAllBooks()");
            }
            throw new LibraryCommonException("500","Something missing in request params", new ResponseVo());
        }
        if (log.isDebugEnabled()) {
            log.info("Exit from findAllBooks()");
        }
        return responseVo;
    }

    public ResponseVo getBookById(Long bookId) {
        if (log.isDebugEnabled()) {
            log.info("Exit from findBookById()");
        }
        Book book = null;
        ResponseVo responseVo = null;
        try {
            Optional<Book> bookPresent = bookRepository.findById(bookId);
            if (bookPresent.isPresent()) {
                book = bookPresent.get();
            }
            responseVo = new ResponseVo();
            responseVo.setData(book);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (LibraryCommonException e) {
            if (log.isErrorEnabled()) {
                log.error("Exception inside findBookById()", e);
            }
        }
        if (log.isDebugEnabled()) {
            log.info("Exit from findBookById()");
        }
        return responseVo;
    }

    public ResponseVo getBookByTitle(String title, String page, String size) {
        if (log.isDebugEnabled()) {
            log.info("Inside getBookByTitle()");
        }
        ResponseVo responseVo = null;
        List<Book> book = null;
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,String> pageMap = new HashMap<>();
        Page<Book> pagedResult = null;
        try {
            if (page!= null && size != null) {
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                pagedResult = bookRepository.findByTitleContaining(title,paging);
            } else {
                page = "0";
                size = "1";
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                pagedResult = bookRepository.findByTitleContaining(title,paging);
            }
            if( pagedResult != null && !pagedResult.isEmpty()) {
                book = pagedResult.getContent();
                pageMap.put("totalElements",String.valueOf(pagedResult.getTotalElements()));
                pageMap.put("totalPages",String.valueOf(LibraryCommonUtility.getTotalPages(Integer.parseInt(size), pagedResult.getTotalElements())));
            }
            dataMap.put("books", book);
            dataMap.put("page",pageMap);
            responseVo = new ResponseVo();
            responseVo.setData(dataMap);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (LibraryCommonException e) {
            if (log.isErrorEnabled()) {
                log.error("Exception inside getBookByTitle()");
            }
            throw new LibraryCommonException("500","Something missing in request params", new ResponseVo());
        }
        return responseVo;
    }

    public ResponseVo getBookByCategory(String category, String page, String size) {
        if (log.isDebugEnabled()) {
            log.info("Inside getBookByCategory()");
        }
        ResponseVo responseVo = null;
        List<Book> book = null;
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,String> pageMap = new HashMap<>();
        Page<Book> pagedResult = null;
        try {
            if (page!= null && size != null) {
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                pagedResult = bookRepository.findByCategory(category,paging);
            } else {
                page = "0";
                size = "1";
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                pagedResult = bookRepository.findByCategory(category,paging);
            }
            if( pagedResult != null && !pagedResult.isEmpty()) {
                book = pagedResult.getContent();
                pageMap.put("totalElements",String.valueOf(pagedResult.getTotalElements()));
                pageMap.put("totalPages",String.valueOf(LibraryCommonUtility.getTotalPages(Integer.parseInt(size), pagedResult.getTotalElements())));
            }
            dataMap.put("books", book);
            dataMap.put("page",pageMap);
            responseVo = new ResponseVo();
            responseVo.setData(dataMap);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (LibraryCommonException e) {
            if (log.isErrorEnabled()) {
                log.error("Exception inside getBookByCategory()");
            }
            throw new LibraryCommonException("500","Something missing in request params", new ResponseVo());
        }
        return responseVo;
    }
}
