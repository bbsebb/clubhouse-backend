package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PhoneNumberEntity {
    private String phoneNumber;

    public PhoneNumberEntity() {
    }

    public PhoneNumberEntity(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumberEntity that)) return false;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
