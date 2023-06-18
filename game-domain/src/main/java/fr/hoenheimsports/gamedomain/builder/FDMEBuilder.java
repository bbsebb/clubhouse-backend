package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.FDME;

import java.net.MalformedURLException;
import java.net.URL;

public class FDMEBuilder {
    public static FDMEBuilder builder() {
        return new FDMEBuilder();
    }
    private URL url;

    public FDMEBuilder withUrl(URL url) {
        this.url = url;
        return this;
    }

    public FDMEBuilder withUrl(String url) {

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public FDME build() {
        return new FDME(url);
    }
}
