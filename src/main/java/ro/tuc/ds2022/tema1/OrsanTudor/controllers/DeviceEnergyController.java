package ro.tuc.ds2022.tema1.OrsanTudor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDataDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceEnergyDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.UserDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.services.DeviceEnergyService;
import ro.tuc.ds2022.tema1.OrsanTudor.services.DeviceService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@CrossOrigin
@RequestMapping(value = "/deviceEnergy")
@EnableScheduling
public class DeviceEnergyController
{
    private final DeviceEnergyService deviceEnergyService;

    //Si acest service;
    private final DeviceService deviceService;

    //Metoda folosita si inainte:
    public static ArrayList<DeviceDataDTO> DeviceEnergyDataList = new ArrayList<DeviceDataDTO>();


    @Autowired
    public DeviceEnergyController(DeviceEnergyService deviceEnergyService,
                                  DeviceService deviceService)
    {
        this.deviceEnergyService = deviceEnergyService;
        this.deviceService = deviceService;
    }


    //Pentru procesarea datelor:
    //@Scheduled(fixedRate = 5000)
    //Pentru fiecare minut:
    //Trebuie 6 fields, adica secunda, minut, ora, zi luna, luna, zi saptamana;
    //Din cealalta aplicatie, se trimit date o data la 10 secunde de la pornirea ei;
    //Aici se proceseaza datele la fiecare minut fix, si se adauga in baza de date ca au fost procesate datele;

    //1) Pentru fiecare minut exact, 00;
    @Scheduled(cron = "0 * * * * *")
    //2) Pentru la fiecare 10 secunde:
    //@Scheduled(cron = "*/10 * * * * *")
    public void ProcessDeviceData()
    {
        //Procesarea datelor:
        System.out.println("Procesarea datelor din lista de energy device: ");



        //Test:
        //int contor = 1;
        //for(DeviceDataDTO deviceDataDTO: DeviceEnergyDataList)
        //{
        //    //Afisez pentru fiecare:
        //    System.out.println("Obiectul " + contor + " : " + deviceDataDTO);
        //    contor++;
        //}




        //Stocarea in BD in functie de cate date avem:
        //a)
        //Aranjare in functie de timp, la ce timpi am primit datele:
        //Si la final inserare in BD a datelor bune:
        //Nu este din GUI inserare pentru tabela deviceEnergy, deci voi crea direct in Service insertul!
        //Doar inserare, nu alte conditii;

        //Date test:
        //DeviceDataDTO deviceDataDTO = new DeviceDataDTO(UUID.fromString("58489f9d-8d15-41e3-938d-43eae67e924f"),
        //        LocalDateTime.now(), 5.0f);
        //float deviceDataSum = 6.0f;

        //Gasirea inserturilor:

        //Lista pentru toate inserturile dorite:
        //Creez obiectul si folosesc acea suma pentru a o trimite mai departe: (Refolosesc un camp in mod gresit dar asa nu ma complic)
        ArrayList<DeviceDataDTO> deviceDataInsertList = new ArrayList<DeviceDataDTO>();
        //float[] deviceDataSum;

        //Pentru fiecare element din lista primita de listen:
        for(DeviceDataDTO deviceDataDTO: DeviceEnergyDataList)
        {
            //Intre 0 si 59 minute:
            //deviceDataDTO.getCurrentTime().getMinute()

            //Verific daca am un minut nou:
            int flag = 0;
            for(DeviceDataDTO deviceDataInsertElement: deviceDataInsertList)
            {
                if(deviceDataDTO.getCurrentTime().getMinute() == deviceDataInsertElement.getCurrentTime().getMinute())
                {
                    //Nu voi insera nimic;
                    flag = 1;
                    break;
                }
            }

            //In functie de daca am sau nu minut nou:
            if(flag == 0)
            {
                //Inseamna ca am un minut nou:
                //LocalDateTime exactTime = deviceDataDTO.getCurrentTime();
                //Voi lua minutul exact:
                LocalDateTime exactTime = deviceDataDTO.getCurrentTime().truncatedTo(ChronoUnit.MINUTES);
                deviceDataInsertList.add(new DeviceDataDTO(deviceDataDTO.getDeviceID(),
                        exactTime, deviceDataDTO.getValue()));
            }
            else
            {
                //Daca nu am un minut nou, adaug la minutul ce era: Il regasesc, dupa adaug;
                int indexList = 0;
                for(DeviceDataDTO deviceDataInsertElement: deviceDataInsertList)
                {
                    if(deviceDataDTO.getCurrentTime().getMinute() == deviceDataInsertElement.getCurrentTime().getMinute())
                    {
                        //Adaug la suma de la indexul:
                        //indexList++;
                        break;
                    }
                    indexList++;
                }

                //Acum schimb pentru acel element suma:
                float currentSum = deviceDataInsertList.get(indexList).getValue();
                deviceDataInsertList.get(indexList).setValue(currentSum + deviceDataDTO.getValue());
            }
        }

        //DeviceDataDTO deviceDataDTO = new DeviceDataDTO();
        //float deviceDataSum = 0.0f;
        //deviceEnergyService.insert(deviceDataDTO, deviceDataSum);
        //deviceEnergyService.insert(deviceDataDTO, deviceDataDTO.getValue());

        //Pentru fiecare element din lista de mai sus, il voi insera:
        for(DeviceDataDTO deviceDataInsertElement: deviceDataInsertList)
        {
            System.out.println("Pentru device-ul " + deviceDataInsertElement.getDeviceID() + " la data " + deviceDataInsertElement.getCurrentTime() + " am inserat in BD!");
            deviceEnergyService.insert(deviceDataInsertElement, deviceDataInsertElement.getValue());
        }



        //b)
        //Compararea cu valoarea maxima, pentru a stii daca am intrecut limita:
        for(DeviceDataDTO deviceDataInsertElement: deviceDataInsertList)
        {
            //Device-ul gaasit pentru valoarea maxima:
            DeviceDTO deviceDTO = deviceService.findDeviceById(deviceDataInsertElement.getDeviceID());
            float valueMax = deviceDTO.getHourlyConsumption();

            //Acum voi verifica pentru fiecare daca este sau nu mai mare:
            //Ambele floats:

            //Asa primul ar fi mai mare:
            //if(Float.compare(deviceDataInsertElement.getValue(), valueMax) >= 0) //Corect;
            if(Float.compare(valueMax, deviceDataInsertElement.getValue()) >= 0) //Gresit, dar pentru test;
            {
                //Test:
                System.out.println("Valoarea maxima este depasita pentru device-ul " + deviceDTO.getId() +
                        " la data " + deviceDataInsertElement.getCurrentTime() + " !");

                //Pentru WEB SOCKET:
            }
        }


        //Remove la lista: Acum am procesat toate datele ei!
        //Cand se apeleaza aceasta functie, se proceseaza tot, nu mai are rost sa lasi ceva in lista:
        DeviceEnergyDataList.clear();
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

















