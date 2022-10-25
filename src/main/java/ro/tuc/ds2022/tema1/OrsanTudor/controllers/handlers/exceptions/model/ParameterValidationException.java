package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ParameterValidationException extends CustomException {

    //Analog mesaj + status name + constructor;
    private static final String MESSAGE = "Parameter is invalid!";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ParameterValidationException(String resource, List<String> errors) {
        super(MESSAGE, httpStatus, resource, errors);
    }
}