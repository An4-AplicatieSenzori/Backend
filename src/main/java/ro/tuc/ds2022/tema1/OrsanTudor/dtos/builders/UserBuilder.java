package ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders;

//Details and DTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;

//Clasa simpla:
public class UserBuilder {

    //Constructor simplu;
    private UserBuilder() {
    }

    //Din Entity Person in Dto Person;
    //Static;
    public static UserDTO toUserDTO(User user) {
        //Cele 3, fara adresa; Un intreg UserDTO;
        return new UserDTO(user.getId(), user.getName(), user.getAge(),
                user.getAddress(), user.getEmail());
    }

    //Din Entity Person in Dto Details Person;
    public static UserDetailsDTO toUserDetailsDTO(User user) {
        //Si cu adresa;
        //Un intreg user details:
        return new UserDetailsDTO(user.getId(), user.getName(), user.getAddress(), user.getAge(),
                user.getEmail(), user.getPassword(), user.getRole());
    }

    //Din Details in Entity;
    //Fara ID, dam toate;
    public static User toUserEntity(UserDetailsDTO userDetailsDTO) {
        //Un intreg user;
        return new User(userDetailsDTO.getName(),
                userDetailsDTO.getAddress(),
                userDetailsDTO.getAge(),
                userDetailsDTO.getEmail(),
                userDetailsDTO.getPassword(),
                userDetailsDTO.getRole()
                );
    }
}














