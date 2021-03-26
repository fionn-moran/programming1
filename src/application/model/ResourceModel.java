package application.model;

import application.Facility;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ResourceModel {

    ArrayList<Facility> facilities;

    public void addResource(String facilityID, String facilityName){
        Facility resource = new Facility(facilityID, facilityName);
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("ResourceInformation.xml"));
        out.writeObject(facilities);
        out.close();
    }


    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ResourceInformation.xml"));
        facilities = (ArrayList<Facility>) is.readObject();
        is.close();
    }
}
