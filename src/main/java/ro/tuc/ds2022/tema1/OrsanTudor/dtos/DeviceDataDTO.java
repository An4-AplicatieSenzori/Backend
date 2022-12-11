package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceDataDTO
{
    private UUID deviceID;

    private LocalDateTime currentTime;

    private float value;


    public DeviceDataDTO(@JsonProperty("deviceID") UUID deviceID,
                         @JsonProperty("currentTime") LocalDateTime currentTime,
                         @JsonProperty("value") float value)
    {
        this.deviceID = deviceID;
        this.currentTime = currentTime;
        this.value = value;
    }


    @Override
    public String toString() {
        return "DeviceDataDTO{" +
                " id = '" + deviceID + '\'' +
                ", currentTime = '" + currentTime + '\'' +
                ", value = '" + value + '\'' +
                '}';
    }
}























