package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.PhoneNumber;

public class PhoneNumberBuilder {
    private String phoneNumber;

    public PhoneNumberBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PhoneNumber build() {
        return new PhoneNumber(phoneNumber);
    }
}
