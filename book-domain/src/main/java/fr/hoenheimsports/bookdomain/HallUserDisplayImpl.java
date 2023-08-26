package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.HallUserDisplay;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.spi.HallUserRepository;

import java.util.Optional;
import java.util.UUID;
@DomainService
public class HallUserDisplayImpl implements HallUserDisplay {
    private final HallUserRepository hallUserRepository;

    public HallUserDisplayImpl(HallUserRepository hallUserRepository) {
        this.hallUserRepository = hallUserRepository;
    }

    @Override
    public Optional<HallUser> findById(UUID id) {
        return this.hallUserRepository.findById(id);
    }
}
