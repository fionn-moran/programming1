package application.model;

import application.Reservation;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingModel {
    private ArrayList<Reservation> reservations;
    public BookingModel() {

        reservations = new ArrayList<Reservation>();

    }



    public void addReservation(String reservationID, String userID, String facilityID, LocalDate date, String duration) {

    Reservation reservation = new Reservation(reservationID, userID, facilityID, date, duration);

    reservations.add(reservation);
}


    public boolean updateReservation(String reservationID, String userID, String facilityID, LocalDate date, String duration) {

        Reservation reservation = new Reservation(reservationID, userID,facilityID,date,duration);

        int i=0;
        for (Reservation item:reservations){
            if (item.getReservationID() == reservationID){
                reservations.set(i,reservation);
                return true;
            }
            i++;
        }
        return false;
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("reservations.xml"));
        out.writeObject(reservations);
        out.close();
    }


    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("reservations.xml"));
        reservations = (ArrayList<Reservation>) is.readObject();
        is.close();
    }

    public boolean removeReservation(int reservationID1) {
        return false;
    }

}