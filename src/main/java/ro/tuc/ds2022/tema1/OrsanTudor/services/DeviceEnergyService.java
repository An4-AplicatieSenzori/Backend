package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceEnergyDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceEnergyBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.DeviceEnergy;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.DeviceEnergyRepository;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.DeviceRepository;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceEnergyService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceEnergyService.class);

    private final DeviceRepository deviceRepository;
    private final DeviceEnergyRepository deviceEnergyRepository;



    @Autowired
    public DeviceEnergyService(DeviceEnergyRepository deviceEnergyRepository, DeviceRepository deviceRepository) {
        this.deviceEnergyRepository = deviceEnergyRepository;
        this.deviceRepository = deviceRepository;
    }



    //Pentru luat toate entry-urile:
    public List<DeviceEnergyDTO> findEnergyDevices()
    {
        List<DeviceEnergy> deviceEnergyList = deviceEnergyRepository.findAll();
        return deviceEnergyList.stream()
                .map(DeviceEnergyBuilder::toDeviceEnergyDTO)
                .collect(Collectors.toList());
    }

    public DeviceEnergyDTO findDeviceEnergyById(UUID id) {
        Optional<DeviceEnergy> deviceEnergyOptional = deviceEnergyRepository.findById(id);

        if (!deviceEnergyOptional.isPresent()) {
            LOGGER.error("Device Energy with id {} was not found in the db!", id);
            throw new ResourceNotFoundException(DeviceEnergy.class.getSimpleName() +
                    " with id: " + id + " was not found!");
        }

        return DeviceEnergyBuilder.toDeviceEnergyDTO(deviceEnergyOptional.get());
    }


    //Restul metodelor:
    public List<DeviceEnergyDTO> findByDeviceTitle(String deviceTitle)
    {
        //Gasesc Device dupa nume, din Device Repo, pentru ca asa dau autowired la 2 repo-uri;
        //Duplicated 1 linie: Eficienta pura:
        Optional<Device> deviceOptional = deviceRepository.findByTitle(deviceTitle);

        //Daca este prezent in tabela lui:
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with title {} was not found in the db!", deviceTitle);
            throw new ResourceNotFoundException(Device.class.getSimpleName()
                    + " with title: " + deviceTitle + " was not found!");
        }


        //SA APARTINA CUM TREBE:
        //!!!
        //SA FIE A USERULUI DAT!!! (Userul luat din front end sau din alt controller!!!)
        //!!!


        //deviceOptional este Device;
        //deviceDTO      este DTO   ;
        //DeviceDTO deviceDTO = DeviceBuilder.toDeviceDTO(deviceOptional.get());

        //Am obtinut id-ul, daca exista;
        UUID deviceID = deviceOptional.get().getId();

        //Gaseste entity, dupa transforma in DTO:
        List<DeviceEnergy> deviceEnergyList = deviceEnergyRepository.findByDeviceId(deviceID);

        //Trimite un DTO: Nu stiu daca cel bun, mai vad!!!
        return deviceEnergyList.stream()
                .map(DeviceEnergyBuilder::toDeviceEnergyDTO)
                .collect(Collectors.toList());
    }
}













