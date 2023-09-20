package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;
import fr.hoenheimsports.repository.game.entity.CoachEntityRepository;
import fr.hoenheimsports.repository.game.entity.game.CoachEntity;
import fr.hoenheimsports.service.game.mapper.CoachMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CoachRepositoryImpl implements CoachRepository {
    private final CoachEntityRepository coachEntityRepository;
    private final CoachMapper coachMapper;

    public CoachRepositoryImpl(CoachEntityRepository coachEntityRepositoryJPA, CoachMapper coachMapper) {
        this.coachEntityRepository = coachEntityRepositoryJPA;
        this.coachMapper = coachMapper;
    }

    @Override
    public Optional<Coach> findByKeys(String name) {
        Optional<CoachEntity> optionalCoachEntity = this.coachEntityRepository.findByName(name);
        return  optionalCoachEntity.map(this.coachMapper::coachEntityToCoach);
    }

    @Override
    public Optional<Coach> findById(UUID id) {
        return this.coachEntityRepository.findById(id).map(this.coachMapper::coachEntityToCoach);
    }

    @Override
    public Coach save(Coach coach) {
        return this.coachMapper.coachEntityToCoach(this.coachEntityRepository.save(this.coachMapper.coachToCoachEntity(coach)));
    }
}
