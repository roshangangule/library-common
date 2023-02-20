package com.librarycommon.advice;

import com.librarycommon.exception.LibraryCommonException;
import com.librarycommon.utility.LibraryCommonUtility;
import com.librarycommon.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    LibraryCommonUtility libraryCommonUtility;

    @ExceptionHandler(LibraryCommonException.class)
    public ResponseEntity<ResponseVo> handleApiException(LibraryCommonException libraryCommonException){
        if(log.isErrorEnabled()) {
            log.error("LibraryCommonException OCCURED :: ", libraryCommonException);
        }
        libraryCommonUtility.createErrorResponse(libraryCommonException);
        return new ResponseEntity<>(libraryCommonException.getResponseVo(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
