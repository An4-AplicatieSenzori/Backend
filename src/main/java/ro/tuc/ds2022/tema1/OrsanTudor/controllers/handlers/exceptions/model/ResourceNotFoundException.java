package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ResourceNotFoundException extends CustomException {

    //Analog mesaj + status name + constructor;
    private static final String MESSAGE = "Resource not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public ResourceNotFoundException(String resource) {
        super(MESSAGE, httpStatus, resource, new ArrayList<>());
    }
}
