package fr.hoenheimsports.gamedomain.exception;

public class RefereeAlreadyExistsException extends Exception{
    public RefereeAlreadyExistsException() {
        super("Referee already exists");
    }
}
