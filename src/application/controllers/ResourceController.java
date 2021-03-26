package application.controllers;

import application.Facility;
import application.Main;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResourceController implements Initializable {

    @FXML
    private TextField facilityName;
    @FXML
    private TextField facilityID;
    @FXML
    private TextArea txtAreaFeedback;

    @FXML ListView<Facility> list;

    private Facility thisFacility;

    private void refreshList()
    {
        list.getItems().clear();
        List<Facility> items = getResources();
        for (Facility item: items) {
            list.getItems().add(item);
        }
    }

    public void handleCreateResourceBtn(ActionEvent e) throws Exception {
        if(facilityID.getText().length()<1){
            txtAreaFeedback.setText("facility id must be at least 1 character");
        }
        else if(saveResource(facilityID.getText(), facilityName.getText())) {
            txtAreaFeedback.setText("Resource Created");
            //Main.set_pane(0);
        }
        refreshList();
    }

    public void handleReadBtn(ActionEvent e) throws Exception {
        thisFacility = list.getSelectionModel().getSelectedItem();
        facilityName.setText(thisFacility.getFacilityName());
        facilityID.setText(thisFacility.getFacilityID());
    }

    public void handleDeleteBtn(ActionEvent e) throws Exception {
        removeFacility();
        facilityID.setText("");
        facilityName.setText("");
    }

    private boolean removeFacility(){
        ArrayList<Facility> facilities = new ArrayList<Facility>();
        XStream xstream = new XStream(new DomDriver());
        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("resources.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean saveResource(String facilityName, String facilityID){
        ArrayList<Facility> facilities = new ArrayList<Facility>();
        XStream xstream = new XStream(new DomDriver());

        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ResourceInformation.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch (Exception e){
            return false;
        }

        try {
            Facility newFacility = new Facility(facilityName, facilityID);
            facilities.add(newFacility);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("ResourceInformation.xml"));
            out.writeObject(facilities);
            out.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private List<Facility> getResources(){
        ArrayList<Facility> facilities = null;
        XStream xstream = new XStream(new DomDriver());
        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ResourceInformation.xml"));
            facilities = (ArrayList<Facility>) is.readObject();
            is.close();
        }
        catch(Exception e){
            facilities =  new ArrayList<Facility>();
        }
        return facilities;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();
    }

    public void handleReturnBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}
