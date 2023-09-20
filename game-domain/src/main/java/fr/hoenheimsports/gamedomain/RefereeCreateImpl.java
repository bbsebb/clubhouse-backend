package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.RefereeCreate;
import fr.hoenheimsports.gamedomain.exception.RefereeAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;

import java.util.UUID;
@DomainService
public class RefereeCreateImpl implements RefereeCreate {
    private final RefereeRepository refereeRepository;

    public RefereeCreateImpl(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Override
    public Referee create(String name)  {
        return this.refereeRepository.findByKeys(name)
                .orElseGet(() -> this.refereeRepository.save(new Referee(UUID.randomUUID(),name)));
    }
}
