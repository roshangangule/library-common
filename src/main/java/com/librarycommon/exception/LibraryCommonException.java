package com.librarycommon.exception;

import com.librarycommon.vo.ResponseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCommonException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private  String errorMessage;
    private ResponseVo responseVo;
}
