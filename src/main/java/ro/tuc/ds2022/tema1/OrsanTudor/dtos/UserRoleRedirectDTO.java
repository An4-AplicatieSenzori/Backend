package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class UserRoleRedirectDTO
{
    //Si pentru acestea las not null!!!
    @NotNull
    private String name;
    @NotNull
    private String password;

    //Constructor gol;
    public UserRoleRedirectDTO() {
    }

    public UserRoleRedirectDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleRedirectDTO that = (UserRoleRedirectDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
























