package fr.hoenheimsports.gamedomain.exception;

public class CompetitionNotFoundException extends   Exception{
    public CompetitionNotFoundException() {
        super("Competition not found");
    }
}
