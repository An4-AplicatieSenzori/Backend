package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

//Alte tools:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link; //??
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//DTO = Data Transfer Object; - Nu luam intreb tabela, ci filtram sa ramana doar field-urile folositoare;
//DAO = Data Access Object; - Face Spring automat din tabele SQL in Obiect Java;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;

//Obiect pentru access services:
import ro.tuc.ds2022.tema1.OrsanTudor.services.UserService;

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
@RequestMapping(value = "/user")
public class UserController
{
    //Service-ul, not auto wired;
    private final UserService userService;

    //Initializezi in constructor service-ul; (not server)
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }



    //Get:
    @GetMapping() //("/GetExample1")
    //ResponseEntity: Lista de PersoaneDTO;
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        //Gasim lista persoane din service;
        List<UserDTO> dtos = userService.findUsers();
        //Pentru fiecare persoana, Link cu getPerson unde primeste id;
        //Se adauga la lista;
        for (UserDTO dto : dtos) {
            Link userLink = linkTo(methodOn(UserController.class)
                    .getUser(dto.getId())).withRel("userDetails");
            dto.add(userLink);
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
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody UserDetailsDTO userDTO)
    {
        //Insert la persoana, returneaza un UUID;
        UUID userID = userService.insert(userDTO);
        //Returneaza ID, cu Status Created;
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }



    //Get:
    //Returneaza un ResponseDTO (Details)
    //Pentru o persoana, avem ID in Link;
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable("id") UUID userId)
    {
        //Gaseste, returneaza un Person;
        UserDetailsDTO dto = userService.findUserById(userId);
        //Returneaza un DTO, status OK;
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}











