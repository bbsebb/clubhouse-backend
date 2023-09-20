package fr.hoenheimsports.gamedomain.exception;

public class CategoryAlreadyExistsException extends Exception {
    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
}
