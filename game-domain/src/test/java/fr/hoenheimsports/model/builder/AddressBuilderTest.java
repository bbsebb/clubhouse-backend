package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBuilderTest {

    @Test
    public void testAddressBuilder() {
        AddressBuilder addressBuilder = new AddressBuilder();
        String expectedStreet = "123 Rue Principale";
        int expectedPostalCode = 75000;
        String expectedCity = "Paris";

        Address address = addressBuilder
                .withStreet(expectedStreet)
                .withPostalCode(expectedPostalCode)
                .withCity(expectedCity)
                .build();

        assertEquals(expectedStreet, address.street());
        assertEquals(expectedPostalCode, address.postalCode());
        assertEquals(expectedCity, address.city());
    }
}