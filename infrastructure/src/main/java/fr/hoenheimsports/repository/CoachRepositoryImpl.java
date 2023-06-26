package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;
import fr.hoenheimsports.repository.entity.CoachEntityRepository;
import fr.hoenheimsports.repository.entity.game.CoachEntity;
import fr.hoenheimsports.service.mapper.CoachMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class CoachRepositoryImpl implements CoachRepository {
    private final CoachEntityRepository coachEntityRepositoryJPA;
    private final CoachMapper coachMapper;

    public CoachRepositoryImpl(CoachEntityRepository coachEntityRepositoryJPA, CoachMapper coachMapper) {
        this.coachEntityRepositoryJPA = coachEntityRepositoryJPA;
        this.coachMapper = coachMapper;
    }

    @Override
    public Optional<Coach> findCoachByKeys(String name) {
        Optional<CoachEntity> optionalCoachEntity = this.coachEntityRepositoryJPA.findByName(name);
        return  optionalCoachEntity.map(this.coachMapper::coachEntityToCoach);
    }
}
