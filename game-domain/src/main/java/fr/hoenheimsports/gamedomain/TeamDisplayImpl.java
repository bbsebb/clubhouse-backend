package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.TeamDisplay;
import fr.hoenheimsports.gamedomain.model.Team;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;

import java.util.List;
import java.util.Optional;

@DomainService
public class TeamDisplayImpl implements TeamDisplay {
    private final TeamRepository teamRepository;

    public TeamDisplayImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        return this.teamRepository.findAllTeam();
    }

    @Override
    public List<Team> findByCategoryAndGender(String categoryName, String genderName) {
        return this.teamRepository.findByCategoryAndGender(categoryName, genderName);
    }

    @Override
    public Optional<Team> findById(String id) {
        return this.teamRepository.findById(id);
    }
}
