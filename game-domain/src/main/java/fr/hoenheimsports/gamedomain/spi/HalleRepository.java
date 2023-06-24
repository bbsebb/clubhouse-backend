package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Halle;

import java.util.Optional;

public interface HalleRepository {
    Optional<Halle> findHallByKeys(String name, String address, int cp, String city);
}
