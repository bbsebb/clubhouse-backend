package fr.hoenheimsports.gamedomain.exception;

public class HallAlreadyExistsException extends Exception{
    public HallAlreadyExistsException() {
        super("Hall already exists");
    }
}
