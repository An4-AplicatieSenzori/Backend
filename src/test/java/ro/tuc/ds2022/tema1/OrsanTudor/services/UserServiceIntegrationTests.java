package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2022.tema1.OrsanTudor.Ds2020TestConfig;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import java.util.List;
import java.util.UUID;

//Create + Delete, ce sa se intample BEFORE and AFTER executie;
//Creeam, ca dupa teste sa dam Delete;
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class UserServiceIntegrationTests extends Ds2020TestConfig {

    //Serviciu: Pentru executare;
    @Autowired
    UserService userService;

    //Pentru 1 persoana, daca ai, sa fie equal to 1 in assert;
    @Test
    public void testGetCorrect() {
        List<UserDTO> userDTOList = userService.findUsers();
        assertEquals("Test 'Insert' User", 1, userDTOList.size());
    }

    //Inseram + Luam o persoana; Vrem ca ce am inserat sa fie ce am gasit cu acel ID, asta asertam, comparare 2 obiecte;
    @Test
    public void testInsertCorrectWithGetById() {
        UserDetailsDTO u = new UserDetailsDTO("John", "Somewhere Else street",
                22, "John@yahoo.com", "PasswordForJohn");
        UUID insertedID = userService.insert(u);
        UserDetailsDTO insertedUser = new UserDetailsDTO(insertedID, u.getName(), u.getAddress(),
                u.getAge(), u.getEmail(), u.getPassword());
        UserDetailsDTO fetchedUser = userService.findUserById(insertedID);
        assertEquals("Test Inserted User", insertedUser, fetchedUser);
    }

    //Mai inserezi o persoana, acum ai 2, si vrei sa testezi size sa fie 2;
    @Test
    public void testInsertCorrectWithGetAll() {
        UserDetailsDTO u = new UserDetailsDTO("John", "Somewhere Else street",
                22, "John@yahoo.com", "PasswordForJohn");
        userService.insert(u);
        List<UserDTO> userDTOList = userService.findUsers();
        assertEquals("Test Inserted Users", 2, userDTOList.size());
    }
}
















