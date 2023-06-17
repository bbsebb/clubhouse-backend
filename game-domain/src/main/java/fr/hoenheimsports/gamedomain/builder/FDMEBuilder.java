package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.FDME;

import java.net.URL;

public class FDMEBuilder {
    private URL url;

    public FDMEBuilder withUrl(URL url) {
        this.url = url;
        return this;
    }

    public FDME build() {
        return new FDME(url);
    }
}
