package com.example.spring.exception;

import com.example.spring.response.MyResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public MyResponse handle(ConstraintViolationException exception) {
        exception.printStackTrace();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation:constraintViolations) {
            return new MyResponse(500, constraintViolation.getPropertyPath()+constraintViolation.getMessage(), false, null);
        }
        return new MyResponse(500, "unknown", false, null);
    }

    @ExceptionHandler
    @ResponseBody
    public MyResponse handle(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        List<ObjectError> allErrors = exception.getAllErrors();
        for (ObjectError objectError:allErrors) {
            return new MyResponse(500, objectError.getCodes()[0]+objectError.getDefaultMessage(), false, null);
        }
        return new MyResponse(500, "unknown", false, null);
    }

    @ExceptionHandler
    @ResponseBody
    public MyResponse handle(MyException exception) {
        exception.printStackTrace();
        return new MyResponse(500, exception.getMessage(), false, null);
    }

}
