package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Embeddable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Embeddable
public class FDMEEntity {
    private URL url;

    public FDMEEntity() {
    }

    public FDMEEntity(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FDMEEntity that)) return false;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
