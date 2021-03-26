package application.controllers;

import application.Main;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import application.Reservation;
import application.model.BookingModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {

        BookingModel bookingModel;

        @FXML private TextField reservationID;
        @FXML private TextField facilityID;
        @FXML private TextField userID;
        @FXML private DatePicker date;
        @FXML private TextField duration;
        @FXML private TextArea txtAreaFeedback;
        @FXML ListView<Reservation> list;

        private Reservation thisReservation;

        private void refreshList()
        {
                list.getItems().clear();
                List<Reservation> items = getReservations();
                for (Reservation item: items) {
                        list.getItems().add(item); }
        }

        public void handleCreateBtn(ActionEvent e) throws Exception {
                if (reservationID.getText().length() == 0 || userID.getText().length() == 0) {
                        txtAreaFeedback.setText("reservation and user id must be at least 1 character \n");
                }
                if(facilityID.getText().length()<3 ){
                        txtAreaFeedback.setText("facility id must be at least 3 characters");
                }
                else if(saveReservation(reservationID.getText(), userID.getText(), facilityID.getText(), date.getValue(), duration.getText())) {
                        txtAreaFeedback.setText("Successful Booking");
                        //Main.set_pane(0);
                }
                refreshList();
        }

        public void handleReadBtn(ActionEvent e) {
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

        private boolean saveReservation(String reservationID, String userID, String facilityID, LocalDate date, String duration) {
                ArrayList<Reservation> reservations = new ArrayList<>();
                XStream xstream = new XStream(new DomDriver());

                try {
                        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
                        reservations = (ArrayList<Reservation>) is.readObject();
                        is.close();
                }

                catch(FileNotFoundException e) {
                        reservations =  new ArrayList<Reservation>();
                        txtAreaFeedback.setText("New Password File");
                }

                catch (Exception e) {
                        txtAreaFeedback.setText("Error accessing Password File");
                        return false;
                }

                try {
                        Reservation reservation = new Reservation(reservationID, userID, facilityID, date, duration);
                        reservations.add(reservation);
                        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));
                        out.writeObject(reservations);
                        out.close();
                }
                catch (Exception e) {
                        txtAreaFeedback.setText("Error writing to Password File");
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

        public void handleReturnBtn(ActionEvent e) throws Exception {
                Main.set_pane(4);
        }
}

