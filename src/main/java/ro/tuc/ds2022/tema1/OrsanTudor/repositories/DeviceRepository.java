package ro.tuc.ds2022.tema1.OrsanTudor.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findByTitle(String title);
    List<Device> findByHourlyConsumption(float hourlyConsumption);

    /*
    @Query(value = "SELECT u " +
            "FROM User u " +
            "WHERE u.name = :name " +
            "AND u.age >= 50  ")
    Optional<Device> find(@Param("name") String name);
    */
}















