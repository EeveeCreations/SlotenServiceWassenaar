package nl.ekarremans.slotenservice.models;

public class Appointment {
//    Attributes
    private Long id;
    private String service;
    private String customerName;
    private String time;
    private String date;
    private float price;
    private boolean isPaid;
    private boolean isCompleted;


//    Constructor

    public Appointment( String customerName, String date, boolean isCompleted, boolean isPaid, float price, String service, String time) {
        this.customerName = customerName;
        this.date = date;
        this.isPaid = isPaid;
        this.isCompleted = isCompleted;
        this.price = price;
        this.service = service;
        this.time = time;



    }

    //    Firebase
    public Appointment() {
    }

//    Setters and Getters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
