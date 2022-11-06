package ro.tuc.ds2022.tema1.OrsanTudor.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

//Entity Spring:
//Este entity, genereala el UUID cumva;
@Entity
public class User implements Serializable
{
    //1L / Pentru serializare;
    private static final long serialVersionUID = 1L;

    //Pentru UUID;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary") //Este binar de aici!!!
    private UUID id;

    //Facute direct din BD?
    //Toate sunt private;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    //2 Constructori, fara ID, este mai sus;
    public User()
    {
    }
    public User(String name, String address, int age, String email, String password) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    //All get+set; (Set si la UUID;)
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}


















