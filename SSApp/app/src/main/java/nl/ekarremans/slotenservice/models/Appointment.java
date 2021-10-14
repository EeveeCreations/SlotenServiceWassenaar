package nl.ekarremans.slotenservice.models;

public class Appointment {
//    Attributes

    private String service;
    private String customerName;
    private String time;
    private float price;
    private boolean isPaid;
    private boolean isCompleted;


//    Constructor

    public Appointment(String service, String customerName, String time, float price, boolean isPaid, boolean isCompleted) {
        this.service = service;
        this.customerName = customerName;
        this.time = time;
        this.price = price;
        this.isPaid = isPaid;
        this.isCompleted = isCompleted;
    }


//    Setters and Getters


    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
