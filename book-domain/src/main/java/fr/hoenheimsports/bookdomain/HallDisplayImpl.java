package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.HallDisplay;
import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.spi.HallRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DomainService
public class HallDisplayImpl implements HallDisplay {
    private final HallRepository halleRepository;

    public HallDisplayImpl(HallRepository halleRepository) {
        this.halleRepository = halleRepository;
    }

    @Override
    public List<Hall> findAll() {
        return this.halleRepository.findAll();
    }

    @Override
    public Optional<Hall> findById(UUID id) {
        return this.halleRepository.findById(id);
    }


}
