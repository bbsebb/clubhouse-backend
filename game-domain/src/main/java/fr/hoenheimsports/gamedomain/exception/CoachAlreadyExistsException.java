package fr.hoenheimsports.gamedomain.exception;

public class CoachAlreadyExistsException extends Exception{
    public CoachAlreadyExistsException() {
        super("Coach already exists");
    }
}
