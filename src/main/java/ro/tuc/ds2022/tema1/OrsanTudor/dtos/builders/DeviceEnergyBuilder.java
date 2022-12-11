package ro.tuc.ds2022.tema1.OrsanTudor.dtos.builders;

import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceDataDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.DeviceEnergyDTO;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.DeviceEnergy;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.User;

public class DeviceEnergyBuilder {

    //Constructor privat:
    private DeviceEnergyBuilder() {
    }

    //Device Energy to Device Energy DTO:
    public static DeviceEnergyDTO toDeviceEnergyDTO(DeviceEnergy deviceEnergy) {
        return new DeviceEnergyDTO(deviceEnergy.getId(), deviceEnergy.getValue(),
                deviceEnergy.getDayPlusHourSelected());
        //deviceEnergy.getDeviceId());
    }

    //Avem un device, trebuie pentru foreign key:
    public static DeviceEnergy toDeviceEnergyEntity(DeviceEnergyDTO deviceEnergyDTO, Device device) {
        return new DeviceEnergy(deviceEnergyDTO.getValue(),
                deviceEnergyDTO.getDayPlusHourSelected(),
                device
        );
    }

    //Pentru cand trimite producer-ul un device energy:
    public static DeviceEnergy toDeviceEnergyEntity2(DeviceDataDTO deviceDataDTO, float deviceDataSum, Device device) {
        return new DeviceEnergy(Math.round(deviceDataSum),
                deviceDataDTO.getCurrentTime(),
                device
        );
    }

    //Altul ar fi toDeviceEnergyWithID; (Pentru Update)
}
