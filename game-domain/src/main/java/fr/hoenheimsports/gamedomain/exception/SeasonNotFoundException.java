package fr.hoenheimsports.gamedomain.exception;

public class SeasonNotFoundException extends Exception{
    public SeasonNotFoundException() {
        super("Season not found");
    }
}
