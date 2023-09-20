package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.spi.CompetitionRepository;
import fr.hoenheimsports.repository.game.entity.CompetitionEntityRepository;
import fr.hoenheimsports.service.game.mapper.CompetitionMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CompetitionRepositoryImpl implements CompetitionRepository {
    private final CompetitionEntityRepository competitionEntityRepository;
    private final CompetitionMapper competitionMapper;

    public CompetitionRepositoryImpl(CompetitionEntityRepository competitionEntityRepository, CompetitionMapper competitionMapper) {
        this.competitionEntityRepository = competitionEntityRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public Competition save(Competition competition) {
        return this.competitionMapper.competitionEntityToCompetition(this.competitionEntityRepository.save(this.competitionMapper.competitionToCompetitionEntity(competition)));
    }

    @Override
    public Optional<Competition> findById(String name) {
        return this.competitionEntityRepository.findById(name).map(this.competitionMapper::competitionEntityToCompetition);
    }
}
