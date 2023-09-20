package fr.hoenheimsports.gamedomain.exception;

public class TeamNotFoundException extends  Exception{
    public TeamNotFoundException() {
        super("Team not found");
    }
}
