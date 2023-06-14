package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberBuilderTest {
    @Test
    public void testPhoneNumberBuilder() {
        PhoneNumberBuilder phoneNumberBuilder = new PhoneNumberBuilder();
        String expectedPhoneNumber = "1234567890";

        PhoneNumber phoneNumber = phoneNumberBuilder
                .withPhoneNumber(expectedPhoneNumber)
                .build();

        assertEquals(expectedPhoneNumber, phoneNumber.phoneNumber());
    }
}