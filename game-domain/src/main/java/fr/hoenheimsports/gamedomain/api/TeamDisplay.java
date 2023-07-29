package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamDisplay {

    List<Team> findAll();
    List<Team> findByCategoryAndGender(String categoryName, String genderName);

    Optional<Team> findById(String id);
}
