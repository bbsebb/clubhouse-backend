package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.HalleDisplay;
import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.gamedomain.spi.HalleRepository;

import java.util.Set;

@DomainService
public class HalleDisplayImpl implements HalleDisplay {
    private final HalleRepository halleRepository;

    public HalleDisplayImpl(HalleRepository halleRepository) {
        this.halleRepository = halleRepository;
    }

    @Override
    public Set<Halle> findAll() {
        return this.halleRepository.findAllHalles();
    }

    @Override
    public Set<Halle> findByClubCode(String clubCode) {
        return this.halleRepository.findByClubCode(clubCode);
    }
}
