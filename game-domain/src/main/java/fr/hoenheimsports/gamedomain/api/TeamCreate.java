package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.TeamAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.*;

public interface TeamCreate {
    Team create(Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) throws TeamAlreadyExistsException;
}
