package Control;

import Model.Name;
import Model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

import static Control.FactoryManager.ENTITY_MANAGER_FACTORY;

public class userController {

    public static void addPerson(Name name, String email, String phone, int id){ // add a new person
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Person person = new Person();
            person.setName(name);
            person.setPhone(phone);
            person.setEmail(email);
            person.setId(id);
            em.persist(person);
            et.commit();
        }
        catch (Exception exc){
            if (et != null){
                et.rollback();
            }
            exc.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static Person getPersonById(int id){ // get a person by their id
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT per FROM Person per WHERE per.id = :id";

        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        tq.setParameter("id", id);
        Person result = null;

        try {
            result = tq.getSingleResult();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }

    public static Person getPersonByName(String name){ // get a person by their names (requires all names)
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT per FROM Person per " +
                "WHERE per.name.firstName = :fname AND per.name.middleName = :mname AND per.name.lastName = :lname";
        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        String[] info = name.split(" ");
        tq.setParameter("fname", info[0]);
        tq.setParameter("mname", info[1]);
        tq.setParameter("lname", info[2]);
        Person result = null;

        try {
            result = tq.getSingleResult();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            em.close();
        }
        return result;
    }

    public static List<Person> getAllPeople(){ // get all the people in the database
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT a FROM Person a WHERE id IS NOT NULL";
        TypedQuery<Person> tq = em. createQuery(query, Person.class);
        List<Person> result = null;

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

    public static void deletePerson(int id){ // delete a person by their id
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Person target = null;

        try{
            et = em.getTransaction();
            et.begin();
            target = em.find(Person.class, id);
            em.remove(target);

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



}