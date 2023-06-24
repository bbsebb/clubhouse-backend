package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.*;

import java.util.Optional;

public interface TeamRepository {
    Optional<Team> findTeamByKeys(Club club, Gender gender, Category category, int number);
}