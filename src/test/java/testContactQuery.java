import Control.contactController;
import Model.Contact;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class testContactQuery {


    @Test
    public void testContactById(){ // tests whether the findbyId query in contact returns the correct values
        Contact testContact = new Contact();
        Contact testContact2 = new Contact();

        testContact.setPersonID1(2242);
        testContact.setPersonID2(2130);
        testContact.setDateContact(LocalDate.parse("2021-04-30"));

        testContact2.setPersonID1(2394);
        testContact2.setPersonID2(2242);
        testContact2.setDateContact(LocalDate.parse("2021-04-06"));

        ArrayList<Contact> testList = new ArrayList<>();
        testList.add(testContact);
        testList.add(testContact2);

        assertEquals(testList, contactController.getContactByID(2242));
    }




}
