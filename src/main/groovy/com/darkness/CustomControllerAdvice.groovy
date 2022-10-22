package com.darkness

import com.darkness.model.ErrorResponse
import com.darkness.model.GenericRequest
import com.darkness.model.GenericResponse
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorResponse handleRuntimeException(RuntimeException e) {
        ErrorResponse response = new ErrorResponse()
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        response.setMessage(e.getStackTrace().toString())
        return response
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse handleBindingExceptions(ConstraintViolationException e) {
        ErrorResponse response = new ErrorResponse()
        response.setCode(HttpStatus.BAD_REQUEST.toString())
        response.setMessage(e.getMessage())
        return response
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    GenericResponse handleTypeMismatchExceptions(MethodArgumentTypeMismatchException e) {
        ErrorResponse response = new ErrorResponse()
        response.setCode(HttpStatus.BAD_REQUEST.toString())
        response.setMessage(e.getMessage())
        return response
    }
}