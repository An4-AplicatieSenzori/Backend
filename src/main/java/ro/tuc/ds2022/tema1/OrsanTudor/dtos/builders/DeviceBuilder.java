package ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDetailsDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.DeviceRepository;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.UserRepository; //Dependenta noua;

public class DeviceBuilder
{
    //Ne trebuie repo care sa apeleze find pentru a cauta;
    //private final UserRepository userRepository; //Final!

    private DeviceBuilder() {
    }

    //Device -> DeviceDTO: Dam si ID-ul; (Avem constructor special)
    //Stie ca user are nume!
    public static DeviceDTO toDeviceDTO(Device device) {

        //Problema la link?
        if(device.getUser() == null)
        {
            return new DeviceDTO(device.getId(), device.getTitle(), device.getDescription(),
                    device.getAddress(), device.getHourlyConsumption(), "No Client");
        }
        else {
            return new DeviceDTO(device.getId(), device.getTitle(), device.getDescription(),
                    device.getAddress(), device.getHourlyConsumption(), device.getUser().getName());
        }
    }

    //ENTITY ARE DEJA OBIECT USER!!!
    //DeviceDTO -> Device: Toate inafara de ID;
    public static Device toDeviceEntity(DeviceDTO deviceDTO, User user) {
        return new Device(deviceDTO.getTitle(),
                deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getHourlyConsumption(),
                user
                );
    }

    public static Device toDeviceEntityWithID(DeviceDTO deviceDTO, User user) {
        return new Device(deviceDTO.getId(),
                deviceDTO.getTitle(),
                deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getHourlyConsumption(),
                user
        );
    }
}















