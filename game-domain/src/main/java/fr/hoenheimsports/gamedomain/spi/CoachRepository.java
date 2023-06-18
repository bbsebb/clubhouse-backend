package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Coach;

import java.util.Optional;

public interface CoachRepository {
    public Optional<Coach> findCoachByKeys(String name);
}
