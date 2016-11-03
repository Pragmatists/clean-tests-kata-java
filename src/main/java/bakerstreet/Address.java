package bakerstreet;

public class Address {

    public final String street;
    public final String city;
    public final PostCode postCode;

    public Address(String street, String city, PostCode postCode) {
        this.street = street;
        this.city = city;
        this.postCode = postCode;
    }

}
