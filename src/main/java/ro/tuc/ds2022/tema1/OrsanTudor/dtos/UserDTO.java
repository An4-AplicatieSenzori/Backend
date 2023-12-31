package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import org.springframework.hateoas.RepresentationModel;
import java.util.Objects;
import java.util.UUID;

//Details = Cu adresa in plus;
//Extends logica din baza de date;
public class UserDTO extends RepresentationModel<UserDTO> {
    //3 Campuri;
    //Nu scriem, doar citim, asa nu trebuie annotations;
    //Sunt private;
    private UUID id;
    private String name;
    private int age;
    private String address;
    private String email;

    //2 Constructors:
    public UserDTO() {
    }
    public UserDTO(UUID id, String name, int age, String address, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Same equals + hash;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return age == userDTO.age &&
                Objects.equals(name, userDTO.name) &&
                Objects.equals(address, userDTO.address) &&
                Objects.equals(email, userDTO.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, email);
    }
}












