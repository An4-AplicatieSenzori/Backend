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
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDataDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserRoleRedirectDTO; //Verde;

//Obiect pentru access services:
import ro.tuc.ds2022.tema1.OrsanTudor.services.UserService;

//Alte tools:
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//Pentru gson;
import com.google.gson.Gson;

//import static:
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Si aici trebuie Cross Origin;
@RestController
@CrossOrigin

//Pentru a adauga la path pagina noua, unde controllerul manage-uieste lucrurile;
//Toate requesturile din controller se fac cu user; (Cu asta lucrez in React)
//Toate celelalte requesturi se fac dupa /user;
@RequestMapping(value = "/user")
public class UserController
{
    //Service-ul, not auto wired;
    private final UserService userService;

    //Pentru convertire JSON; Folosit in n places;
    private static final Gson gson = new Gson();

    //Pentru salvare user curent;
    //Private si Static: Devine public;
    public static UserDetailsDTO currentUser = new UserDetailsDTO();

    //Initializezi in constructor service-ul; (not server)
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
        currentUser = new UserDetailsDTO("InitialName", "InitialAddress", 100,
                "InitialEmail@Email.com", "Passwor1-", "noRole");
        //String name, String address, int age, String email, String password, String role
    }



    //Get: /user;
    //INPUT - NIMIC / OUTPUT - LISTA USERI;
    @GetMapping() //("/GetExample1")
    //ResponseEntity: Lista de PersoaneDTO;
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        //Gasim lista persoane din service;
        List<UserDTO> dtos = userService.findUsers();
        //Pentru fiecare persoana, Link cu getPerson unde primeste id;
        //Se adauga la lista;
        //link are si el paranteza;
        //Add a link;
        for (UserDTO dto : dtos) {
            Link userLink = linkTo(methodOn(UserController.class)
                    .getUser(dto.getId())).withRel("UserDetails!");
            //Pentru link: rel + href!
            //getUser(dto.getId(), dto.getId()))
            dto.add(userLink);
        }

        //Cu DTOS returnezi:
        //Returneaza lista de persoane, cu Status OK;
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    //No parametrii:
    //Aceeasi functie pentru ADMIN, USER, CLIENT, DEVICES;
    //In Devices folosesc tot functia de aici pentru a stii ce user este logat; (Sincronizat)
    @GetMapping(value = "/userRole")
    public ResponseEntity<String> getUserRole()
    {

        //Link userLink = linkTo(methodOn(UserController.class).getUser(dto.getId())).withRel("UserDetails!");
        //dto.add(userLink);
        //Doar return la rol, pentru a verifica in front end ce este!!!
        String userRole = currentUser.getRole();

        return new ResponseEntity<>(gson.toJson(userRole), HttpStatus.OK);
    }



    //Get pentru datele de luat;
    //Nici un argument dat!
    //Te poti uita la URL-uri, iti zice ce eroare da;
    @GetMapping(value = "/clientData")
    public ResponseEntity<UserDataDTO> getUserData()
    {
        //Datele userului curent:
        UserDataDTO userDataDTO = new UserDataDTO(currentUser.getName(), currentUser.getEmail(),
                currentUser.getAge(), currentUser.getAddress());

        System.out.println("Name: " + userDataDTO.getName() + " ,Email: " + userDataDTO.getEmail()
                + " ,Age" + userDataDTO.getAge() + " ,Address: " + userDataDTO.getAddress() + " !");

        //Cum se trimite un obiect? Asa, dupa se ia in result?
        //return new ResponseEntity<>(gson.toJson(userDataDTO), HttpStatus.OK);
        return new ResponseEntity<>(userDataDTO, HttpStatus.OK); //Doar obiect trimis!!!
    }



//    @GetMapping(value = "/userId")
//    public ResponseEntity<UUID> getUserId()
//    {
//        UUID userId = currentUser.getId();
//        //return new ResponseEntity<>(gson.toJson(userRole), HttpStatus.OK);
//        return new ResponseEntity<>(userId, HttpStatus.OK);
//    }




    @GetMapping(value = "/userName")
    public ResponseEntity<String> getUserName()
    {
        String userName = currentUser.getName();
        return new ResponseEntity<>(gson.toJson(userName), HttpStatus.OK);
    }



    @GetMapping(value = "/noRole")
    public ResponseEntity<String> getNoRole()
    {
        //Nu conteaza ce trimit, doar conteaza sa refac rolul in noRole!!!
        //Resetez tot userul, nu doar roleul:
        //ROL RESETAT!!!
        currentUser.setRole("noRole");
        //Trebuie ca noRole sa nu se activeze asa repede;

        //Nu trebuie id? Cred!!!
        //currentUser.setId("");
        currentUser.setName("InitialName");
        currentUser.setAddress("InitialAddress");
        currentUser.setAge(100);
        currentUser.setEmail("InitialEmail@Email.com");
        currentUser.setPassword("Passwor1-");

        String userRole = currentUser.getRole();

        return new ResponseEntity<>(gson.toJson(userRole), HttpStatus.OK);
    }



    //Get: /user/id;
    //Returneaza un ResponseDTO (Details)
    //Pentru o persoana, avem ID in Link;
    //@PathVariable("id") Probabil se creeaza aici?
    //INPUT - UUID / OUTPUT - USERDTO;
    //@PathVariable("id3") UUID userId, @PathVariable("id2") UUID userId2
    @GetMapping(value = "/{id}") ///{id}/{id2}
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable("id") UUID userId)
    {
        //DTO gasind din id!
        //Gaseste, returneaza un Person;
        UserDetailsDTO dto = userService.findUserById(userId);

        //Return one object, one user! Intregul DTO! Probabil cu id cu tot!
        //Returneaza un DTO, status OK;
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    //Post: Un tip de mapare;
    //Pentru Post, returnam UUID pentru confirmare;
    //Primeste un Person;
    //CTRL + SHIFT + F search;
    //Valida persoana;
    //INPUT - USERDTO / OUTPUT - UUID;
    //Inputul este defapt ce primesti in React;
    //Fara /;
    @PostMapping()
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody UserDetailsDTO userDTO)
    {
        //Insert la persoana, returneaza un UUID;
        UUID userID = userService.insert(userDTO);

        //Return ID daca chiar merge, doar in postman ajuta;
        //Returneaza ID, cu Status Created;
        return new ResponseEntity<>(userID, HttpStatus.CREATED); //Nu primeste si mesaje aici!
    }




    //Trimit tot UUID si la Update!
    @PostMapping(value = "/updateUser")
    public ResponseEntity<UUID> updateUser(@Valid @RequestBody UserDetailsDTO userDTO)
    {
        //Same id?
        //Foloseste cel cu ID?
        UserDetailsDTO dto = userService.findByName(userDTO.getName());

        //Aici ca sa nu mai caut cu find din nou: Same ID???
        dto.setAddress(userDTO.getAddress());
        dto.setAge(userDTO.getAge());
        dto.setEmail(userDTO.getEmail());
        dto.setPassword(userDTO.getPassword());
        dto.setRole(userDTO.getRole());

        System.out.println("ID De la DTO: " + dto.getId());

        //If we have user, update the current user with the new data:
        //Save merge dupa id, in service; Dupa id;
        UUID userID = userService.update(dto);

        return new ResponseEntity<>(userID, HttpStatus.OK);
    }



    //Primit tot name si password ca la Redirect!!!
    @PostMapping(value = "/deleteUser")
    public ResponseEntity<UUID> deleteUser(@Valid @RequestBody UserRoleRedirectDTO userRoleRedirectDTO)
    {
        //Un DTO: Pentru sters: Avem id pentru stergere:
        UserDetailsDTO dto = userService.findByNameAndPassword(userRoleRedirectDTO.getName(),
                userRoleRedirectDTO.getPassword());

        //Trebuie dat cascade pentru stergere!
        UUID userDeleteID = dto.getId();
        UUID userID = userService.delete(userDeleteID);

        return new ResponseEntity<>(userID, HttpStatus.OK);
    }




    //Accesata doar in background;
    //NotNull nu pune spatii frumoase la erori;
    //ResponseEntity<UUID>
    //(@Valid @RequestBody UserDetailsDTO userDTO)
    //Nu trebuie in FRONT END sa fie request pentru un tip de date?
    //Probabil nu, trimite json + primeste un obiect, care se converteste aici!!!
    @PostMapping(value = "/userRoleRedirect")
    public ResponseEntity<String> userRoleRedirect(@Valid @RequestBody UserRoleRedirectDTO userRoleRedirectDTO)
    {
        //Insert la persoana, returneaza un UUID;
        //UUID userID = userService.insert(userDTO);

        //Cele 2 luate:
        String userName = userRoleRedirectDTO.getName();
        String userPassword = userRoleRedirectDTO.getPassword();

        UserDetailsDTO dto = userService.findByNameAndPassword(userName, userPassword);

        //Salvare de la login al userului curent:
        //currentUser = dto; //Egal obiect! Nu cred ca conteaza;
        currentUser.setId(dto.getId());
        currentUser.setName(dto.getName());
        currentUser.setAddress(dto.getAddress());
        currentUser.setAge(dto.getAge());
        currentUser.setEmail(dto.getEmail());
        currentUser.setPassword(dto.getPassword());
        currentUser.setRole(dto.getRole());

        String userRole = dto.getRole(); //ROLUL NOU!!!

        //Return ID daca chiar merge, doar in postman ajuta;
        //Returneaza ID, cu Status Created;
        //return new ResponseEntity<>(userID, HttpStatus.CREATED); //Nu primeste si mesaje aici!
        //String transformat in Json pentru RESPONSE ENTITY!!!
        //Un alt format STRING care este acceptat de JSON, trebuie convertit altfel nu merge;
        return new ResponseEntity<>(gson.toJson(userRole), HttpStatus.OK); //<String>; //OK or CREATED; //userRole;
        //UUID este special fata de String?
    }


}











