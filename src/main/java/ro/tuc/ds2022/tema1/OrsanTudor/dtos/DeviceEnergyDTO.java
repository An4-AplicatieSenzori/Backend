package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2022.tema1.OrsanTudor.entities.Device;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;



public class DeviceEnergyDTO extends RepresentationModel<DeviceEnergyDTO> implements Comparable<DeviceEnergyDTO>{

    //Fara constrangeri speciale:
    private UUID id;

    @NotNull
    private int value;

    @NotNull
    private LocalDateTime dayPlusHourSelected;

    //@NotNull
    //private Device device;
    //@NotNull
    //private String deviceTitle;

    public DeviceEnergyDTO(){

    }

    //Generate merge doar pentru repr model:
    public DeviceEnergyDTO(int value, LocalDateTime dayPlusHourSelected){
        this.value = value;
        this.dayPlusHourSelected = dayPlusHourSelected;
    }

    public DeviceEnergyDTO(UUID id, int value, LocalDateTime dayPlusHourSelected){
        this.id = id;
        this.value = value;
        this.dayPlusHourSelected = dayPlusHourSelected;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public LocalDateTime getDayPlusHourSelected() {
        return dayPlusHourSelected;
    }
    public void setDayPlusHourSelected(LocalDateTime dayPlusHourSelected) {
        this.dayPlusHourSelected = dayPlusHourSelected;
    }

    //Nu este id aici?
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEnergyDTO that = (DeviceEnergyDTO) o;
        return value == that.value &&
                dayPlusHourSelected.compareTo(that.dayPlusHourSelected) == 0;
        //hourlyConsumption == that.hourlyConsumption;
    }

    //Nici aici id:
    @Override
    public int hashCode() {
        return Objects.hash(value, dayPlusHourSelected);
    }


    //Pentru comparable:
    //Primul este ce este in obiectul in sine, a 2-a este obiectul trimis;
    @Override
    public int compareTo(DeviceEnergyDTO deviceEnergyDTO) {
        if (getDayPlusHourSelected() == null || deviceEnergyDTO.getDayPlusHourSelected() == null) {
            return 0; //Daca nu se pot compara;
        }

        LocalDateTime timeObj1 = getDayPlusHourSelected();
        LocalDateTime timeObj2 = deviceEnergyDTO.getDayPlusHourSelected();

        return timeObj1.compareTo(timeObj2);
    }
    //Collections.sort(deviceEnergyList); va sorta acum dupa data;
}






























