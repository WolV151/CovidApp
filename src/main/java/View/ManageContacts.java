// Marin Donchev R00192936 SDH2-A

package View;

import Control.applicationController;
import Control.userController;
import Model.Name;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class ManageContacts extends Tab {



    public ManageContacts(){
        VBox contactInfo = new VBox(); // this holds of the whole main screen
        contactInfo.setId("contact-info");
        contactInfo.getStylesheets().add("styles/ManageContacts.css"); // get the css for the tab

        HBox titlePane = new HBox(); // holds the title
        titlePane.setId("title-pane");
        titlePane.setPickOnBounds(false);

        Label title = new Label("\tContacts");
        title.setFont(Font.font("Verdana", 30));
        title.setPadding(new Insets(30, 30, 30 , 280.5));
        titlePane.getChildren().add(title);

        Line line =  new Line(820f, 20f, 20f, 20f);
        Line lineTwo =  new Line(820f, 20f, 20f, 20f);



        setText("Add Contact");
        HBox firstNameInfo = new HBox(); // this holds first name nodes
        HBox middleNameInfo = new HBox(); // holds the middle name
        HBox lastNameInfo = new HBox(); // this holds last name nodes
        HBox idInfo = new HBox(); // this holds id nodes
        HBox phoneInfo = new HBox(); // this holds phone nodes
        HBox emailInfo = new HBox(); // holds email

        HBox programFuncBox = new HBox(); // this holds of load/save buttons
        programFuncBox.setPadding(new Insets(10, 10, 10, 10));
        programFuncBox.setId("program-func-box");
        programFuncBox.setSpacing(7);

        HBox contactButtonBox = new HBox(); // this holds the add/remove contact buttons
        contactButtonBox.setId("contact-button-box");
        contactButtonBox.setPadding(new Insets(10, 10 , 17, 10));
        contactButtonBox.setSpacing(7);

        //first name -------------------------------------------------
        Label firstNameLabel = new Label("Enter first name ");
        TextField firstNameField = new TextField();
        firstNameLabel.setLabelFor(firstNameField);
        firstNameInfo.setPadding(new Insets(15, 12, 15, 12));
        firstNameInfo.setSpacing(35);
        firstNameInfo.getChildren().addAll(firstNameLabel, firstNameField);

        //middle name -------------------------------------------------
        Label middleNameLabel = new Label("Enter middle name");
        TextField middleNameField = new TextField();
        middleNameLabel.setLabelFor(middleNameField);
        middleNameInfo.setPadding(new Insets(15, 12, 15, 12));
        middleNameInfo.setSpacing(21);
        middleNameInfo.getChildren().addAll(middleNameLabel, middleNameField);

        //last name -------------------------------------------------
        Label lastNameLabel = new Label("Enter last name  ");
        TextField lastNameField = new TextField();
        lastNameLabel.setLabelFor(lastNameField);
        lastNameInfo.setPadding(new Insets(15, 12, 15, 12));
        lastNameInfo.setSpacing(37);
        lastNameInfo.getChildren().addAll(lastNameLabel, lastNameField);

        //id -------------------------------------------------
        Label idLabel = new Label("Enter unique id  ");
        TextField idField = new TextField();
        idLabel.setLabelFor(idField);
        idInfo.setPadding(new Insets(10, 12, 10, 12));
        idInfo.setSpacing(39);
        idInfo.getChildren().addAll(idLabel, idField);

        //phone -------------------------------------------------
        Label phoneLabel = new Label("Enter phone   ");
        TextField phoneField = new TextField();
        phoneLabel.setLabelFor(phoneField);
        phoneInfo.setPadding(new Insets(15, 12, 15, 12));
        phoneInfo.setSpacing(56);
        phoneInfo.getChildren().addAll(phoneLabel, phoneField);

        //email -------------------------------------------------
        Label emailLabel = new Label("Enter email   ");
        TextField emailField = new TextField();
        emailLabel.setLabelFor(emailField);
        emailInfo.setPadding(new Insets(15, 12, 15, 12));
        emailInfo.setSpacing(61);
        emailInfo.getChildren().addAll(emailLabel, emailField);





        // upper buttons ---------------------------------------------------------

        // these buttons are located above the contact table - the add / remove
        Button addContact = new Button("Add Contact");
        addContact.setId("add-button");
        addContact.setOnAction(e ->{

            if(firstNameField.getText().equals("") ||
            middleNameField.getText().equals("") ||
            lastNameField.getText().equals("") ||
            emailField.getText().equals("") ||
            phoneField.getText().equals("") ||
            idField.getText().equals(""))
            {
                Alert error = new Alert(Alert.AlertType.ERROR, "A field is missing", ButtonType.OK);
                error.showAndWait(); // wait for input
            } else{
                Name name = new Name(); // construct a new name
                name.setFirstName(firstNameField.getText());
                name.setMiddleName(middleNameField.getText());
                name.setLastName(lastNameField.getText());

                // add a person based on the fields
                try{
                    userController.addPerson(name, emailField.getText(), phoneField.getText(), Integer.parseInt(idField.getText()));
                } catch (NumberFormatException exc){
                    Alert error = new Alert(Alert.AlertType.ERROR, "The ID field requires a number", ButtonType.OK);
                    error.showAndWait(); // wait for input
                }

                firstNameField.clear(); // clear the fields for next insert
                middleNameField.clear();
                lastNameField.clear();
                idField.clear();
                phoneField.clear();
                emailField.clear();
            }
        });


        contactButtonBox.getChildren().addAll(addContact);

        contactInfo.getChildren().addAll(titlePane, line,firstNameInfo, middleNameInfo, lastNameInfo, idInfo, phoneInfo, emailInfo, lineTwo,contactButtonBox
                ,programFuncBox);

        // lower buttons -------------------------------------------

        // these buttons are located below the contact table - save/exit/crash

        Button saveFile = new Button("Save to text file"); // save to file using serialization
        saveFile.setId("save-button");
        saveFile.setOnAction(e -> applicationController.save());
        programFuncBox.getChildren().addAll(saveFile);

        Button exit = new Button("Exit"); // exit the application
        exit.setId("exit-button");
        exit.setOnAction(e -> applicationController.exit());

        Button memoryButton = new Button("Cause a memory exception."); // cause an out of memory error
        memoryButton.setId("memory-button");
        memoryButton.setOnAction(e -> applicationController.crash());

        Pane memoryErrorPane = new Pane();
        memoryErrorPane.setId("memory-pane");
        memoryErrorPane.setPickOnBounds(false);
        memoryButton.setLayoutY(programFuncBox.getLayoutY() - 20);
        memoryButton.setLayoutX(10);
        memoryErrorPane.getChildren().add(memoryButton);

        // created a separate pane for exit in order to align it to the right of the load/save
        Pane exitPane = new Pane();
        exitPane.setId("exit-pane");
        exitPane.setPickOnBounds(false);
        exit.setLayoutY(programFuncBox.getLayoutY() - 3);
        exit.setLayoutX(730);
        exitPane.getChildren().add(exit);

        contactInfo.getChildren().add(exitPane);
        contactInfo.getChildren().add(memoryErrorPane);
        setContent(contactInfo);

    }
}

