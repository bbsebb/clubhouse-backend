package fr.hoenheimsports.gamedomain.exception;

public class RefereeNotFoundException extends Exception{
    public RefereeNotFoundException() {
        super("Referee not found");
    }
}
