package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.HallDisplay;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;

import java.util.Set;

@DomainService
public class HalleDisplayImpl implements HallDisplay {
    private final HallRepository halleRepository;

    public HalleDisplayImpl(HallRepository halleRepository) {
        this.halleRepository = halleRepository;
    }

    @Override
    public Set<Hall> findAll() {
        return this.halleRepository.findAll();
    }

    @Override
    public Set<Hall> findByClubCode(String clubCode) {
        return this.halleRepository.findByClubId(clubCode);
    }
}
