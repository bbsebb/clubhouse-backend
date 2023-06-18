package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.FDME;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class FDMEBuilderTest {
    @Test
    public void testBuilderMethod() {
        FDMEBuilder fdmeBuilder = FDMEBuilder.builder();
        assertNotNull(fdmeBuilder);
    }
    @Test
    public void testFDMEBuilder() throws MalformedURLException {
        FDMEBuilder fdmeBuilder = new FDMEBuilder();
        URL expectedUrl = new URL("https://example.com/fdme");

        FDME fdme = fdmeBuilder.withUrl(expectedUrl).build();

        assertEquals(expectedUrl, fdme.url());
    }

    @Test
    public void testWithInvalidUrl() {
        FDMEBuilder fdmeBuilder = new FDMEBuilder();
        String invalidUrl = "invalid-url";

        assertThrows(RuntimeException.class, () -> fdmeBuilder.withUrl(invalidUrl));
    }
}