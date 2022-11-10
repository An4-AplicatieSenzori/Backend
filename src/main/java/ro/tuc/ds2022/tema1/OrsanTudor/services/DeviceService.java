package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.DeviceRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository; //Final!

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    //Get All Devices:
    public List<DeviceDTO> findDevices() {
        //Lista de device entities:
        List<Device> deviceList = deviceRepository.findAll();
        //Convertire cu builder:
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    //Get 1 Device:
    public DeviceDTO findDeviceById(UUID id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);

        //Daca este prezent doar:
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in the db!", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() +
                    " with id: " + id + " was not found!");
        }
        //Return DTO cu builder:
        return DeviceBuilder.toDeviceDTO(deviceOptional.get());
    }

    //Insert 1 Device:
    public UUID insert(DeviceDTO deviceDTO) {
        //Ia device-ul entity din DTO;
        Device device = DeviceBuilder.toDeviceEntity(deviceDTO);
        //Il salveaza (ca un update):
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in the db!", device.getId());
        //Returneaza id-ul celui inserat;
        return device.getId();
    }
}


























