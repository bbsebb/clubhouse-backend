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
        gameEntity.setHalle(this.mergeIfExistAlreadyOrSave(gameEntity.getHalle()));
        gameEntity.setHomeTeam(this.mergeIfExistAlreadyOrSave(gameEntity.getHomeTeam()));
        gameEntity.setVisitingTeam(this.mergeIfExistAlreadyOrSave(gameEntity.getVisitingTeam()));
        gameEntity.getReferees().setDesignatedReferee1(this.mergeIfExistAlreadyOrSave(gameEntity.getReferees().getDesignatedReferee1()));
        gameEntity.getReferees().setDesignatedReferee2(this.mergeIfExistAlreadyOrSave(gameEntity.getReferees().getDesignatedReferee2()));
        gameEntity.getReferees().setOfficiatingReferee1(this.mergeIfExistAlreadyOrSave(gameEntity.getReferees().getOfficiatingReferee1()));
        gameEntity.getReferees().setOfficiatingReferee2(this.mergeIfExistAlreadyOrSave(gameEntity.getReferees().getOfficiatingReferee2()));

        gameEntity = this.gameEntityRepository.save(gameEntity);
        return this.gameMapper.gameEntityToGame(gameEntity);
    }

    private RefereeEntity mergeIfExistAlreadyOrSave(RefereeEntity refereeEntity){
        this.refereeEntityRepository.findByName(refereeEntity.getName()).ifPresent(refereeEntityInRepository -> refereeEntity.setId(refereeEntityInRepository.getId()));

        var newRefereeEntity = this.refereeEntityRepository.findById(refereeEntity.getId()).map(refereeEntityInRepository -> {
            refereeEntityInRepository.setName(refereeEntity.getName());
            return refereeEntityInRepository;
        }).orElse(refereeEntity);

        return this.refereeEntityRepository.save(newRefereeEntity);
    }

    private TeamEntity mergeIfExistAlreadyOrSave(TeamEntity teamEntity) {
        teamEntity.setCategory(this.mergeIfExistAlreadyOrSave(teamEntity.getCategory()));
        teamEntity.setClub(this.mergeIfExistAlreadyOrSave(teamEntity.getClub()));
        teamEntity.setCoach(this.mergeIfExistAlreadyOrSave(teamEntity.getCoach()));
        this.teamEntityRepository.findByClubAndGenderAndCategoryAndNumber(teamEntity.getClub(), teamEntity.getGender(), teamEntity.getCategory(), teamEntity.getNumber())
                .ifPresent(teamEntityInRepository -> teamEntity.setId(teamEntityInRepository.getId()));
        var newTeamEntity = this.teamEntityRepository.findById(teamEntity.getId()).map(teamEntityInRepository -> {
            teamEntityInRepository.setCategory(teamEntity.getCategory());
            teamEntityInRepository.setClub(teamEntity.getClub());
            teamEntityInRepository.setCoach(teamEntity.getCoach());
            teamEntityInRepository.setTeamsColor(teamEntity.getTeamsColor());
            teamEntityInRepository.setGender(teamEntity.getGender());
            teamEntityInRepository.setNumber(teamEntity.getNumber());
            return teamEntityInRepository;
        }).orElse(teamEntity);

        return this.teamEntityRepository.save(newTeamEntity);
    }

    private HalleEntity mergeIfExistAlreadyOrSave(HalleEntity halleEntity) {
        this.halleEntityRepository.findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(
                halleEntity.getName(),
                halleEntity.getAddress().getStreet(),
                halleEntity.getAddress().getPostalCode(),
                halleEntity.getAddress().getCity())
                .ifPresent(hallEntityInRepository -> halleEntity.setId(hallEntityInRepository.getId()));

        return this.halleEntityRepository.save(halleEntity);
    }

    private CoachEntity mergeIfExistAlreadyOrSave(CoachEntity coachEntity) {
        this.coachEntityRepository.findByName(coachEntity.getName())
                .ifPresent(coachEntityInRepository -> coachEntity.setId(coachEntityInRepository.getId()));
        var newCoachEntity = this.coachEntityRepository
                .findById(coachEntity.getId()).map(coachEntityInRepository -> {
            coachEntityInRepository.setName(coachEntity.getName());
            coachEntityInRepository.setPhoneNumber(coachEntity.getPhoneNumber());
            return coachEntityInRepository;
        }).orElse(coachEntity);

        return this.coachEntityRepository.save(newCoachEntity);
    }

    private ClubEntity mergeIfExistAlreadyOrSave(ClubEntity clubEntity) {
        var newClubEntity = this.clubEntityRepository.findById(clubEntity.getCode()).map(clubEntityInRepository -> {
            clubEntityInRepository.setName(clubEntity.getName());
            return clubEntityInRepository;
        }).orElse(clubEntity);
        return this.clubEntityRepository.save(newClubEntity);
    }

    private CategoryEntity mergeIfExistAlreadyOrSave(CategoryEntity categoryEntity) {
        var newCategoryEntity = this.categoryEntityRepository.findById(categoryEntity.getName()).map(categoryEntityInRepository -> {
            categoryEntityInRepository.setName(categoryEntity.getName());
            return categoryEntityInRepository;
        }).orElse(categoryEntity);
        return this.categoryEntityRepository.save(newCategoryEntity);
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
