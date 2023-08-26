package fr.hoenheimsports.repository.booking.entity.booking;

import jakarta.persistence.Embeddable;

import java.util.Objects;


@Embeddable
public class AddressEntity {
    private String street;
    private int cp;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressEntity that)) return false;
        return cp == that.cp && Objects.equals(street, that.street) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, cp, city);
    }
}
