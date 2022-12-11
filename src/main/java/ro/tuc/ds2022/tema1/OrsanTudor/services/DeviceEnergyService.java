package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDataDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceEnergyDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceEnergyBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.DeviceEnergy;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;
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

    //In device este id user; Deci pot verifica daca este acel user;
    private final DeviceRepository deviceRepository;
    private final DeviceEnergyRepository deviceEnergyRepository;



    @Autowired
    public DeviceEnergyService(DeviceEnergyRepository deviceEnergyRepository, DeviceRepository deviceRepository) {
        this.deviceEnergyRepository = deviceEnergyRepository;
        this.deviceRepository = deviceRepository;
    }


    //Trimit un obiect de tip DeviceData, asa cum il face Message Producer;
    public UUID insert(DeviceDataDTO deviceDataDTO, float deviceDataSum)
    {
        //System.out.println("Device data: " + deviceDataDTO.getDeviceID());
        //Pentru insert, avem deja ID Device, se mai genereaza doar ID DeviceEnergy;

        //Obiect de tip DeviceEnergy:
        DeviceEnergy deviceEnergy = new DeviceEnergy();

        //Voi lua device pentru a il insera:
        Optional<Device> deviceOptional = deviceRepository.findById(deviceDataDTO.getDeviceID());
        if (!deviceOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in the db!", deviceDataDTO.getDeviceID());
            throw new ResourceNotFoundException(DeviceEnergy.class.getSimpleName() +
                    " with id: " + deviceDataDTO.getDeviceID() + " was not found!");
        }

        //Schimbat din tip DeviceDataDTO in Entity;
        //Nu voi face schimbari din alte DTO sau din Entity, voi face doar aceasta;
        //Nu voi pune conditii de not null sau de RepresentationModel;
        //Aici doar trebuie inserat, datele de intrare sigur sunt bune, sunt luate din CSV nu de la USER;
        deviceEnergy = DeviceEnergyBuilder.toDeviceEnergyEntity2(deviceDataDTO, deviceDataSum, deviceOptional.get());

        deviceEnergy = deviceEnergyRepository.save(deviceEnergy);
        LOGGER.debug("Device Energy with id {} was inserted in the db!", deviceEnergy.getId());
        return deviceEnergy.getId();
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













