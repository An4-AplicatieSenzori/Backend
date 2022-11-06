package ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;

public class DeviceBuilder
{
    private DeviceBuilder() {
    }

    //Device -> DeviceDTO: Dam si ID-ul; (Avem constructor special)
    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getId(), device.getTitle(), device.getDescription(),
                device.getAddress(), device.getHourlyConsumption());
    }

    //DeviceDTO -> Device: Toate inafara de ID;
    public static Device toDeviceEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getTitle(),
                deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getHourlyConsumption()
                );
    }
}















