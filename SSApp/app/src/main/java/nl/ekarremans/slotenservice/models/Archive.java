package nl.ekarremans.slotenservice.models;

import java.sql.Timestamp;
import java.util.ArrayList;

class Archive {

//    Attributes
    private ArrayList<Appointment> archivedAppointments;


//    Constructor

    public Archive(ArrayList<Appointment> archivedAppointments) {
        this.archivedAppointments = archivedAppointments;
    }


//    Setters and Getters


    public ArrayList<Appointment> getArchivedAppointments() {
        return archivedAppointments;
    }

    public void setArchivedAppointments(ArrayList<Appointment> archivedAppointments) {
        this.archivedAppointments = archivedAppointments;
    }
}
