package fr.hoenheimsports.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public record FDME(URL url) {
    public static final FDME UNKNOWN;

    static {
        try {
            UNKNOWN = new FDME(new URL("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public FDME {
        Objects.requireNonNull(url,"url should be not null");
    }
}
