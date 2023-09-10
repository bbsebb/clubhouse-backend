package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;

public interface EmailService {

    /**
     * Send an email to the user who booked the book
     * @param booking the booking to notify
     */
    void sendEmailNotifyBookingToUser(Booking booking);

    /**
     * Send an email to the recipient of the book
     * @param booking the booking to notify
     */
    void sendEmailNotifyBookingToRecipient(Booking booking);
}
