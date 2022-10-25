package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.annotation.AgeLimit;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

//DTO in loc de ENTITY;
//Se folosesc NotNull etc... doar la Validate;
public class PersonDetailsDTO {

    //UUID, Name, Address, Age;
    private UUID id;
    //Addnotations pentru scriere corecta;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @AgeLimit(limit = 18)
    private int age;

    //Constructor gol;
    public PersonDetailsDTO() {
    }

    //Constructor fara UUID;
    public PersonDetailsDTO( String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    //Constructor cu UUID;
    public PersonDetailsDTO(UUID id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
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


    //Equal pentru obiecte;
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetailsDTO that = (PersonDetailsDTO) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    //HashCode pentru HashMap;
    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}























