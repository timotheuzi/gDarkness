/*
package com.darkness.exception

import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.ErrorHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest

@Controller
class CustomErrController implements ErrorHandler {

    private ErrorAttributes errorAttributes

    String ERROR_PATH = "/error"

    void AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes
    }

    @GetMapping(value = "/error", produces = "text/html")
    ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView("/errors/error", getErrorAttributes(request, true))
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request)
        return this.errorAttributes.getErrorAttributes((WebRequest) requestAttributes, includeStackTrace)//
    }

    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request))
        HttpStatus status = getStatus(request)
        return new ResponseEntity<Map<String, Object>>(body, status)
    }

    String getErrorPath() {
        return ERROR_PATH
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace")
        if (parameter == null) {
            return false
        }
        return !"false".equals(parameter.toLowerCase())
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code")
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode)
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR
    }

    @Override
    void handleError(Throwable arg0) {
        System.out.println("noooooooooo!")
        // TODO Auto-generated method stub
    }
}*/
