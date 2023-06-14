package fr.hoenheimsports.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    @Test
    public void testConstructorWithInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("ABC123"));
    }
}