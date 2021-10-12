package nl.ekarremans.slotenservice.models;

import java.util.ArrayList;

class Calender {
//    Attributes
    private String day;
    private ArrayList<Appointment> appointments;


    public Calender(String day, ArrayList<Appointment> appointments) {
        this.day = day;
        this.appointments = appointments;
    }


}
