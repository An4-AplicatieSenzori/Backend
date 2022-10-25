package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model;
//Technical university of Cluj-Napoca; Am adaugat credentialele mele;

import org.springframework.http.HttpStatus;
import java.util.List;

//Scrie cine a facut gitul doar in 2022;
//Super pentru runtime exception;
public class CustomException extends RuntimeException {

    //resource = ce primim;
    //status = ce oferim pentru a stii cum functioneaza; (un tip anume)
    //valderr = ca niste mesaje de eroare; (ca message) (List este destul)
    private final String resource;
    private final HttpStatus status ;
    private final List<String> validationErrors;

    //Constructor:
    public CustomException(String message, HttpStatus status,  String resource, List<String> errors) {
        super(message);
        this.resource = resource;
        this.validationErrors = errors;
        this.status = status;
    }

    //Doar getteri, date finale;
    public String getResource() {
        return resource;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public List<String> getValidationErrors() {
        return validationErrors;
    }
}











