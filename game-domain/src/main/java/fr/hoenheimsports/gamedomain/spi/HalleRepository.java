package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Halle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HalleRepository {
    Optional<Halle> findHallByKeys(String name, String address, int cp, String city);

    Optional<Halle> findHallById(String id);

    Set<Halle> findAllHalles();

    Set<Halle> findByClubCode(String clubId);



}
