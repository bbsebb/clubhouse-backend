package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Address;

public class AddressBuilder {
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
