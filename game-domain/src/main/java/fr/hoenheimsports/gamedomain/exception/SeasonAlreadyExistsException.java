package fr.hoenheimsports.gamedomain.exception;

public class SeasonAlreadyExistsException extends Exception{
    public SeasonAlreadyExistsException() {
        super("Season already exists");
    }
}
