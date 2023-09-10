package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.HallUserCreate;
import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.bookdomain.spi.HallUserRepository;

import java.util.Optional;
import java.util.UUID;

@DomainService
public class HallUserCreateImpl implements HallUserCreate {
    private final HallUserRepository hallUserRepository;

    public HallUserCreateImpl(HallUserRepository hallUserRepository) {
        this.hallUserRepository = hallUserRepository;
    }


    @Override
    public HallUser createUnregisteredUser(String username, String email, Address address, boolean isMembre) {
        return this.hallUserRepository.save(new Tenant(UUID.randomUUID(), username, email, address, isMembre));
    }

    @Override
    public HallUser createRegisteredUser(UUID id, String username, String email) {
        return this.hallUserRepository.save(new AssociationHallUser(id, username, email));
    }


    @Override
    public Optional<HallUser> findByUd(UUID id) {
        return this.hallUserRepository.findById(id);
    }


}
