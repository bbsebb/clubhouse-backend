package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.PhoneNumber;

public class PhoneNumberBuilder {
    public static PhoneNumberBuilder builder() {
        return new PhoneNumberBuilder();
    }
    private String phoneNumber;

    public PhoneNumberBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PhoneNumber build() {
        return new PhoneNumber(phoneNumber);
    }
}
