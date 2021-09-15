import Control.userController;
import Model.Name;
import Model.Person;
import org.junit.Test;
import static org.junit.Assert.*;

public class testUserQuery {


    @Test
    public void testPersonQuery(){ // tests whether the findById query works in the person controller
        Person testGuy = new Person();
        Name nameForGuy =  new Name();
        nameForGuy.setFirstName("John");
        nameForGuy.setMiddleName("F.");
        nameForGuy.setLastName("Smith");
        testGuy.setName(nameForGuy);
        testGuy.setEmail("john@example.com");
        testGuy.setPhone("1999999999");
        testGuy.setId(1234);

        assertEquals(testGuy, userController.getPersonById(1234));
    }




}
