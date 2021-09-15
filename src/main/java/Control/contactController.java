package Control;

import Model.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

import static Control.FactoryManager.ENTITY_MANAGER_FACTORY;

public class contactController {

    public static void addCloseContact(int personID1, int personID2, LocalDate date){ // add a new close contact
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager(); // create an entity manager
        EntityTransaction et = null; // start a transaction
        try{
            et = em.getTransaction();
            et.begin();
            Contact contact = new Contact(); // create the new contact object
            contact.setPersonID1(personID1); // set it's attributes to the parameters
            contact.setPersonID2(personID2);
            contact.setDateContact(date);
            em.persist(contact); // persist the object
            et.commit(); // commit the transaction
        }
        catch (Exception exc){
            if (et != null){
                et.rollback(); // if something goes wrong, rollback the transaction
            }
            exc.printStackTrace();
        }
        finally {
            em.close(); // close the entity manager
        }
    }

    public static void deleteSpecificContact(int id1, int id2, LocalDate date){ // delete by either id one or two
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        String query = "DELETE FROM Contact a WHERE a.personID1 = :id1 AND a.personID2 = :id2 AND a.dateContact = :date";

        try{
            et = em.getTransaction();
            et.begin();
            em.createQuery(query).setParameter("id1", id1).setParameter("id2", id2)
                    .setParameter("date", date).executeUpdate();

            //em.persist(target); // if we uncomment this, then the instance will not be deleted from the db
            et.commit();
        }
        catch (Exception exc){
            if(et != null){
                et.rollback();
            }
            exc.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void deleteContact(int id){ // delete all contacts of matching id
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        String query = "DELETE FROM Contact a WHERE a.personID1 = :id OR a.personID2 = :id";

        try{
            et = em.getTransaction();
            et.begin();
            em.createQuery(query).setParameter("id", id).executeUpdate();

            //em.persist(target); // if we uncomment this, then the instance will not be deleted from the db
            et.commit();
        }
        catch (Exception exc){
            if(et != null){
                et.rollback();
            }
            exc.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    public static List<Contact> getContactByID(int id){ // get contact of either id one or two
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Contact c WHERE c.personID1 = :id OR c.personID2 = :id";

        TypedQuery<Contact> tq = em.createQuery(query, Contact.class);
        tq.setParameter("id", id);
        List<Contact> result = null;

        try {
            result = tq.getResultList();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }

    public static List<Contact> getAllContacts(){ // get everything from the contact table
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Contact c";
        TypedQuery<Contact> tq = em.createQuery(query, Contact.class);
        List<Contact> result = null;

        try{
            result = tq.getResultList();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            em.close();
        }

        return result;
    }

    public static List<Contact> getContactsSince(int id, LocalDate date){ // filter contacts since date
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Contact c WHERE c.dateContact >= :date AND (c.personID1 = :id OR c.personID2 = :id)";
        TypedQuery<Contact> tq = em.createQuery(query, Contact.class);
        tq.setParameter("date", date).setParameter("id", id);
        List<Contact> result = null;

        try{
            result = tq.getResultList();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            em.close();
        }

        return result;
    }

}
