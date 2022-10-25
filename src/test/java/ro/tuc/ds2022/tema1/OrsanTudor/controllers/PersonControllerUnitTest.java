package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.tuc.ds2022.tema1.OrsanTudor.Ds2020TestConfig;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.services.PersonService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerUnitTest extends Ds2020TestConfig {

    //Mock obiect;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService service;

    //Doar un insert:
    @Test
    public void insertPersonTest() throws Exception {
        //ObjectMapper;
        ObjectMapper objectMapper = new ObjectMapper();

        //O persoana cu adresa;
        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", "Somewhere Else street", 22);

        //Ca un PostMan?
        //URL de la insert;
        //Type JSOn;
        //Expected = ce sa se intample, Creat sau BadRequest;
        mockMvc.perform(post("/person")
                .content(objectMapper.writeValueAsString(personDTO))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    //Testare cu Fail Age;
    @Test
    public void insertPersonTestFailsDueToAge() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", "Somewhere Else street", 17);

        //Same ideea, doar ca age < 18, deci fail incoming;
        mockMvc.perform(post("/person")
                .content(objectMapper.writeValueAsString(personDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    //Nu avem adresa, deci va da null;
    @Test
    public void insertPersonTestFailsDueToNull() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDetailsDTO personDTO = new PersonDetailsDTO("John", null, 17);

        mockMvc.perform(post("/person")
                .content(objectMapper.writeValueAsString(personDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}























