package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Address;

public class AddressBuilder {
    public static AddressBuilder builder() {
        return new AddressBuilder();
    }
    private String street;
    private int postalCode;
    private String city;

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withPostalCode(int postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public Address build() {
        return new Address(street, postalCode, city);
    }
}
