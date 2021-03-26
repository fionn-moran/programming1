package application.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import application.Main;
import application.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminUserController implements Initializable {

    @FXML private TextField foreName;
    @FXML private TextField surName;
    @FXML private TextField username;
    @FXML private TextField role;
    @FXML private TextField mobileNo;
    @FXML private TextField email;
    @FXML private ChoiceBox userType;
    @FXML private TextField password;
    @FXML private TextArea txtAreaFeedback;

    @FXML ListView<User> list;


    private User thisUser;

    private void refreshList()
    {
        list.getItems().clear();
        List<User> items = getUsers();
        for (User item: items) {
            list.getItems().add(item);
        }
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }

    public void handleRegisterBtn(ActionEvent e) throws Exception {
        User newUser = new User(foreName.getText(), surName.getText(), role.getText(), mobileNo.getText(), email.getText(), userType.getValue().toString(), password.getText());
        createUser(newUser);
        refreshList();
    }

    public void handleReadBtn(ActionEvent e) throws Exception {
            thisUser = list.getSelectionModel().getSelectedItem();
            foreName.setText(thisUser.getForeName());
            surName.setText(thisUser.getSurName());
            role.setText(thisUser.getRole());
            mobileNo.setText(thisUser.getMobileNo());
            email.setText(thisUser.getEmail());
            userType.setValue(thisUser.getUserType().toString());
            password.setText(thisUser.getPassword());

        }

    public void handleDeleteBtn(ActionEvent e) throws Exception {
        foreName.setText("");
        surName.setText("");
        username.setText("");
        role.setText("");
        mobileNo.setText("");
        email.setText("");
        userType.setValue(null);
        password.setText("");

    }

    private List<User> getUsers(){
        ArrayList<User> users = null;
        XStream xstream = new XStream(new DomDriver());

        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch(Exception e){
            users =  new ArrayList<User>();
        }

        return users;
    }


    private boolean createUser(User user){
        ArrayList<User> users = new ArrayList<User>();
        XStream xstream = new XStream(new DomDriver());
        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
            users = (ArrayList<User>) is.readObject();
            is.close();
        }
        catch (Exception e){
            return false;
        }
        try {
            users.add(user);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
            out.writeObject(users);
            out.close();
        }
        catch (Exception e){
            txtAreaFeedback.setText("Error encountered writing file to password file");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();

    }
}
