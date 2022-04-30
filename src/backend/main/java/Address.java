package src.backend.main.java;

public class Address {

    private int id;
    private int streetHouseNumber;
    private String postcode;
    private String addressSuplement;
    private String city;
    private String country;
    private String deliveryInstruction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreetHouseNumber() {
        return streetHouseNumber;
    }

    public void setStreetHouseNumber(int streetHouseNumber) {
        this.streetHouseNumber = streetHouseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddressSuplement() {
        return addressSuplement;
    }

    public void setAddressSuplement(String addressSuplement) {
        this.addressSuplement = addressSuplement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDeliveryInstruction() {
        return deliveryInstruction;
    }

    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }
}
