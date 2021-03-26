package application.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import application.Main;
import application.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextArea txtAreaFeedback;

    public void handleLoginBtn(ActionEvent e) throws Exception {
        int result = login(username.getText(), password.getText());
        if(username.getText().length()<4 || password.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        }
        else if(result > 0){
            txtAreaFeedback.setText("Successful Login");

            Main.set_pane(result);
        }
        else {
            txtAreaFeedback.setText("Un-Successful Login");
            password.clear();
        }
    }

    private int login(String username, String password) {
        ArrayList<User> users = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            users =  new ArrayList<User>();
            txtAreaFeedback.setText("Password File not located");
            return 0;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return 0;
        }

        for(User user: users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                Main.newUser= user;
                if(user.getUserType().equals("User"))
                    return 4;
                else
                    return 3;
            }
        }
        return 0;
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

}