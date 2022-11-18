package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;



//Representation pe DTO:
public class DeviceDTO extends RepresentationModel<DeviceDTO> {

    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String description;
    @NotNull
    private String address;

    @NotNull
    private float hourlyConsumption;

    //String facut DOAR pentru afisare, nu si pentru functionare corecta!!!
    private String userName; //Aici doar numele, nu si obiectul!!!

    //3 Constructors:
    public DeviceDTO() {
    }

    public DeviceDTO(String title, String description, String address, float hourlyConsumption, String userName) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.hourlyConsumption = hourlyConsumption;
        this.userName = userName;
    }

    public DeviceDTO(UUID id, String title, String description, String address, float hourlyConsumption, String userName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.hourlyConsumption = hourlyConsumption;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public float getHourlyConsumption() {
        return hourlyConsumption;
    }
    public void setHourlyConsumption(float hourlyConsumption) {
        this.hourlyConsumption = hourlyConsumption;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDTO that = (DeviceDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(address, that.address) &&
                Objects.equals(userName, that.userName) &&
                Float.compare(hourlyConsumption, that.hourlyConsumption) == 0;
                //hourlyConsumption == that.hourlyConsumption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, address, hourlyConsumption, userName);
    }
}























