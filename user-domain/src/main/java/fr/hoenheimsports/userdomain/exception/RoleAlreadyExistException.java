package fr.hoenheimsports.userdomain.exception;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException() {
    }

    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
