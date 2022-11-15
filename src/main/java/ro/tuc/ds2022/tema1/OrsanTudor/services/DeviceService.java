package ro.tuc.ds2022.tema1.OrsanTudor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.DeviceRepository;
import ro.tuc.ds2022.tema1.OrsanTudor.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository; //Final!
    //Repo la user deja aici:
    private final UserRepository userRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    //Get All Devices:
    public List<DeviceDTO> findDevices() {

        //Lista de device entities:
        List<Device> deviceList = deviceRepository.findAll();
        //Convertire cu builder:
        //Daca ID Blob il ia diferit, nu gaseste bine?
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



    //public List<DeviceDTO> findClientDevices(UUID userId)
    public List<DeviceDTO> findClientDevices(UUID id)
    {
        //Vrem sa gasim lista de devices a unui user:
        //Direct id, select dupa el;
        //Lista?
        //Optional<Device> deviceOptional = deviceRepository.findById(userId);
        //Trebuie gasit ca UUID???
        List<Device> deviceClientList = deviceRepository.findAllClientDevices(id);

        //if (!deviceOptional.isPresent()) {
        if(!deviceClientList.isEmpty()){
            LOGGER.error("The client with id {} does not have any devices in the db!", id);
            //Fara throw, e bine si goala!
            //throw new ResourceNotFoundException("The user with id" +
            //        id + " does not have devices!");
            //Merge stream oare?
        }

        //Return DTO cu builder:
        //return DeviceBuilder.toDeviceDTO(deviceOptional.get());
        //Trebuie returnate transformate:
        //Ca mai sus:
        return deviceClientList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }


    //Insert 1 Device:
    public UUID insert(DeviceDTO deviceDTO) {
        //Ia device-ul entity din DTO;
        //In insert din deviceDto pune in Device;
        //Transmitere string bine:

        System.out.println("User name: " + deviceDTO.getUserName());

        //Aici primesti o lista de useri, gasiti dupa numele dat la insert;
        //List<User> users = userRepository.findByName(deviceDTO.getUserName());
        Optional<User> users = userRepository.findByName(deviceDTO.getUserName());
        //Daca gaseste ceva, il insereaza;
        //Daca nu gaseste, pune null;
        //users.get(0); //Numele gasit!

        User user = new User();

        //if(users.isEmpty())
        if(!users.isPresent())
        {
            user = null;
        }
        else {
            //user = users.get(0);
            user = users.get(); //Optional not a list;
        }

        Device device = DeviceBuilder.toDeviceEntity(deviceDTO, user);

        //Il salveaza (ca un update):
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in the db!", device.getId());
        //Returneaza id-ul celui inserat;
        return device.getId();
    }
}


























