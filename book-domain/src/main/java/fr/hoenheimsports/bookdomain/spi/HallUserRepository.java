package fr.hoenheimsports.bookdomain.spi;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;

import java.util.Optional;
import java.util.UUID;

public interface HallUserRepository {
    Optional<Tenant> findRegisteredUserById(UUID id);
    Optional<AssociationHallUser> findUnregisteredUserById(UUID id);
    HallUser save(Tenant tenant);
    HallUser save(AssociationHallUser associationHallUser);
}
