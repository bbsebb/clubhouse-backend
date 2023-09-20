package fr.hoenheimsports.gamedomain.exception;

public class PoolNotFoundException extends Exception{
    public PoolNotFoundException() {
        super("Pool not found");
    }
}
