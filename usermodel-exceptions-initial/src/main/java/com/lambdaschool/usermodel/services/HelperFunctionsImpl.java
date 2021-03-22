package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions {
    @Override
    public List<ValidationError> getConstraintViolations(Throwable cause) {
        List<ValidationError> validationErrors = new ArrayList<>();

        while(cause != null) {

            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException exception = (ConstraintViolationException) cause;

                ValidationError validationError = new ValidationError();
                validationError.setFieldname(exception.getMessage());
                validationError.setMessage(exception.getConstraintName());

                validationErrors.add(validationError);
            }

            if (cause instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException exception = (MethodArgumentNotValidException) cause;

                List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

                for (FieldError fe : fieldErrors) {
                    ValidationError validationError = new ValidationError();

                    validationError.setFieldname(fe.getField());
                    validationError.setMessage(fe.getDefaultMessage());

                    validationErrors.add(validationError);
                }
            }

            cause = cause.getCause();
        }

        return validationErrors;
    }
}
