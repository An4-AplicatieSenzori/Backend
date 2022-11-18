package ro.tuc.ds2022.tema1.OrsanTudor.entities;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime; //Doar java time!
import java.util.Date;
import java.util.List;
import java.util.UUID;



//Dependente noi in backend, si in frontend:
//Analog cu celelalte 2 entitati:
@Entity
public class DeviceEnergy implements Serializable{
    private static final long serialVersionUID = 1L;

    //uuid2 de ce nu 1:
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    //Nu trebuie nimic unique!
    //Value:
    //Nu poate fi null!!! Doar la user pentru device am pus
    //Nullable true sau false, ca introducem la INSERT sau la UPDATE!!!
    //Asa poti insera cand vrei fata de restul coloanelor care sunt obligatorii!!!
    @Column(name = "value", nullable = false)
    //Int sau Float, depinde de chart:
    private int value;

    //Date: (An, Luna, Zi):
    //Util:
    //@Column(name = "daySelected", nullable = false)
    //private Date daySelected;

    //Hour: (Time, hour, minut, second):
    //@Column(name = "hourConsumption", nullable = false)
    //private Time hourConsumption;
    //Tab ia primul care este acolo;

    @Column(name = "dayPlusHourSelected", nullable = false)
    private LocalDateTime dayPlusHourSelected;

    //Id de la Device:
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;


    public DeviceEnergy()
    {
    }

    //Fara id, cu Device;
    public DeviceEnergy(int value, LocalDateTime dayPlusHourSelected, Device device) {
        this.value = value;
        //this.daySelected = daySelected;
        //this.hourConsumption = hourConsumption;
        this.dayPlusHourSelected = dayPlusHourSelected;
        this.device = device;
    }

    //Cu id, cu Device:
    public DeviceEnergy(UUID id, int value, LocalDateTime dayPlusHourSelected,
                        Device device) {
        this.id = id;
        this.value = value;
        //this.daySelected = daySelected;
        //this.hourConsumption = hourConsumption;
        this.dayPlusHourSelected = dayPlusHourSelected;
        this.device = device;
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
    /*
    public Date getDaySelected() {
        return daySelected;
    }
    public void setDaySelected(Date daySelected) {
        this.daySelected = daySelected;
    }
    public Time getHourConsumption() {
        return hourConsumption;
    }
    public void setHourConsumption(Time hourConsumption) {
        this.hourConsumption = hourConsumption;
    }
    */
    public LocalDateTime getDayPlusHourSelected() {
        return dayPlusHourSelected;
    }
    public void setDayPlusHourSelected(LocalDateTime dayPlusHourSelected) {
        this.dayPlusHourSelected = dayPlusHourSelected;
    }
    public Device getDevice() {
        return device;
    }
    public void setDevice(Device device) {
        this.device = device;
    }
}



















