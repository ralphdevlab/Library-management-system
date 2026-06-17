public class Patron {

    private String patronID;
    private String name;
    private String address;
    private double fineAmount;

    //Constructor - runs when we create a new patron object
    public Patron(String patronID, String name, String address, double fineAmount) {
        this.patronID = patronID;
        this.name = name;
        this.address = address;
        this.fineAmount = fineAmount;
    }

    //Getters - let other class READ the data
    public String getPatronID() {
        return patronID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    // toString - controls how a Patron looks when printed
    @Override
    public String toString() {
        return "ID: " + patronID +
                "|| Name: " + name +
                "|| Address: " + address +
                "|| Fine: " + fineAmount;
    }
}
