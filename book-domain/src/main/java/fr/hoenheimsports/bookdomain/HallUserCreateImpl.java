package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.HallUserCreate;
import fr.hoenheimsports.bookdomain.exception.HallUserNotFoundException;
import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.bookdomain.spi.HallUserRepository;

import java.util.UUID;

@DomainService
public class HallUserCreateImpl implements HallUserCreate {
    private final HallUserRepository hallUserRepository;

    public HallUserCreateImpl(HallUserRepository hallUserRepository) {
        this.hallUserRepository = hallUserRepository;
    }


    @Override
    public HallUser createUnregisteredUser(String username, String email, Address address) {
        return this.hallUserRepository.save(new Tenant(UUID.randomUUID(),username,email,address));
    }

    @Override
    public HallUser createRegisteredUser(UUID id) {
        return this.hallUserRepository.save(this.hallUserRepository.findRegisteredUserById(id).orElseThrow(HallUserNotFoundException::new));
    }
}
