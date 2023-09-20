package fr.hoenheimsports.gamedomain.exception;

public class TeamAlreadyExistsException extends Exception{
    public TeamAlreadyExistsException() {
        super("Team already exists");
    }
}
