package ro.tuc.ds2022.tema1.OrsanTudor.entities;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Device implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Type(type = "uuid-binary")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "hourlyConsumption", nullable = false)
    private float hourlyConsumption;

    //@OneToMany(mappedBy = "device")
    //private List<User> user;
    //@OneToMany(mappedBy = "user")
    //private List<Device> device;

    //Relatie 1-n: Un User are o Lista de Devices;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Device()
    {
    }

    public Device(String title, String description, String address,  float hourlyConsumption, User user) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.hourlyConsumption = hourlyConsumption;
        this.user = user;
        //User null pana adaug user;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}


















