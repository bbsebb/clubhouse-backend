package fr.hoenheimsports.bookdomain.exception;

public class TimeslotAlreadyBooked extends RuntimeException {
    public TimeslotAlreadyBooked() {
    }

    public TimeslotAlreadyBooked(String message) {
        super(message);
    }
}
