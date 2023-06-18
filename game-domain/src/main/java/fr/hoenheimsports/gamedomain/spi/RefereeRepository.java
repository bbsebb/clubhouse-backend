package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.Referee;

import java.util.Optional;

public interface RefereeRepository {
    public Optional<Referee> findRefereeByKeys(String name);
}
