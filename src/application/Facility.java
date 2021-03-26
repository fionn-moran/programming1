package application;

import javafx.scene.control.TextField;

public class Facility {

    private String facilityID;
    private String facilityName;

    public Facility(String facilityID, String facilityName) {

        this.facilityID = facilityID;
        this.facilityName = facilityName;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return facilityID;
    }
}
