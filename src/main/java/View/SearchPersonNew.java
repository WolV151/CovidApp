package View;

import Control.userController;
import Control.contactController;
import Model.Contact;
import Model.Person;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.List;

public class SearchPersonNew extends Tab {
    private Person targetPerson = null;

    public static VBox contactList (TableView<Person> contactsTable){

        VBox contactList = new VBox();
        List<Person> people = userController.getAllPeople();

        contactsTable.getItems().clear(); //clears everything in the beginning
        for(int i = 0; i < people.size(); i++){ // read the contents of the contact list and add them to the table
            contactsTable.getItems().add(people.get(i));
        }
        contactList.getChildren().add(contactsTable);

        return contactList;
    }

    public static VBox closeContactList (TableView<Contact> contactsTable){ // same as the above method but for Contact class
                                                                        // probably could have done it in one method
        VBox closeContacts = new VBox();
        List<Contact> contacts = contactController.getAllContacts();

        contactsTable.getItems().clear(); //clears everything in the beginning
        for(int i = 0; i < contacts.size(); i++){ // read the contents of the contact list and add them to the table
            contactsTable.getItems().add(contacts.get(i));
        }
        closeContacts.getChildren().add(contactsTable);

        return closeContacts;
    }

    public static VBox filter (TableView<Contact> contactsTable, int id, LocalDate date){ // same concept as the above two but filtered
        VBox closeContacts = new VBox();
        List<Contact> contacts = contactController.getContactsSince(id, date);

        contactsTable.getItems().clear(); //clears everything in the beginning
        for(int i = 0; i < contacts.size(); i++){ // read the contents of the contact list and add them to the table
            contactsTable.getItems().add(contacts.get(i));
        }
        closeContacts.getChildren().add(contactsTable);

        return closeContacts;
    }

    public SearchPersonNew(){
        VBox content = new VBox();
        content.getStylesheets().add("styles/SearchPerson.css");
        content.setId("content");
        setText("Database");

        HBox titlePane = new HBox();
        titlePane.setId("title-pane");
        titlePane.setPickOnBounds(false);

        Label title = new Label("\tAll Contacts");
        title.setFont(Font.font("Verdana", 30));
        title.setPadding(new Insets(30, 30, 30 , 240.5));
        titlePane.getChildren().add(title);

        Line line =  new Line(820f, 20f, 20f, 20f); // upper line
        Line lineTwo =  new Line(820f, 20f, 20f, 20f); // bottom line

        HBox searchPane = new HBox();
        searchPane.setPadding(new Insets(10, 12, 10 , 12));
        searchPane.setSpacing(5);

        VBox tablePane = new VBox();
        tablePane.setPadding(new Insets(15, 12, 15, 12));
        tablePane.setSpacing(5);

        VBox buttonPane = new VBox();
        buttonPane.setId("button-pane");
        buttonPane.setPadding(new Insets(15, 12, 15, 12));
        tablePane.setSpacing(5);

        //search text field ------------------------------------------------
        Label searchLabel = new Label("Search a person ");
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setId("search-button");
        searchField.setPromptText("Enter name or ID");
        searchLabel.setLabelFor(searchField);

        Button refresh =  new Button("Refresh/Go back");
        refresh.setId("refresh-button");

        searchPane.getChildren().addAll(searchLabel, searchField, searchButton, refresh);

        HBox filterContacts = new HBox();
        filterContacts.setPadding(new Insets(15, 12 ,15, 12));
        filterContacts.setSpacing(5);
        Label datesLabel = new Label("Search for close contacts since:");
        DatePicker datePicker = new DatePicker();
        Button filterButton = new Button("Filter");
        filterButton.setId("filter-button");

        filterContacts.getChildren().addAll(datesLabel, datePicker, filterButton);

        //contactsDisplay --------------------------------------------------
        ScrollPane contactDisplay = new ScrollPane();
        contactDisplay.setFitToWidth(true);

        // contacts list(table) ----------------------------------------------------
        TableView<Person> tableView = new TableView<>(); // this is the table view that holds all the Person details

        // takes a Person class and a string
        TableColumn<Person, String> columnFirstName = new TableColumn<>("First Name");
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> columnmiddleName = new TableColumn<>("Middle Name");
        columnmiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        TableColumn<Person, String> columnLastName = new TableColumn<>("Last Name");
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Person, String> columnPhone = new TableColumn<>("Phone");
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Person, String> columnEmail = new TableColumn<>("Email");
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(320, 340);

        tableView.getColumns().add(columnFirstName); // add all the columns in the table
        tableView.getColumns().add(columnmiddleName);
        tableView.getColumns().add(columnLastName);
        tableView.getColumns().add(columnId);
        tableView.getColumns().add(columnPhone);
        tableView.getColumns().add(columnEmail);

        contactDisplay.setContent(contactList(tableView));
        tablePane.getChildren().add(contactDisplay);

        // close contacts display --------------------------------------------------
        ScrollPane closeContactDisplay = new ScrollPane();
        closeContactDisplay.setFitToWidth(true);

        // close contacts list(table) ----------------------------------------------------
        TableView<Contact> contactTableView = new TableView<>(); // this is the table view that holds all the contact details

        // takes a Contact class and a string
        TableColumn<Contact, String> columnPersonOneID = new TableColumn<>("ID1");
        columnPersonOneID.setCellValueFactory(new PropertyValueFactory<>("personID1"));

        TableColumn<Contact, String> columnPersonTwoID = new TableColumn<>("ID2");
        columnPersonTwoID.setCellValueFactory(new PropertyValueFactory<>("personID2"));

        TableColumn<Contact, LocalDate> columnDate = new TableColumn<>("Date");
        columnDate.setCellValueFactory(new PropertyValueFactory<>("dateContact"));

        contactTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        contactTableView.setPrefSize(320, 340);

        contactTableView.getColumns().add(columnPersonOneID); // add all the columns in the table
        contactTableView.getColumns().add(columnPersonTwoID);
        contactTableView.getColumns().add(columnDate);


        closeContactDisplay.setContent(closeContactList(contactTableView));
        tablePane.getChildren().add(filterContacts);
        tablePane.getChildren().add(closeContactDisplay);

        //------------------------------------------------------lower parts
        Button removeContact = new Button("Remove");
        removeContact.setId("remove-button");

        tableView.setOnMouseClicked(e ->{ // clear selections for one table in order to use remove for both
            contactTableView.getSelectionModel().clearSelection(); // if i select something in one table it removes the
        });                                                     // selection from the other table

        contactTableView.setOnMouseClicked(e ->{
            tableView.getSelectionModel().clearSelection();
        });


        removeContact.setOnAction(e->{ // has two conditions on removal
            try {
                // this first condition basically states if there is nothing marked in the Person table, then something in the Contact table is marked
                if(tableView.getSelectionModel().getSelectedItems().toString().equals("[]")){ // if i want to delete only a contact
                    String[] contactRemovedInfo = contactTableView.getSelectionModel().getSelectedItems().toString()
                            .replace("[", "").replace("]", "").split(",");

                    contactController.deleteSpecificContact(Integer.parseInt(contactRemovedInfo[0]), Integer.parseInt(contactRemovedInfo[1]),
                            LocalDate.parse(contactRemovedInfo[2]));
                    contactTableView.getItems().removeAll(contactTableView.getSelectionModel().getSelectedItems());

                    System.out.println("Removed: " + tableView.getSelectionModel().getSelectedItems().toString());
                } else{ // else if my selection is in the Person table check these conditions instead
                    System.out.println("Removed: " + tableView.getSelectionModel().getSelectedItems().toString());
                    String[] removedGuyInfo = tableView.getSelectionModel().getSelectedItems().toString()
                            .replace("[", "").replace("]", "").split(",");

                    contactController.deleteContact(Integer.parseInt(removedGuyInfo[3]));
                    userController.deletePerson(Integer.parseInt(removedGuyInfo[3]));
                    tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
                }

            } catch (ArrayIndexOutOfBoundsException exc){
                System.out.println("Arrayboundserror");
            }

        });

        searchButton.setOnAction(e ->{
            tableView.getItems().clear();
            contactTableView.getItems().clear();

            try{ // if int parsed successfully
                Person myPerson = userController.getPersonById(Integer.parseInt(searchField.getText()));
                targetPerson = myPerson;
                List<Contact> myContacts = contactController.getContactByID(myPerson.getId());
                tableView.getItems().add(myPerson);

                for (Contact contact : myContacts){
                    contactTableView.getItems().add(contact);
                }

            } catch (NumberFormatException exc){ // if not then it must be a string
                Person myPerson = userController.getPersonByName(searchField.getText());
                targetPerson = myPerson;
                List<Contact> myContacts = contactController.getContactByID(myPerson.getId());
                tableView.getItems().add(myPerson);

                for (Contact contact : myContacts){
                    contactTableView.getItems().add(contact);
                }            }
        });

        refresh.setOnAction(e ->{
            contactDisplay.setContent(contactList(tableView));
            closeContactDisplay.setContent(closeContactList(contactTableView));
            searchField.clear();
        });

        filterButton.setOnAction(e ->{
            closeContactDisplay.setContent(filter(contactTableView, targetPerson.getId(), datePicker.getValue()));
        });

        buttonPane.getChildren().add(removeContact);
        content.getChildren().addAll(titlePane, line, searchPane, tablePane, lineTwo, buttonPane);
        setContent(content);
    }

}
