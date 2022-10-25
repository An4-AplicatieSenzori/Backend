package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.PersonBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Person;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//Clasa normala, Service;
@Service
public class PersonService {

    //Loger si Repo;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    //Autowired si pentru repo;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Pentru gasire persoane:
    //Returneaza lista cu toate persoanele;
    public List<PersonDTO> findPersons() {
        //FindAll predefinit;
        List<Person> personList = personRepository.findAll();

        //Stream, unde: Din entities personList, transformi in DTOS;
        //Colectezi in o lista;
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    //Dai id, primesti Details Person;
    public PersonDetailsDTO findPersonById(UUID id) {
        //Iara predefinita, primesti un entity;
        //Optional pentru ca se poate negasi;
        Optional<Person> prosumerOptional = personRepository.findById(id);

        //isPresent de la Optional, pentru a vedea daca exista sau nu;
        if (!prosumerOptional.isPresent()) {
            //Mesaj eroare:
            LOGGER.error("Person with id {} was not found in db.", id);
            //Exceptie de mai sus;
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id + " ;");
        }

        //A luat resursa, a transformat-o in DTO pentru return;
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    //Primesti UUID la inserat, dai detaliile persoanei;
    public UUID insert(PersonDetailsDTO personDTO) {
        //Convertesti din DTO in ENTITY;
        Person person = PersonBuilder.toEntity(personDTO);
        //Update + Insert : Persoana isi da Update;
        person = personRepository.save(person);
        //Mesaj:
        LOGGER.debug("Person with id {} was inserted in db.", person.getId());
        //Returnarea id-ului;
        return person.getId();
    }
}


























