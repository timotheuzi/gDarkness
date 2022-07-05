package com.darkness.exception

import com.darkness.model.GenericResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    GenericResponse handleRuntimeException(RuntimeException exception){
        exception.printStackTrace()
        return new GenericResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message("There was a runtime exception:" + exception.printStackTrace())
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    GenericResponse handleBadRequest(RuntimeException exception){
        exception.printStackTrace()
        return new GenericResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message("There was a runtime exception:" + exception.printStackTrace())
    }


}
