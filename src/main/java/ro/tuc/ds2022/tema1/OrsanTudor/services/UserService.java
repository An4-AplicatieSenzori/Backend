package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.UserBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.UserRepository;
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
        return userList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    //Dai id, primesti Details Person;
    public UserDetailsDTO findUserById(UUID id) {
        //Iara predefinita, primesti un entity;
        //Optional pentru ca se poate negasi;
        Optional<User> userOptional = userRepository.findById(id);

        //isPresent de la Optional, pentru a vedea daca exista sau nu;
        if (!userOptional.isPresent()) {
            //Mesaj eroare:
            LOGGER.error("User with id {} was not found in the db!", id);
            //Exceptie de mai sus;
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id + " ;");
        }

        //A luat resursa, a transformat-o in DTO pentru return;
        return UserBuilder.toUserDetailsDTO(userOptional.get());
    }

    //Primesti UUID la inserat, dai detaliile persoanei;
    public UUID insert(UserDetailsDTO userDTO) {
        //Convertesti din DTO in ENTITY;
        User user = UserBuilder.toUserEntity(userDTO);
        //Update + Insert : Persoana isi da Update;
        user = userRepository.save(user);
        //Mesaj:
        LOGGER.debug("User with id {} was inserted in the db!", user.getId());
        //Returnarea id-ului;
        return user.getId();
    }
}


























