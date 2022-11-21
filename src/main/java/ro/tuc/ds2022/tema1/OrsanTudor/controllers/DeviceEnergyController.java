package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceEnergyDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.services.DeviceEnergyService;
import ro.tuc.ds2022.tema1.OrsanTudor.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@CrossOrigin
@RequestMapping(value = "/deviceEnergy")
public class DeviceEnergyController
{
    private final DeviceEnergyService deviceEnergyService;
    private final DeviceService deviceService;


    @Autowired
    public DeviceEnergyController(DeviceEnergyService deviceEnergyService,
                                  DeviceService deviceService)
    {
        this.deviceEnergyService = deviceEnergyService;
        this.deviceService = deviceService;
    }



    @GetMapping()
    public ResponseEntity<List<DeviceEnergyDTO>> getEnergyDevices()
    {
        List<DeviceEnergyDTO> dtos = deviceEnergyService.findEnergyDevices();
        for (DeviceEnergyDTO dto : dtos) {
            Link deviceLink = linkTo(methodOn(DeviceEnergyController.class)
                    .getDeviceEnergy(dto.getId())).withRel("deviceEnergyDetails");
            dto.add(deviceLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //NU STIE CARE SA IA DIN ASTEA?
    //Este necesara asta pentru cea de mai sus:
    //AICI LAS BASIC, NU PUN ID, DOAR JOS MAI SCHIMB!!!
    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceEnergyDTO> getDeviceEnergy(@PathVariable("id") UUID deviceEnergyId)
    {
        DeviceEnergyDTO dto = deviceEnergyService.findDeviceEnergyById(deviceEnergyId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //Restul metodelor:
    //Return a list of all data usage of a device from a day;
    //Trimis un String:
    //Stie sa transforme prin serializare in STRING acel Device Title!!!
    @GetMapping(value = "/deviceTitle/{deviceTitle}")
    public ResponseEntity<List<DeviceEnergyDTO>> getDeviceData(@PathVariable("deviceTitle") String deviceTitle)
    {
        //Access la userul curent, la id-ul sau:
        UUID userIdBackend = UserController.currentUser.getId();

        //Trebuie verificat daca device-ul are acest user sau nu!!!
        //Trimit String device and ID user si verific daca exista asa ceva in BD;
        //Daca exista, trimit mai departe si merge codul normal, daca nu, exceptie;
        //Apel, nu conteaza stocarea!!! Va da server error before!!!
        DeviceDTO deviceFromUser = deviceService.findByTitleAndUserID(deviceTitle, userIdBackend);

        //Filtrare dupa titlu, un query special:
        List<DeviceEnergyDTO> dtoList = deviceEnergyService.findByDeviceTitle(deviceTitle);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}

















