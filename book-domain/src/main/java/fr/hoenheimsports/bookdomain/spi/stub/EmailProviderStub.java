package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.spi.EmailProvider;

import java.util.ArrayList;
import java.util.List;

public class EmailProviderStub implements EmailProvider {

    private final List<Email> emails;

    public EmailProviderStub() {
        this.emails = new ArrayList<>();
    }

    @Override
    public void sendEmail(String to, Booking booking) {
        this.emails.add(new Email(to, this.createBodyEmail(booking.getState())));
    }

    @Override
    public String findRecipient() {
        return "recipient@hoenheimsports.fr";
    }

    public List<Email> getEmails() {
        return emails;
    }

    private String createBodyEmail(BookingState  state) {
        return switch (state) {
            case ACCEPTED -> "booking accepted";
            case PENDING -> "booking pending";
            case VALIDATED -> "booking validated";
            case CANCELED -> "booking canceled";
            case FINISHED -> "booking finished";
            case REFUSED -> "booking refused";
        };
    }

    public static class Email {
        private final String to;
        private final String message;

        public Email(String s, String message) {
            to = s;
            this.message = message;
        }

        public String getTo() {
            return to;
        }

        public String getMessage() {
            return message;
        }
    }
}
