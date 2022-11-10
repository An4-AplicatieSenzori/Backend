package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

//Extinde exceptia basica:
public class DuplicateResourceException extends CustomException {

    //Un mesaj, si nu trebuie status cu numar, ci au nume;
    private static final String MESSAGE = "Resource duplicated!";
    private static final HttpStatus httpStatus = HttpStatus.CONFLICT;

    //Constructor, trimitem resursa mai departe, nu avem valerr, avem mesaj si status;
    //Foloseste un String mesaj;
    public DuplicateResourceException(String resource)
    {
        super(MESSAGE, httpStatus, resource, new ArrayList<>());
    }

}
