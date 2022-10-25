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
public class Person  implements Serializable
{
    //1L / Pentru serializare;
    private static final long serialVersionUID = 1L;

    //Pentru UUID;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    //Facute direct din BD?
    //Toate sunt private;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "age", nullable = false)
    private int age;

    //2 Constructori, fara ID, este mai sus;
    public Person()
    {
    }
    public Person(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
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
}


















