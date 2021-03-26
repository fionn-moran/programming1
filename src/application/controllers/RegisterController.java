package application.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import application.Main;
import application.User;
import java.io.*;
import java.util.ArrayList;

public class RegisterController {

    @FXML private TextField foreName;
    @FXML private TextField surName;
    @FXML private TextField username;
    @FXML private TextField role;
    @FXML private TextField mobileNo;
    @FXML private TextField email;
    @FXML private ChoiceBox userType;
    @FXML private PasswordField password;
    @FXML private PasswordField repeat_password;
    @FXML private TextArea txtAreaFeedback;

    public void handleRegisterBtn(ActionEvent e) throws Exception {
        if (foreName.getText().length() == 0 || surName.getText().length() == 0) {
            txtAreaFeedback.setText("name must be at least 1 character \n");
        }
        if(password.getText().length()<3 ){
            txtAreaFeedback.setText("Password must be at least 3 characters");
        }
        else if(!password.getText().equals(repeat_password.getText())){
            txtAreaFeedback.setText("Password must match RepeatPassword");
        }
        else if(register(foreName.getText(), surName.getText(), username.getText(), role.getText(), mobileNo.getText(), email.getText(), (String)userType.getValue(), password.getText())) {
            txtAreaFeedback.setText("Successful Registration");
            //Main.set_pane(0);
        }
    }

    private boolean register(String foreName, String surName, String username, String role, String mobileNo, String email, String userType, String password) {
        ArrayList<User> users = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());

        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            users =  new ArrayList<User>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            User user = new User(foreName, surName, username, role, mobileNo, email, userType, password);
            users.add(user);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
            out.writeObject(users);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }
}

