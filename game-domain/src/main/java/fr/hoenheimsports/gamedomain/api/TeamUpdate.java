package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.TeamAlreadyExistsException;
import fr.hoenheimsports.gamedomain.exception.TeamNotFoundException;
import fr.hoenheimsports.gamedomain.model.*;

public interface TeamUpdate {
    Team update(Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) throws TeamNotFoundException;

}
