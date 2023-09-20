package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.CoachCreate;
import fr.hoenheimsports.gamedomain.exception.CoachAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;

import java.util.UUID;
@DomainService
public class CoachCreateImpl implements CoachCreate {
    private final CoachRepository coachRepository;

    public CoachCreateImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach create(String name, String phoneNumber) throws CoachAlreadyExistsException {
        return this.create(name, new PhoneNumber(phoneNumber));
    }

    @Override
    public Coach create(String name, PhoneNumber phoneNumber) throws CoachAlreadyExistsException {
        if(this.coachRepository.findByKeys(name).isPresent()) {
            throw new CoachAlreadyExistsException();
        }
        return this.coachRepository.save(new Coach(UUID.randomUUID(),name, phoneNumber));
    }
}
