package Model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 *     Contact class is used to record the fact that
 * two people recorded in the application had
 * a close contact between one another.
 * </p>
 * <p>
 * In order for this class to be used
 * there needs to be at least two people
 * recorded in the application database
 * as it will require two existing people's ids.
 * </p>
 * <p>
 *     The class uses JPA in order to be stored into a relational database.
 * </p>
 *
 *
 *
 * <p>
 * The class does not take any parameters as
 * JPA will require the class to have an empty constructor
 * </p>
 * <p>
 *     The class has three attributes, all of them act as a composite primary key:
 *     <ul>
 *         <li>personID1 - This is the the id attribute of the first person</li>
 *         <li>personID2 - This is the the id attribute of the second person</li>
 *         <li>date - a date at which the close contact happened</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     The class implements the Serializable interface in order to be used with JPA and be saved to a .ser file
 * </p>
 *
 *
 *
 * <h2>Creating a new instance of the Contact class</h2>
 * <p>
 * <pre>{@code
 * public class createAContact {
 *
 *     public createAContact{
 *         Contact newContact = new Contact();
 *         newContact.setPersonID1(2193);
 *         newContact.setPersonID2(2834);
 *         newContact.setDateContact(LocalDate.parse("2021-03-05"));
 *     }
 * }
 * }</pre>
 * </p>
 *
 * @author Marin Donchev
 * @version %I%, %G%
 * @see Person
 * @see LocalDate
 * @see Serializable
 */

@Entity
@Table(name = "closeContact")
public class Contact implements Serializable{

    /**
     * Version attribute used in serialization
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * An existing person id attribute
     */
    @Id // really careful with the id tag as it can mess up the queries really bad, since jpa does not care about db pk
    @Column(name = "personID1", unique = true)
    private int personID1;

    /**
     * An existing person id attribute
     */
    @Id
    @Column(name = "personID2", unique = true)
    private int personID2;
    /**
     * A date class (uses LocalDate)
     */
    @Id
    @Column(name = "dateContact", unique = false)
    private LocalDate dateContact;

    /**
     * Get the ID of the first Person involved
     * @return the Id of the second person involved
     */
    public int getPersonID1() {
        return personID1;
    }

    /**
     * Set the ID of the first Person involved
     * @param personID1 A valid id of a person
     */
    public void setPersonID1(int personID1) {
        this.personID1 = personID1;
    }
    /**
     * Get the ID of the second Person involved
     * @return the Id of the second person involved
     */
    public int getPersonID2() {
        return personID2;
    }
    /**
     * Get the ID of the second Person involved
     * @param personID2 A valid id of a person
     */
    public void setPersonID2(int personID2) {
        this.personID2 = personID2;
    }
    /**
     * Get the date of the close contact
     * @return the date of the close contact
     */
    public LocalDate getDateContact() {
        return dateContact;
    }
    /**
     * Set the date of the close contact
     * @param dateContact a single LocalDate class
     */
    public void setDateContact(LocalDate dateContact) {
        this.dateContact = dateContact;
    }
    /**
     * Returns the string representation of the class
     */
    @Override
    public String toString() {
        return personID1 + "," + personID2 + "," + dateContact.toString();
    }

    /**
     * Checks whether the contact equals another contact
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return personID1 == contact.personID1 &&
                personID2 == contact.personID2 &&
                Objects.equals(dateContact, contact.dateContact);
    }

    /**
     * hashCode method
     * @return A hash code value for a contact.
     */
    @Override
    public int hashCode() {
        return Objects.hash(personID1, personID2, dateContact);
    }
}
