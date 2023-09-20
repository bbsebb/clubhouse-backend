package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.PoolCreate;
import fr.hoenheimsports.gamedomain.exception.PoolAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;
import fr.hoenheimsports.gamedomain.spi.PoolRepository;
@DomainService
public class PoolCreateImpl implements PoolCreate {
    private final PoolRepository poolRepository;

    public PoolCreateImpl(PoolRepository poolRepository) {
        this.poolRepository = poolRepository;
    }

    @Override
    public Pool create(String code, String name, Competition competition)   {
        return this.poolRepository.findById(code)
                .orElseGet(() -> (this.poolRepository.save(new Pool(code, name,competition))));
    }
}
