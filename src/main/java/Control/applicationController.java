package Control;

import Model.Contact;
import Model.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import static Control.FactoryManager.ENTITY_MANAGER_FACTORY;

public class applicationController {

    public static void save(){ // this method saves all the classes in the application via serialization
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("allClassInformation.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.flush();

            List<Person> people = userController.getAllPeople();
            List<Contact> contacts = contactController.getAllContacts();

            objectOutputStream.writeObject(people);
            objectOutputStream.writeObject(contacts);
            objectOutputStream.close();
            fileOutputStream.close();

            Alert confirmsave = new Alert(Alert.AlertType.INFORMATION, "Saved to file successfully",
                    ButtonType.OK);
            confirmsave.showAndWait();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void crash(){ // simple method that creates infinite Person objects and saves them into a list
        LinkedList<Person> peopleList = new LinkedList<>(); // calling this method causes the whole application to run
        while (true){                                       // out of memory
            Person dummy = new Person();
            peopleList.add(dummy);
        }
    }


    public static void exit(){ // this method exits the application
        // on click create a confirm box
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Exit program?", ButtonType.YES, ButtonType.CANCEL);
        confirm.showAndWait(); // wait for input

        if (confirm.getResult() == ButtonType.YES){ // if yes is clicked, do the same as save
            ENTITY_MANAGER_FACTORY.close();
            System.exit(0);
        }
        else if (confirm.getResult() == ButtonType.CANCEL){ //if cancel is clicked - close the alert box
            confirm.close();
        }
    }
}
