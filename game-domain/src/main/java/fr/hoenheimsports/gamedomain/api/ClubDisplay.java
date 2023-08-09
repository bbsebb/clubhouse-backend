package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Team;

import java.util.List;
import java.util.Optional;

public interface ClubDisplay {
    List<Club> findAll();
    Optional<Club> findByCode(String code);
}
