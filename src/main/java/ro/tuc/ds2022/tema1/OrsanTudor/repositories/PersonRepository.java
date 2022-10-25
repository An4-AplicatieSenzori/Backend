package ro.tuc.ds2022.tema1.OrsanTudor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Jpa / Person + UUID;
//Este interfata pentru a putea apela in Service;
//Metodele CRUD basice exista deja;
//Pot crea cu PARAM;
//1 to n like PS; Convert DTO to Entity;
public interface PersonRepository extends JpaRepository<Person, UUID> {

    //Example: JPA generate Query by Field / Direct din denumire:
    List<Person> findByName(String name);


    //Example: Write Custom Query, daca nu merge numele generic;
    //Ii dai numele pentru query, dupa care returneaza un person, p, de gasit;
    //Optional?
    @Query(value = "SELECT p " +
            "FROM Person p " +
            "WHERE p.name = :name " +
            "AND p.age >= 50  ")
    Optional<Person> findSeniorsByName(@Param("name") String name);
}















