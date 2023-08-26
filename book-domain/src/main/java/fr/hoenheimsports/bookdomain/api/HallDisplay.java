package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Hall;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HallDisplay {
    List<Hall> findAll();
    Optional<Hall> findById(UUID id);
}
