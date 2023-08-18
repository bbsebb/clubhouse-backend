package fr.hoenheimsports.bookdomain.spi;

import fr.hoenheimsports.bookdomain.model.Booking;

public interface EmailProvider {
    void sendEmail(String to, Booking booking);
    String findRecipient();
}
