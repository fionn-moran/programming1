package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<>();
    private static int sceneIndex = 0;
    public static User newUser = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Anchor.fxml"));
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/AccountController.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Login.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Register.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/AdminMenuController.fxml"))); //index 3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/UserMenuController.fxml"))); //index 4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Reservations.fxml"))); //index 5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/AdminReservations.fxml"))); //index 6
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/AdminUserController.fxml"))); //index 7
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/UserUpdate.fxml"))); //index 8
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Resource.fxml"))); //index 9

        root.getChildren().add(anchor.get(0)); //Login/Register HomeScreen

        primaryStage.setTitle("Login/Register");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void set_pane(int index){
        root.getChildren().remove(anchor.get(sceneIndex));  //remove the existing pane from the root
        root.getChildren().add(anchor.get(index));          //add the selected pane to the root
        sceneIndex=index;                                   //update the stored sceneIndex
    }

}
