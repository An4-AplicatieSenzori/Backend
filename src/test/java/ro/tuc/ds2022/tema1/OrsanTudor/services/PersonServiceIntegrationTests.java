package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2022.tema1.OrsanTudor.Ds2020TestConfig;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.PersonDetailsDTO;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import java.util.List;
import java.util.UUID;

//Create + Delete, ce sa se intample BEFORE and AFTER executie;
//Creeam, ca dupa teste sa dam Delete;
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class PersonServiceIntegrationTests extends Ds2020TestConfig {

    //Serviciu: Pentru executare;
    @Autowired
    PersonService personService;

    //Pentru 1 persoana, daca ai, sa fie equal to 1 in assert;
    @Test
    public void testGetCorrect() {
        List<PersonDTO> personDTOList = personService.findPersons();
        assertEquals("Test 'Insert' Person", 1, personDTOList.size());
    }

    //Inseram + Luam o persoana; Vrem ca ce am inserat sa fie ce am gasit cu acel ID, asta asertam, comparare 2 obiecte;
    @Test
    public void testInsertCorrectWithGetById() {
        PersonDetailsDTO p = new PersonDetailsDTO("John", "Somewhere Else street", 22);
        UUID insertedID = personService.insert(p);
        PersonDetailsDTO insertedPerson = new PersonDetailsDTO(insertedID, p.getName(),p.getAddress(), p.getAge());
        PersonDetailsDTO fetchedPerson = personService.findPersonById(insertedID);
        assertEquals("Test Inserted Person", insertedPerson, fetchedPerson);
    }

    //Mai inserezi o persoana, acum ai 2, si vrei sa testezi size sa fie 2;
    @Test
    public void testInsertCorrectWithGetAll() {
        PersonDetailsDTO p = new PersonDetailsDTO("John", "Somewhere Else street", 22);
        personService.insert(p);
        List<PersonDTO> personDTOList = personService.findPersons();
        assertEquals("Test Inserted Persons", 2, personDTOList.size());
    }
}
















