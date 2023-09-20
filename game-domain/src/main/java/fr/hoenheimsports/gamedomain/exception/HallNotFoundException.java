package fr.hoenheimsports.gamedomain.exception;

public class HallNotFoundException extends Exception{
    public HallNotFoundException() {
        super("Hall not found");
    }
}
