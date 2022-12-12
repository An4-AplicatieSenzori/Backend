package ro.tuc.ds2022.tema1.OrsanTudor.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.tema1.OrsanTudor.controllers.DeviceEnergyController;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDataDTO;

@Component
public class QueueListener {
    private static final Logger logDeviceData = LoggerFactory.getLogger(QueueListener.class);

    @RabbitListener(queues = "queue.q")
    public void listen(String payloadDeviceData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules(); //Ups;
        DeviceDataDTO deviceDataDTO = objectMapper.readValue(payloadDeviceData, DeviceDataDTO.class);

        //Voi adauga la lista din celalalt controller data primita:
        //De fiecare data cand primesc o data, o adaug in lista din controller: Si primesc un mesaj de citire cand asculta;
        DeviceEnergyController.DeviceEnergyDataList.add(deviceDataDTO);

        logDeviceData.info("Mesajul citit de la AMQP:" + payloadDeviceData);
    }
}



