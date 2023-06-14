package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.PhoneNumber;

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
