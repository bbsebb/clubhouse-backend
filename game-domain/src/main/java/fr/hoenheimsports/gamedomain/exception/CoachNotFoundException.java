package fr.hoenheimsports.gamedomain.exception;

public class CoachNotFoundException extends Exception{
    public CoachNotFoundException() {
        super("Coach not found");
    }
}
