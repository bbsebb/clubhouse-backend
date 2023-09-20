package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.TeamUpdate;
import fr.hoenheimsports.gamedomain.exception.TeamNotFoundException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;

import java.util.Optional;
@DomainService
public class TeamUpdateImpl implements TeamUpdate {
    private final TeamRepository teamRepository;

    public TeamUpdateImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team update(Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) throws TeamNotFoundException {
        Optional<Team> team = this.teamRepository.findByKeys(club,gender,category,number);
        if(team.isEmpty()) {
            throw new TeamNotFoundException();
        }
        return this.teamRepository.save(new Team(team.get().getId(),team.get().getCategory(),team.get().getGender(),team.get().getNumber(),team.get().getClub(),teamsColor,coach));
    }
}
