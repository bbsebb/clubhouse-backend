package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.repository.entity.*;
import fr.hoenheimsports.repository.entity.game.*;
import fr.hoenheimsports.service.mapper.GameMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GameRepositoryImpl implements GameRepository {
    private final GameMapper gameMapper;
    private final GameEntityRepository gameEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final ClubEntityRepository clubEntityRepository;
    private final CoachEntityRepository coachEntityRepository;
    private final CompetitionEntityRepository competitionEntityRepository;
    private final HalleEntityRepository halleEntityRepository;
    private final RefereeEntityRepository refereeEntityRepository;
    private final PoolEntityRepository poolEntityRepository;
    private final SeasonEntityRepository seasonEntityRepository;
    private final TeamEntityRepository teamEntityRepository;





    public GameRepositoryImpl(GameMapper gameMapper, GameEntityRepository gameRepositoryJPA, CategoryEntityRepository categoryEntityRepository, ClubEntityRepository clubEntityRepository, CoachEntityRepository coachEntityRepository, CompetitionEntityRepository competitionEntityRepository, HalleEntityRepository halleEntityRepository, RefereeEntityRepository refereeEntityRepository, PoolEntityRepository poolEntityRepository, SeasonEntityRepository seasonEntityRepository, TeamEntityRepository teamEntityRepository) {
        this.gameMapper = gameMapper;
        this.gameEntityRepository = gameRepositoryJPA;
        this.categoryEntityRepository = categoryEntityRepository;
        this.clubEntityRepository = clubEntityRepository;
        this.coachEntityRepository = coachEntityRepository;
        this.competitionEntityRepository = competitionEntityRepository;
        this.halleEntityRepository = halleEntityRepository;
        this.refereeEntityRepository = refereeEntityRepository;
        this.poolEntityRepository = poolEntityRepository;
        this.seasonEntityRepository = seasonEntityRepository;
        this.teamEntityRepository = teamEntityRepository;
    }

    @Override
    public Game save(Game game) {
        GameEntity gameEntity = this.gameMapper.gameToGameEntity(game);
        gameEntity.getCompetition().setPool(this.poolEntityRepository.save(gameEntity.getCompetition().getPool()));
        gameEntity.setCompetition(this.competitionEntityRepository.save(gameEntity.getCompetition()));
        gameEntity.setSeason(this.seasonEntityRepository.save(gameEntity.getSeason()));
        gameEntity.setHalle(this.halleEntityRepository.save(gameEntity.getHalle()));
        gameEntity.setHomeTeam(this.saveTeam(gameEntity.getHomeTeam()));
        gameEntity.setVisitingTeam(this.saveTeam(gameEntity.getVisitingTeam()));
        gameEntity.setReferees(this.saveReferees(gameEntity.getReferees()));
        gameEntity = this.gameEntityRepository.save(gameEntity);
        return this.gameMapper.gameEntityToGame(gameEntity);
    }

    private RefereesEntity saveReferees(RefereesEntity refereesEntity){
        refereesEntity.setDesignatedReferee1(this.refereeEntityRepository.save(refereesEntity.getDesignatedReferee1()));
        refereesEntity.setDesignatedReferee2(this.refereeEntityRepository.save(refereesEntity.getDesignatedReferee2()));
        refereesEntity.setOfficiatingReferee1(this.refereeEntityRepository.save(refereesEntity.getOfficiatingReferee1()));
        refereesEntity.setOfficiatingReferee2(this.refereeEntityRepository.save(refereesEntity.getOfficiatingReferee2()));
        return refereesEntity;
    }

    private TeamEntity saveTeam(TeamEntity teamEntity) {
        teamEntity.setCategory(this.categoryEntityRepository.save(teamEntity.getCategory()));
        teamEntity.setClub(this.clubEntityRepository.save(teamEntity.getClub()));
        teamEntity.setCoach(this.coachEntityRepository.save(teamEntity.getCoach()));
        return this.teamEntityRepository.save(teamEntity);
    }

    @Override
    public Game findById(String gameCode) {
        return null;
    }

    @Override
    public List<Game> findAll() {
        return null;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void delete(String gameCode) {

    }
}
