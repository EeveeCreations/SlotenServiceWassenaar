package nl.ekarremans.slotenservice.models;

public class Appointment {
//    Attributes
    private String id;
    private String service;
    private String customerName;
    private String phone;
    private String time;
    private String date;
    private float price;
    private boolean isPaid;
    private boolean isCompleted;


//    Constructor



    //    Firebase
    public Appointment() {
    }

//    Setters and Getters

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

//    Getters en Setters


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
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
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
}
