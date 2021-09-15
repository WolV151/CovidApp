package Model;

import java.io.Serial;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person implements Serializable{

    @Serial
    private static final long serialVersionUID = 2L;

    // I assume that phone and email are also unique apart from the id, not that it matters I think

    @Id
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Embedded
    @AttributeOverrides({ // embed the names to store everything in one table
            @AttributeOverride(name = "firstName", column = @Column(name = "firstName")),
            @AttributeOverride(name = "middleName", column = @Column(name = "middleName")),
            @AttributeOverride(name = "lastName", column = @Column(name = "lastName"))
    })
    private Name name; // name embeddable property

    // name getters, used by the tables to read
    public String getFirstName(){ // i need the getters for the individual names in order for table to be able to read from them
        return name.getFirstName();
    }

    public String getMiddleName(){
        return name.getMiddleName();
    }

    public String getLastName(){
        return name.getLastName();
    }

    public void setName(Name name1){ // the setter can stay like this as a whole
        name = name1;
    }

    // phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name.getFirstName() + "," + name.getMiddleName() + "," + name.getMiddleName() + "," +
                getId() + "," + getEmail() + "," + getPhone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(email, person.email) &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email, id);
    }
}
