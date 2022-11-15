package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.services.DeviceService;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin

//Nu are legatura cu User Role deloc acest controller;
@RequestMapping(value = "/device")
public class DeviceController
{
    private final DeviceService deviceService;
    //private final UserController userController;
    //Merge cu autowired? Merge si pe services, si pe controllere etc... pe orice! Interesant!

    @Autowired
    public DeviceController(DeviceService deviceService, UserController userController)
    {
        //Dependenta extra;
        this.deviceService = deviceService;
        //this.userController = userController;
    }

    //All GET:
    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices()
    {
        List<DeviceDTO> dtos = deviceService.findDevices();
        for (DeviceDTO dto : dtos) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(dto.getId())).withRel("deviceDetails");
            dto.add(deviceLink); //??
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



    //Same ideea, alt query: Id pentru un anumit user;
    //Path variable neaparat id;
    @GetMapping(value = "/clientDevices") ///{id}")
    //public ResponseEntity<List<DeviceDTO>> getClientDevices(@PathVariable("id") UUID userId)
    public ResponseEntity<List<DeviceDTO>> getClientDevices() //(@PathVariable("id") UUID id)
    {
        //Dependenta intre controllere:
        //In loc de dat id de la front end, iau id in backend!
        //UUID userIdBackend = userController;
        //Asa apelezi static: Direct din controller
        UUID userIdBackend = UserController.currentUser.getId();

        List<DeviceDTO> dtosClient = deviceService.findClientDevices(userIdBackend); //id

        //Linkul este bun:
        for (DeviceDTO dto : dtosClient) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(dto.getId())).withRel("deviceDetails");
            dto.add(deviceLink);
        }
        return new ResponseEntity<>(dtosClient, HttpStatus.OK);
    }



    //Post 1:
    //Mai trebuie adaugat user name!
    @PostMapping()
    public ResponseEntity<UUID> insertDevice(@Valid @RequestBody DeviceDTO deviceDTO)
    {
        //Insert si USER, dar DOAR DACA EXISTA!!! Altfel mare bai!
        //Este deja userul creat, vreau doar legatura;
        //Insert client:
        //Daca este null, insereaza null la client in isert;
        //Nu trebuie verificare aici;
        UUID deviceID = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }

    //Get 1:
    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") UUID deviceId)
    {
        DeviceDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}















