package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
import java.util.UUID;

//Details = Cu adresa in plus;
//Extends logica din baza de date;
public class PersonDTO extends RepresentationModel<PersonDTO> {
    //3 Campuri;
    //Nu scriem, doar citim, asa nu trebuie annotations;
    //Sunt private;
    private UUID id;
    private String name;
    private int age;

    //2 Constructors:
    public PersonDTO() {
    }
    public PersonDTO(UUID id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //All get + set;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //Same equals + hash;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return age == personDTO.age &&
                Objects.equals(name, personDTO.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}












