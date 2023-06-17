package fr.hoenheimsports.gamedomain.model;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("the phone code must be a sequence of digits.");
        }
    }
}
