package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserDataDTO
{
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private int age;
    @NotNull
    private String address;

    //Constructor gol;
    public UserDataDTO() {
    }

    public UserDataDTO(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataDTO that = (UserDataDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                age == that.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age, address);
    }
}
























