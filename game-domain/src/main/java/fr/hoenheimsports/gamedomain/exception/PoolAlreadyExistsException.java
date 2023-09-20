package fr.hoenheimsports.gamedomain.exception;

public class PoolAlreadyExistsException extends Exception{
    public PoolAlreadyExistsException() {
        super("Pool already exists");
    }
}
