package ro.tuc.ds2022.tema1.OrsanTudor.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    //Unique!
    Optional<Device> findByTitle(String title);
    List<Device> findByHourlyConsumption(float hourlyConsumption);

    //Queryul este codul!
    //Trebuie spatiu altfel le lipeste:
    //Gasire toate deviceuri unde id-ul este acesta:
    //Gol daca nu!
    //Cum sunt in java, nu ca si sql! HQL!
    @Query(value = "SELECT d " +
            "FROM Device d " +
            "WHERE d.user.id = :id")
    List<Device> findAllClientDevices(@Param("id") UUID id); //Doar aici trebuie id!!!
    //List<Device> findAllClientDevices(@Param("id") UUID userId);


    //Returnez LIST nu Optional:
    @Query(value = "SELECT d " +
            "FROM Device d " +
            "WHERE d.user.id = :userId " +
            "AND d.title = :deviceTitle")
    Optional<Device> findByTitleAndUserId(@Param("deviceTitle") String deviceTitle,
                                      @Param("userId") UUID userId);


    /*
    @Query(value = "SELECT u " +
            "FROM User u " +
            "WHERE u.name = :name " +
            "AND u.age >= 50  ")
    Optional<Device> find(@Param("name") String name);
    */
}















