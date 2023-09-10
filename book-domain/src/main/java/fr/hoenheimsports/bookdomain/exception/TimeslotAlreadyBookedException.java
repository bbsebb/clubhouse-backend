package fr.hoenheimsports.bookdomain.exception;

public class TimeslotAlreadyBookedException extends RuntimeException {
    public TimeslotAlreadyBookedException() {
    }

    public TimeslotAlreadyBookedException(String message) {
        super(message);
    }
}
