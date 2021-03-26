package application.controllers;

import application.model.BookingModel;
import application.Main;
import application.Reservation;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

//import java.awt.TextArea;
// import java.awt.TextField;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminReservationController implements Initializable {

    BookingModel bookingModel;

    @FXML
    private TextField reservationID;
    @FXML
    private TextField userID;
    @FXML
    private TextField facilityID;
    @FXML
    private DatePicker date;
    @FXML
    private TextField duration;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    ListView<Reservation> list;

    private Reservation thisReservation;

    private void refreshList()
    {
        list.getItems().clear();
        List<Reservation> items = getReservations();
        for (Reservation item: items) {
            list.getItems().add(item); }
    }

    public void handleCreateBtn(ActionEvent e) throws Exception {
        Reservation newReservation = new Reservation(reservationID.getText().toString(), userID.getText().toString(), facilityID.getText(), date.getValue(), duration.getText());
        saveReservation(newReservation);
        refreshList();
    }

   public void handleReadBtn(ActionEvent e) throws Exception {
       thisReservation = list.getSelectionModel().getSelectedItem();
       reservationID.setText(thisReservation.getDuration());
       userID.setText(thisReservation.getUserID());
       facilityID.setText(thisReservation.getFacilityID());
       date.setValue(thisReservation.getDate());
       duration.setText(thisReservation.getDuration());
    }

    public void handleDeleteBtn(ActionEvent e) throws Exception {
        reservationID.setText("");
        userID.setText("");
        facilityID.setText("");
        date.setValue(null);
        duration.setText("");

    }

    public void handleReturnBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }


    private boolean saveReservation(Reservation reservation){
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        XStream xstream = new XStream(new DomDriver());
        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
            reservations = (ArrayList<Reservation>) is.readObject();
            is.close();
        }
        catch (Exception e){
            return false;
        }
        try {
            reservations.add(reservation);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));
            out.writeObject(reservations);
            out.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private List<Reservation> getReservations(){
        ArrayList<Reservation> reservations = null;
        XStream xstream = new XStream(new DomDriver());

        try{
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
            reservations = (ArrayList<Reservation>) is.readObject();
            is.close();
        }
        catch(Exception e){
            reservations =  new ArrayList<Reservation>();
        }
        return reservations;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshList();
    }


}
