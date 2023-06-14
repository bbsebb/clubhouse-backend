package fr.hoenheimsports.model;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("the phone number must be a sequence of digits.");
        }
    }
}
