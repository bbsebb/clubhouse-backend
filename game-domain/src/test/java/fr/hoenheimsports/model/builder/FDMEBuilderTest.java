package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.FDME;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class FDMEBuilderTest {
    @Test
    public void testFDMEBuilder() throws MalformedURLException {
        FDMEBuilder fdmeBuilder = new FDMEBuilder();
        URL expectedUrl = new URL("https://example.com/fdme");

        FDME fdme = fdmeBuilder.withUrl(expectedUrl).build();

        assertEquals(expectedUrl, fdme.url());
    }
}