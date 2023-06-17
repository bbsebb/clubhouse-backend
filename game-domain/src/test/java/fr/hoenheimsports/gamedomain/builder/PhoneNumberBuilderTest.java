package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.PhoneNumber;
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