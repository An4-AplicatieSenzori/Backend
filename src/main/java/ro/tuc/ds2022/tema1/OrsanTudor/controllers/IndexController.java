package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

//Alte tooluri:
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Pentru a primi un request doar de la un URL si atat;
@RestController
@CrossOrigin
public class IndexController
{
    //Path / URL: Cel initial:
    //http://localhost:8080/
    @GetMapping(value = "/")

    //Trimitem un status, dar doar un singur mesaj;
    public ResponseEntity<String> getStatus()
    {
        return new ResponseEntity<>("Tema 1 Orsan Tudor: City APP Service is running...", HttpStatus.OK);
    }
}












