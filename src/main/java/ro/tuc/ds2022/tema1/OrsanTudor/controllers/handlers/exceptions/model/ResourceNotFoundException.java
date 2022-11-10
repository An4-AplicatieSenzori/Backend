package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ResourceNotFoundException extends CustomException {

    //Analog mesaj + status name + constructor;
    //Acesta apare la eroarea in sine! Message tot din request!
    private static final String MESSAGE = "Resource not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    //Trimitem doar un mesaj? Super le foloseste restul, in rest doar un parametru;
    public ResourceNotFoundException(String resource) {
        super(MESSAGE, httpStatus, resource, new ArrayList<>());
    }
}
