package fr.hoenheimsports.gamedomain.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
/**
 * Represents an FDME (Feuille de Match Électronique) characterized by its URL.
 * <p>
 * This record provides a way to reference a digital match sheet by its URL.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code url} must not be {@code null}.</li>
 * </ul>
 */
public record FDME(URL url) {

    /**
     * A constant representing an unknown FDME.
     */
    public static final FDME UNKNOWN;

    static {
        try {
            UNKNOWN = new FDME(URL.of(URI.create("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/"), null));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Constructs an instance of {@code FDME} after validating the provided URL.
     */
    public FDME {
        Objects.requireNonNull(url,"url should be not null");
    }
}
