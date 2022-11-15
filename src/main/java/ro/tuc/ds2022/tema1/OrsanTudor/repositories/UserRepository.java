package ro.tuc.ds2022.tema1.OrsanTudor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Jpa / Person + UUID;
//Este interfata pentru a putea apela in Service;
//Metodele CRUD basice exista deja;
//Pot crea cu PARAM;
//1 to n like PS; Convert DTO to Entity;
public interface UserRepository extends JpaRepository<User, UUID> {

    //Toate optional?
    //Example: JPA generate Query by Field / Direct din denumire:
    //Unique, poate trebuie returnat doar unul;
    //Ia si ID cred!
    Optional<User> findByName(String name);
    //List<User> findByName(String name);

    //List<User> findByAge(int age);
    //List<User> findByAddress(String address);

    List<User> findByEmail(String email);
    //List<User> findByPassword(String password);

    //Probabil daca gaseste unul este destul;
    Optional<User> findByNameAndPassword(String name, String password);
    //User findByNameAndPassword(String name, String password);


    //Example: Write Custom Query, daca nu merge numele generic;
    //Ii dai numele pentru query, dupa care returneaza un person, p, de gasit;
    //Optional?
    /*
    @Query(value = "SELECT u " +
            "FROM User u " +
            "WHERE u.name = :name " +
            "AND u.age >= 50  ")
    Optional<User> findSeniorsByName(@Param("name") String name);
    */
}















