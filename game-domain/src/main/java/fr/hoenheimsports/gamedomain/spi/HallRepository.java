package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Hall;

import java.util.Optional;
import java.util.Set;

public interface HallRepository {
    Optional<Hall> findHallByKeys(String name, String address, int cp, String city);

    Optional<Hall> findHallById(String id);

    Set<Hall> findAllHalls();

    Set<Hall> findByClubCode(String clubId);



}
