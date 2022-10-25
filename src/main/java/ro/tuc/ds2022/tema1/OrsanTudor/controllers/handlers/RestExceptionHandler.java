package ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers;

//Multe tooluri de importat:
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Toate schimbate;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.*;
//import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.CustomException;
//import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ExceptionHandlerResponseDTO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
//Pentru colectii:
import java.util.*;

//ControllerAdice
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //Value pentru exception;
    @ExceptionHandler(value = {ConstraintViolationException.class})
    //ConstraintViolation;
    //Return an Object as a Response;
    //Primit WebRequest request, Trimis Object response;
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {

        //Detalii:
        HttpStatus status = HttpStatus.CONFLICT;
        Set<ConstraintViolation<?>> details = e.getConstraintViolations();
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(
                //Parametrii pentru handler;
                e.getMessage(),
                status.getReasonPhrase(),
                status.value(),
                e.getMessage(),
                details,
                request.getDescription(false));

        return handleExceptionInternal(
                e,
                errorInformation,
                new HttpHeaders(),
                status,
                request
        );
    }

    //For Errors: (Argumente invalide)
    //Errors, we can use them;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ObjectError> errs = ex.getBindingResult().getAllErrors();
        List<String> details = new ArrayList<>();
        //Pentru obiect dam mesaj;
        for (ObjectError err : errs) {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            details.add(fieldName + ":" + errorMessage);
        }
        //Trimitem la Handler
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(ex.getParameter().getParameterName(),
                status.getReasonPhrase(),
                status.value(),
                MethodArgumentNotValidException.class.getSimpleName(),
                details,
                request.getDescription(false));
        //Analog:
        return handleExceptionInternal(
                ex,
                errorInformation,
                new HttpHeaders(),
                status,
                request
        );
    }

    //For Errors: (Custom exception)
    //Primit un WebRequest request, oferit un Object response;
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleCustomExceptions(CustomException ex,
                                                            WebRequest request) {
        //Handler + Return;
        ExceptionHandlerResponseDTO errorInformation = new ExceptionHandlerResponseDTO(ex.getResource(),
                ex.getStatus().getReasonPhrase(),
                ex.getStatus().value(),
                ex.getMessage(),
                ex.getValidationErrors(),
                request.getDescription(false));
        return handleExceptionInternal(
                ex,
                errorInformation,
                new HttpHeaders(),
                ex.getStatus(),
                request
        );
    }
}
















