package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

//Alte tools:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link; //??
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//DTO = Data Transfer Object; - Nu luam intreb tabela, ci filtram sa ramana doar field-urile folositoare;
//DAO = Data Access Object; - Face Spring automat din tabele SQL in Obiect Java;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDetailsDTO;

//Obiect pentru access services:
import ro.tuc.ds2022.tema1.OrsanTudor.services.PersonService;

//Alte tools:
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//import static:
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Si aici trebuie Cross Origin;
@RestController
@CrossOrigin

//Pentru a adauga la path pagina noua, unde controllerul manage-uieste lucrurile;
@RequestMapping(value = "/person")
public class PersonController
{
    //Service-ul, not auto wired;
    private final PersonService personService;

    //Initializezi in constructor service-ul; (not server)
    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    //Get:
    @GetMapping() //("/GetExample1")
    //ResponseEntity: Lista de PersoaneDTO;
    public ResponseEntity<List<PersonDTO>> getPersons()
    {
        //Gasim lista persoane din service;
        List<PersonDTO> dtos = personService.findPersons();
        //Pentru fiecare persoana, Link cu getPerson unde primeste id;
        //Se adauga la lista;
        for (PersonDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }

        //Returneaza lista de persoane, cu Status OK;
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //Post: Un tip de mapare;
    //Pentru Post, returnam UUID pentru confirmare;
    //Primeste un Person;
    //CTRL + SHIFT + F search;
    //Valida persoana;
    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO)
    {
        //Insert la persoana, returneaza un UUID;
        UUID personID = personService.insert(personDTO);
        //Returneaza ID, cu Status Created;
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    //Get:
    //Returneaza un ResponseDTO (Details)
    //Pentru o persoana, avem ID in Link;
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId)
    {
        //Gaseste, returneaza un Person;
        PersonDetailsDTO dto = personService.findPersonById(personId);
        //Returneaza un DTO, status OK;
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource
}











