package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;

//Status + Util;
import org.springframework.http.HttpStatus;
import java.util.List;

public class EntityValidationException extends CustomException {

    //Analog mesaj + status name + constructor;
    private static final String MESSAGE =  "Entity could not be processed!";
    private static final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

    public EntityValidationException(String resource, List<String> errors) {
        super(MESSAGE, httpStatus, resource, errors);
    }
}

