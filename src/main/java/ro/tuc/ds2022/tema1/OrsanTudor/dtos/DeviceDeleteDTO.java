package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DeviceDeleteDTO
{
    @NotNull
    private String title;

    //Constructor gol;
    public DeviceDeleteDTO() {
    }

    public DeviceDeleteDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDeleteDTO that = (DeviceDeleteDTO) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
























