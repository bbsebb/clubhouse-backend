package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.CoachUpdate;
import fr.hoenheimsports.gamedomain.exception.CoachNotFoundException;
import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;

import java.util.Optional;
@DomainService
public class CoachUpdateImpl implements CoachUpdate {
    private final CoachRepository coachRepository;

    public CoachUpdateImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach update(String name, String phoneNumber) throws CoachNotFoundException {
        return this.update(name, new PhoneNumber(phoneNumber));
    }

    @Override
    public Coach update(String name, PhoneNumber phoneNumber) throws CoachNotFoundException {
        Optional<Coach> coach = this.coachRepository.findByKeys(name);
        if(coach.isEmpty()) {
            throw new CoachNotFoundException();
        }
        return this.coachRepository.save(new Coach(coach.get().id(),name, phoneNumber));
    }
}
