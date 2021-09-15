package View;

import Control.contactController;
import Control.userController;
import Model.Person;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.List;


public class RecordContact extends Tab {

    private void addToSelectList(ComboBox<String> combo){ // add the contacts to the combo boxes
        List<Person> people = userController.getAllPeople();

        for (Person i: people){
            combo.getItems().removeAll(i.toString()); // prevents re-adding the same contact on multiple clicks
            combo.getItems().add(i.toString());
        }
    }

    public RecordContact(){
        setText("Record Close Contact");

        VBox closeContactDisplay = new VBox(); // the whole display
        closeContactDisplay.setId("contact-display");
        closeContactDisplay.getStylesheets().add("styles/RecordContact.css");

        HBox titlePane = new HBox();
        titlePane.setId("title-pane");
        titlePane.setPickOnBounds(false);

        Label title = new Label("\tRecord a Close Contact");
        title.setFont(Font.font("Verdana", 30));
        title.setPadding(new Insets(30, 30, 30 , 180.5));
        titlePane.getChildren().add(title);

        Line line =  new Line(820f, 20f, 20f, 20f);


        HBox contactSelectBox = new HBox(); // first contact details
        HBox secondContactSelectBox =  new HBox(); // second contact details
        HBox dateBox = new HBox(); // date
        HBox timeBox = new HBox(); // time
        HBox buttonBox = new HBox(); // easier to manage buttons within a layout

        buttonBox.setPadding(new Insets(12, 10, 12, 10));

        contactSelectBox.setPadding(new Insets(12, 10, 12, 10));
        contactSelectBox.setSpacing(7);

        secondContactSelectBox.setPadding(new Insets(12, 10, 12, 10));
        secondContactSelectBox.setSpacing(7);

        dateBox.setPadding(new Insets(12, 10, 12, 10));
        dateBox.setSpacing(7);

        timeBox.setPadding(new Insets(12, 10, 12, 10));
        timeBox.setSpacing(7);

        //----------------------------- Contact Picker --------------------------------------------
        Label contactListLabel = new Label("Select a contact:\t\t "); // this will work only if there are contacts loaded
        ComboBox<String> selectContact =  new ComboBox<String>();
        contactListLabel.setLabelFor(selectContact);

        selectContact.setOnMouseClicked(e -> {  // update on mouse click
            addToSelectList(selectContact);
        });


        Label contactListLabel2 = new Label("Select another contact:"); // second contact
        ComboBox<String> selectContact2 =  new ComboBox<String>();
        contactListLabel.setLabelFor(selectContact);

        selectContact2.setOnMouseClicked(e -> { // same as the first one
            addToSelectList(selectContact2);
        });


        //----------------------------- Date Picker --------------------------------------------
        Label dateLabel =  new Label("Enter date of the close contact");
        DatePicker datePicker = new DatePicker(); // pick a date
        dateLabel.setLabelFor(datePicker);

        Button addEncounter = new Button("Add close contact");
        buttonBox.getChildren().add(addEncounter);


        addEncounter.setOnAction(e ->{ // the button action to record a contact
            if (selectContact.getValue() == null || selectContact2.getValue() == null || datePicker.getValue() == null){
                Alert error = new Alert(Alert.AlertType.ERROR, "Please select two people and a date.", ButtonType.OK);
                error.showAndWait(); // wait for input
            } else{
                int p1ID = Integer.parseInt(selectContact.getValue().split(",")[3]); // 3rd index is the id
                int p2ID = Integer.parseInt(selectContact2.getValue().split(",")[3]);

                if (p1ID == p2ID){
                    Alert error = new Alert(Alert.AlertType.ERROR, "Cannot add a close contact between the same Person", ButtonType.OK);
                    error.showAndWait(); // wait for input
                }else contactController.addCloseContact(p1ID, p2ID, datePicker.getValue());
            }



        });

        // add all the children to the panes
        contactSelectBox.getChildren().addAll(contactListLabel, selectContact); // select a contact
        secondContactSelectBox.getChildren().addAll(contactListLabel2, selectContact2);
        dateBox.getChildren().addAll(dateLabel, datePicker); // date picker


        closeContactDisplay.getChildren().addAll(titlePane, line, contactSelectBox, secondContactSelectBox, dateBox, timeBox, buttonBox);
        setContent(closeContactDisplay);

    }



}
