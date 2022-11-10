package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.DuplicateResourceException;
//Pus instant;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ParameterValidationException;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.UserBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//Clasa normala, Service;
@Service
public class UserService {

    //Loger si Repo;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    //Autowired si pentru repo;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    //Pentru gasire persoane:
    //Returneaza lista cu toate persoanele;
    public List<UserDTO> findUsers() {
        //FindAll predefinit;
        List<User> userList = userRepository.findAll();

        //Stream, unde: Din entities personList, transformi in DTOS;
        //Colectezi in o lista;
        //Stream cu mapare;
        return userList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }



    //Dai id, primesti Details Person;
    //UserById and ById;
    public UserDetailsDTO findUserById(UUID id) {
        //Iara predefinita, primesti un entity;
        //Optional pentru ca se poate negasi;
        //Vezi daca il gaseste cu isPresent;
        Optional<User> userOptional = userRepository.findById(id);

        //getSimpleName() pentru class?
        //isPresent de la Optional, pentru a vedea daca exista sau nu;
        //Nu stiu unde da throw;
        if (!userOptional.isPresent()) {
            //Mesaj eroare:
            //LOGGERS PENTRU MESAJE IN CONSOLA;
            LOGGER.error("User with id {} was not found in the db!", id);
            //Exceptie de mai sus;
            //THROW LA REQUEST, PENTRU A VEDEA CINE DOREA RESURSA CE SCRIE!!!
            //Nu da extend la nimic, doar throw cu un mesaj anume!
            throw new ResourceNotFoundException(User.class.getSimpleName()
                    + " with id: " + id + " was not found!");
            //Daca ar fi de inserat dupa acesta, ar face? Ar trebui else mai jos;
        }

        //A luat resursa, a transformat-o in DTO pentru return;
        //Get din Optional!
        return UserBuilder.toUserDetailsDTO(userOptional.get());
    }



    public UserDetailsDTO findByNameAndPassword(String name, String password)
    {
        Optional<User> userOptional = userRepository.findByNameAndPassword(name, password);

        //Nu stiu daca pune parola aici, daca nu il gaseste oriucm nu era parola buna!
        //In Logger poti trimite mai multi parametrii!!!
        if (!userOptional.isPresent()) {
            LOGGER.error("User with name and password {} was not found in the db!", name, password);
            throw new ResourceNotFoundException(User.class.getSimpleName()
                    + " with name and password: " + name + " , " + password + " was not found!");
        }

        return UserBuilder.toUserDetailsDTO(userOptional.get());
    }



    //Primesti UUID la inserat, dai detaliile persoanei;
    public UUID insert(UserDetailsDTO userDetailsDTO) {

        //Convertesti din DTO in ENTITY;
        User user = UserBuilder.toUserEntity(userDetailsDTO);
        UUID id = user.getId();

        //Update + Insert : Persoana isi da Update;
        //Insert in sine:
        user = userRepository.save(user);


        //Asa nu mai zice de unreachable;
        //Throw nu opreste functionalitatea!
        /*
        if(true)
        {
            //Test throw exception 1: Duplicate:
            throw new DuplicateResourceException(User.class.getSimpleName()
                    + " with id: " + id + " is already in the list!");

            ArrayList<String> listaErori = new ArrayList<>(); //ArrayList<String>("a", "d");
            listaErori.add("Error 1");
            listaErori.add("Error 2");
            listaErori.add("Error 3");
            //Test throw exception 2: Parameter Validation:
            throw new ParameterValidationException(User.class.getSimpleName()
                    + " with id: " + id + " does not have the right parameters!",
                    listaErori); //ArrayList peste List;
                    //new ArrayList<String>("Error 1", "Error 2", "Error 3"));
        }
        //return;
        */

        //Mesaj:
        //In toate cazurile poate, din front end stabilit!
        LOGGER.debug("User with id {} was inserted in the db!", user.getId());
        //Returnarea id-ului;
        return user.getId();
    }
}


























