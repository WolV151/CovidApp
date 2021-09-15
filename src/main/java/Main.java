// Marin Donchev R00192936 SDH2-A

import View.ManageContacts;
import View.RecordContact;
import View.SearchPersonNew;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainPane = new BorderPane();
        Group root = new Group();

        TabPane tabPane = new TabPane();  // tab pane to store the tabs
        tabPane.setPrefSize(800, 620);

        tabPane.getTabs().add(new ManageContacts());  // first tab
        tabPane.getTabs().add(new RecordContact());
        tabPane.getTabs().add(new SearchPersonNew());


        mainPane.setCenter(tabPane);
        root.getChildren().add(mainPane);
        primaryStage.setTitle("Covid Close Contact Record rev.2");
        primaryStage.setScene(new Scene(root, 801, 615));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
