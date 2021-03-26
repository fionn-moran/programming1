package application.controllers;

import application.Main;
import application.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserController {
    @FXML private TextField mobileNo;
    @FXML private TextField email;

    public void handleUpdateBtn(ActionEvent e) throws Exception {
        updateUser();
        mobileNo.setText("");
        email.setText("");
    }

    public void handleDeleteBtn(ActionEvent e) throws Exception {
        deleteUser();
        mobileNo.setText("");
        email.setText("");
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(4);
    }

    private boolean updateUser(){
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

        return true;
    }


    private boolean deleteUser(){
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
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
            out.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

}
