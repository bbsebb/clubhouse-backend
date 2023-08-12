package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;
import fr.hoenheimsports.repository.game.entity.RefereeEntityRepository;
import fr.hoenheimsports.repository.game.entity.game.RefereeEntity;
import fr.hoenheimsports.service.mapper.RefereeMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RefereeRepositoryImpl implements RefereeRepository {
    private final RefereeEntityRepository refereeEntityRepository;
    private final RefereeMapper refereeMapper;

    public RefereeRepositoryImpl(RefereeEntityRepository refereeEntityRepositoryJPA, RefereeMapper refereeMapper) {
        this.refereeEntityRepository = refereeEntityRepositoryJPA;
        this.refereeMapper = refereeMapper;
    }

    @Override
    public Optional<Referee> findRefereeByKeys(String name) {
        Optional<RefereeEntity> optionalRefereeEntity = this.refereeEntityRepository.findByName(name);
        return  optionalRefereeEntity.map(this.refereeMapper::refereeEntityToReferee);
    }

}
