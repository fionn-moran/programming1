package application.model;

import application.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UsersModel {
    ArrayList<User> user;

    public void addUser(String foreName, String surName, String username, String role, String mobileNo, String email, String userType, String password){
        User user = new User(foreName, surName, username, role, mobileNo, email, userType, password);
    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
        user = (ArrayList<User>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
        out.writeObject(user);
        out.close();
    }




}
