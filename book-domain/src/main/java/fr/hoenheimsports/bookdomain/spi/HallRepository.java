package fr.hoenheimsports.bookdomain.spi;

import fr.hoenheimsports.bookdomain.model.Hall;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HallRepository {
    List<Hall> findAll();

    Optional<Hall> findById(UUID id);
}
