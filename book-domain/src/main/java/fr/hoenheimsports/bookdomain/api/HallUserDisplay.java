package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.HallUser;

import java.util.Optional;
import java.util.UUID;

public interface HallUserDisplay {
    Optional<HallUser> findById(UUID id);
}
