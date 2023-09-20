package fr.hoenheimsports.gamedomain.exception;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException() {
        super("Category not found");
    }
}
