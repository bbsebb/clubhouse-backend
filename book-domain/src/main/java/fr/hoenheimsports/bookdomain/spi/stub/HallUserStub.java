package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.bookdomain.spi.HallUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class HallUserStub implements HallUserRepository {

    private final Map<UUID, Tenant> tenants;
    private final Map<UUID, AssociationHallUser> associationHallUsers;

    public HallUserStub() {
        this(new HashMap<>(), new HashMap<>());
    }

    public HallUserStub(Map<UUID, Tenant> tenants, Map<UUID, AssociationHallUser> associationHallUsers) {
        this.tenants = tenants;
        this.associationHallUsers = associationHallUsers;
    }

    @Override
    public Optional<Tenant> findRegisteredUserById(UUID id) {
        return Optional.ofNullable(this.tenants.get(id));
    }

    @Override
    public Optional<AssociationHallUser> findUnregisteredUserById(UUID id) {
        return Optional.ofNullable(this.associationHallUsers.get(id));
    }

    @Override
    public Optional<HallUser> findById(UUID id) {
        HallUser hallUser = this.tenants.get(id);
        if (hallUser == null) {
            hallUser = this.associationHallUsers.get(id);
        }
        return Optional.ofNullable(hallUser);
    }

    @Override
    public HallUser save(Tenant tenant) {
         this.tenants.put(tenant.getId(), tenant);
        return tenant;
    }

    @Override
    public HallUser save(AssociationHallUser associationHallUser) {
        this.associationHallUsers.put(associationHallUser.getId(), associationHallUser);
        return associationHallUser;
    }
}
