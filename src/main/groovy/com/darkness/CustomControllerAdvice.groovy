package com.darkness

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
    GenericResponse handleRuntimeException(RuntimeException e){
        e.printStackTrace()
        return new GenericResponse().code(HttpStatus.INTERNAL_SERVER_ERROR.toString()).message(e.printStackTrace())
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    GenericResponse handleBindingExceptions(ConstraintViolationException e){
        e.printStackTrace()
        return new GenericResponse().code(HttpStatus.BAD_REQUEST.toString()).message(e.printStackTrace())
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    GenericResponse handleTypeMismatchExceptions(MethodArgumentTypeMismatchException e){
        e.printStackTrace()
        return new GenericResponse().code(HttpStatus.BAD_REQUEST.toString()).message(e.printStackTrace())
    }


}