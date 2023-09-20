package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FDMETest {
    @Test
    public void testConstructorValidInput() throws MalformedURLException {
        URL validURL = URL.of(URI.create("https://example.com"), null);
        FDME fdme = new FDME(validURL);

        assertEquals(validURL, fdme.url());
    }

    @Test
    public void testConstructorNullInput() {
        assertThrows(NullPointerException.class, () -> new FDME(null));
    }

    @Test
    public void testUnknownConstant() throws MalformedURLException {
        URL expectedURL = URL.of(URI.create("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/"), null);
        FDME unknown = FDME.UNKNOWN;

        assertEquals(expectedURL, unknown.url());
    }
}