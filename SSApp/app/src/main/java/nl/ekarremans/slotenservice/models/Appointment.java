package nl.ekarremans.slotenservice.models;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import nl.ekarremans.slotenservice.AppointmentActivity;
import nl.ekarremans.slotenservice.Observers.AppointmentObserver;
import nl.ekarremans.slotenservice.Observers.AppointmentObserverble;

public class Appointment implements Serializable, AppointmentObserverble {
    //    Attributes
    private String id;
    private String service;
    private String phone;
    private String customerName;
    private String time;
    private String date;
    private float price;
    private boolean isPaid;
    private boolean isCompleted;
    private ArrayList<AppointmentObserver> observers = new ArrayList<>();


//    Constructor

    public Appointment(String service, String customerName, String phone, String time, String date, float price, boolean isPaid, boolean isCompleted) {
        this.service = service;
        this.customerName = customerName;
        this.phone = phone;
        this.time = time;
        this.date = date;
        this.price = price;
        this.isPaid = isPaid;
        this.isCompleted = isCompleted;
    }

    //    Firebase
    public Appointment() {
    }

//    Setters and Getters


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
//        Update();
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted)
//            throws FileNotFoundException, URISyntaxException
    {
        this.isCompleted = isCompleted;
//        Update();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void registerObserver(AppointmentObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(AppointmentObserver observer) {
        this.observers.remove(observer);
    }


    @Override
    public void Update() throws FileNotFoundException, URISyntaxException {
        for (AppointmentObserver observer : this.observers) {
            observer.update(this);
        }

    }
}
