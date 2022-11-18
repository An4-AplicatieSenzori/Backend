package ro.tuc.ds2022.tema1.OrsanTudor.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.DeviceEnergy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Entitate, UUID;
public interface DeviceEnergyRepository extends JpaRepository<DeviceEnergy, UUID>{

    //Toate metodele de find:
    //Optional = ori 0, ori 1;
    //List daca se POATE sa ai mai multe!
    //Si list poate fi 0, trebuie verificat daca este empty!!!
    //Raman liste: Desi nu trebuie cautata ora exacta:

    List<DeviceEnergy> findByValue(int value);

    //findByDayAndHourSelected nu merge pentru ca And in nume nu merge bine la acest find!!!
    List<DeviceEnergy> findByDayPlusHourSelected(LocalDateTime dayPlusHourSelected);

    //Query pentru select in functie de zi, adica doar zi nu si ora:
    //Asa selectez tot consumul unui device per ziua aia!!! (24 entries in teorie!)

    @Query(value = "SELECT de " +
            "FROM DeviceEnergy de " +
            "WHERE de.device.id = :deviceID")
    List<DeviceEnergy> findByDeviceId(@Param("deviceID") UUID deviceID);
}








