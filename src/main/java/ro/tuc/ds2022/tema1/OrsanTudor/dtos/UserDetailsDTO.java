package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.annotation.AgeLimit;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

//DTO in loc de ENTITY;
//Se folosesc NotNull etc... doar la Validate;
public class UserDetailsDTO {

    //UUID, Name, Address, Age;
    private UUID id;
    //Addnotations pentru scriere corecta;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;

    @NotNull
    private String email;

    @NotNull
    private String password;

    //Constructor gol;
    public UserDetailsDTO() {
    }

    //Constructor fara UUID;
    public UserDetailsDTO(String name, String address, int age, String email, String password) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    //Constructor cu UUID;
    public UserDetailsDTO(UUID id, String name, String address, int age, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.password = password;
    }


    //All get+set;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    //Equal pentru obiecte;
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsDTO that = (UserDetailsDTO) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    //HashCode pentru HashMap;
    @Override
    public int hashCode() {
        return Objects.hash(name, address, age, email, password);
    }
}























