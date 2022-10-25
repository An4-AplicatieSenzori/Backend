package ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders;

//Details and DTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Person;

//Clasa simpla:
public class PersonBuilder {

    //Constructor simplu;
    private PersonBuilder() {
    }

    //Din Entity Person in Dto Person;
    //Static;
    public static PersonDTO toPersonDTO(Person person) {
        //Cele 3, fara adresa;
        return new PersonDTO(person.getId(), person.getName(), person.getAge());
    }

    //Din Entity Person in Dto Details Person;
    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        //Si cu adresa;
        return new PersonDetailsDTO(person.getId(), person.getName(), person.getAddress(), person.getAge());
    }

    //Din Details in Entity;
    //Fara ID, dam toate;
    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getName(),
                personDetailsDTO.getAddress(),
                personDetailsDTO.getAge());
    }
}














