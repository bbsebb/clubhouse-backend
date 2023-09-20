package fr.hoenheimsports.gamedomain.exception;

public class ClubNotFoundException extends Exception{
    public ClubNotFoundException() {
        super("Club not found");
    }
}
