package fr.hoenheimsports.bookdomain.exception;

public class HallUserNotFoundException extends RuntimeException {
    public HallUserNotFoundException() {
    }

    public HallUserNotFoundException(String message) {
        super(message);
    }
}
