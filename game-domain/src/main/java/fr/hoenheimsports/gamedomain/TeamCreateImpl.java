package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.TeamCreate;
import fr.hoenheimsports.gamedomain.exception.TeamAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;

import java.util.UUID;
@DomainService
public class TeamCreateImpl implements TeamCreate {
    private final TeamRepository teamRepository;

    public TeamCreateImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team create(Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) throws TeamAlreadyExistsException {
        if(this.teamRepository.findByKeys(club,gender,category,number).isPresent() ) {
            throw new TeamAlreadyExistsException();
        }
        return this.teamRepository.save(new Team(UUID.randomUUID(),category,gender,number,club,teamsColor,coach));
    }
}
