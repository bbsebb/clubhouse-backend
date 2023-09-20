package fr.hoenheimsports.gamedomain.exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException() {
        super("Game not found");
    }
}
