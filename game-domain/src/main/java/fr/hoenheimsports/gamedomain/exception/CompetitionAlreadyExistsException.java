package fr.hoenheimsports.gamedomain.exception;

public class CompetitionAlreadyExistsException extends Exception{
    public CompetitionAlreadyExistsException() {
        super("Competition already exists");
    }
}
