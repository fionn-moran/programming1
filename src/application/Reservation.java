package application;

import jdk.jshell.execution.LoaderDelegate;
import java.time.LocalDate;

public class Reservation {

    private String reservationID;
    private String userID;
    private String facilityID;
    private LocalDate date;
    private String duration;

    public Reservation(String reservationID, String userID, String facilityID, LocalDate date, String duration) {

        this.reservationID = reservationID;
        this.userID = userID;
        this.facilityID = facilityID;
        this.date = date;
        this.duration = duration;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return reservationID + "-" + userID;
    }
}

