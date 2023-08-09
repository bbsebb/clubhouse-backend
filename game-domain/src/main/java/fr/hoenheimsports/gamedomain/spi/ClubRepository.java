package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Gender;
import fr.hoenheimsports.gamedomain.model.Team;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {

    List<Club> findAllClub();
    Optional<Club> findByCode(String code);
}
