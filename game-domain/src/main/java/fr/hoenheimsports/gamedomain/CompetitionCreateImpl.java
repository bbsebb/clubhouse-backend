package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.CompetitionCreate;
import fr.hoenheimsports.gamedomain.exception.CompetitionAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;
import fr.hoenheimsports.gamedomain.spi.CompetitionRepository;
@DomainService
public class CompetitionCreateImpl implements CompetitionCreate {
    private final CompetitionRepository competitionRepository;

    public CompetitionCreateImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition create(String name)   {
        return competitionRepository.findById(name)
                .orElseGet(() -> competitionRepository.save(new Competition(name)));
    }
}
