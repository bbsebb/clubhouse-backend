package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.repository.game.entity.*;
import fr.hoenheimsports.repository.game.entity.game.*;
import fr.hoenheimsports.service.game.mapper.GameMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class GameRepositoryImpl implements GameRepository {
    private final GameMapper gameMapper;
    private final GameEntityRepository gameEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final ClubEntityRepository clubEntityRepository;
    private final CoachEntityRepository coachEntityRepository;
    private final CompetitionEntityRepository competitionEntityRepository;
    private final HallEntityRepository halleEntityRepository;
    private final RefereeEntityRepository refereeEntityRepository;
    private final PoolEntityRepository poolEntityRepository;
    private final SeasonEntityRepository seasonEntityRepository;
    private final TeamEntityRepository teamEntityRepository;


    public GameRepositoryImpl(GameMapper gameMapper, GameEntityRepository gameRepositoryJPA, CategoryEntityRepository categoryEntityRepository, ClubEntityRepository clubEntityRepository, CoachEntityRepository coachEntityRepository, CompetitionEntityRepository competitionEntityRepository, HallEntityRepository halleEntityRepository, RefereeEntityRepository refereeEntityRepository, PoolEntityRepository poolEntityRepository, SeasonEntityRepository seasonEntityRepository, TeamEntityRepository teamEntityRepository) {
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

        Set<HallEntity> halleEntitiesHomeTeam = this.clubEntityRepository.findById( gameEntity.getHomeTeam().getClub().getCode())
                .map(clubEntity -> new HashSet<>(clubEntity.getHalles()))
                .orElse(new HashSet<>());
        halleEntitiesHomeTeam.add(gameEntity.getHalle());
        gameEntity.getHomeTeam().getClub().getHalles().addAll(halleEntitiesHomeTeam);

        gameEntity.setHomeTeam(this.saveTeam(gameEntity.getHomeTeam()));

        Set<HallEntity> halleEntitiesVisitingTeam = this.clubEntityRepository.findById( gameEntity.getVisitingTeam().getClub().getCode())
                .map(clubEntity -> new HashSet<>(clubEntity.getHalles()))
                .orElse(new HashSet<>());
        gameEntity.getVisitingTeam().getClub().getHalles().addAll(halleEntitiesVisitingTeam);

        gameEntity.setVisitingTeam(this.saveTeam(gameEntity.getVisitingTeam()));
        gameEntity.setReferees(this.saveReferees(gameEntity.getReferees()));
        gameEntity = this.gameEntityRepository.save(gameEntity);
        return this.gameMapper.gameEntityToGame(gameEntity);
    }

    private RefereesEntity saveReferees(RefereesEntity refereesEntity) {
        RefereeEntity designatedReferee1 = this.refereeEntityRepository.save(refereesEntity.getDesignatedReferee1());
        RefereeEntity designatedReferee2 = this.refereeEntityRepository.save(refereesEntity.getDesignatedReferee2());

        // The couple name/id must be unique and name isn't a key
        if (designatedReferee1.getName().equals(refereesEntity.getOfficiatingReferee1().getName())) {
            refereesEntity.setOfficiatingReferee1(designatedReferee1);
        } else if (designatedReferee2.getName().equals(refereesEntity.getOfficiatingReferee1().getName())) {
            refereesEntity.setOfficiatingReferee1(refereesEntity.getOfficiatingReferee2());
        } else {
            refereesEntity.setOfficiatingReferee1(this.refereeEntityRepository.save(refereesEntity.getOfficiatingReferee1()));
        }

        if (designatedReferee1.getName().equals(refereesEntity.getOfficiatingReferee1().getName())) {
            refereesEntity.setOfficiatingReferee2(designatedReferee1);
        } else if (designatedReferee2.getName().equals(refereesEntity.getOfficiatingReferee2().getName())) {
            refereesEntity.setOfficiatingReferee2(refereesEntity.getOfficiatingReferee2());
        } else {
            refereesEntity.setOfficiatingReferee2(this.refereeEntityRepository.save(refereesEntity.getOfficiatingReferee2()));
        }

        refereesEntity.setDesignatedReferee1(designatedReferee1);
        refereesEntity.setDesignatedReferee2(designatedReferee2);

        return refereesEntity;
    }

    private TeamEntity saveTeam(TeamEntity teamEntity) {
        teamEntity.setCategory(this.categoryEntityRepository.save(teamEntity.getCategory()));
        teamEntity.setClub(this.clubEntityRepository.save(teamEntity.getClub()));
        teamEntity.setCoach(this.coachEntityRepository.save(teamEntity.getCoach()));
        this.teamEntityRepository.findByClubAndGenderAndCategoryAndNumber(
                teamEntity.getClub(),
                teamEntity.getGender(),
                teamEntity.getCategory(),
                teamEntity.getNumber()).ifPresent(
                        previousTeamEntity -> teamEntity.setId(previousTeamEntity.getId())
        );
        return this.teamEntityRepository.save(teamEntity);
    }


    @Override
    public Optional<Game> findById(String gameCode) {
        return this.gameEntityRepository.findById(gameCode).map(gameMapper::gameEntityToGame);
    }

    @Override
    public List<Game> findAll() {
        return this.gameEntityRepository.findAll().stream().map(gameMapper::gameEntityToGame).toList();
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void delete(String gameCode) {

    }
}
