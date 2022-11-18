///*
package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.tuc.ds2022.tema1.OrsanTudor.Ds2020TestConfig;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.services.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerUnitTest extends Ds2020TestConfig {



    //Mock obiect;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;



    //Doar un insert:
    @Test
    public void insertUserTest() throws Exception {
        //ObjectMapper;
        ObjectMapper objectMapper = new ObjectMapper();

        //O persoana cu adresa;
        UserDetailsDTO userDTO = new UserDetailsDTO("John", "Somewhere Else street",
                22, "John@yahoo.com", "PasswordForJohn", "RoleForJohn");
        //"John@yahoo.com", "PasswordForJohn", "RoleForJohn"

        //Ca un PostMan?
        //URL de la insert;
        //Type JSOn;
        //Expected = ce sa se intample, Creat sau BadRequest;
        mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }



    //Testare cu Fail Age;
    @Test
    public void insertUserTestFailsDueToAge() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetailsDTO userDTO = new UserDetailsDTO("John", "Somewhere Else street",
                17, "John@yahoo.com", "PasswordForJohn", "RoleForJohn");

        //Same ideea, doar ca age < 18, deci fail incoming;
        mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }



    //Nu avem adresa, deci va da null;
    @Test
    public void insertUserTestFailsDueToNull() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetailsDTO userDTO = new UserDetailsDTO("John", null,
                17, "John@yahoo.com", "PasswordForJohn", "RoleForJohn");

        mockMvc.perform(post("/user")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
//*/























