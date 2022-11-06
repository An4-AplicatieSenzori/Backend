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

@RequestMapping(value = "/device")
public class DeviceController
{
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService)
    {
        this.deviceService = deviceService;
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

    //Post 1:
    @PostMapping()
    public ResponseEntity<UUID> insertDevice(@Valid @RequestBody DeviceDTO deviceDTO)
    {
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















