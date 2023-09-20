package fr.hoenheimsports.gamedomain.exception;

public class ClubAlreadyExistsException extends Exception{
    public ClubAlreadyExistsException() {
        super("Club already exists");
    }
}
