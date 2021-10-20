package nl.ekarremans.slotenservice.models;

class Service {

//    Attributen
    private String name;
    private float price;


//    Constructoor
    public Service(String name, int price) {
        this.name = name;
        this.price = price;
    }

//    getter Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
