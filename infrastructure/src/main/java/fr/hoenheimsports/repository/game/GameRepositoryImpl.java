package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.repository.game.entity.*;
import fr.hoenheimsports.repository.game.entity.game.*;
import fr.hoenheimsports.service.game.mapper.GameMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {
    private final GameMapper gameMapper;
    private final GameEntityRepository gameEntityRepository;
    private final TeamEntityRepository teamEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final CoachEntityRepository coachEntityRepository;
    private final RefereeEntityRepository refereeEntityRepository;
    private final ClubEntityRepository clubEntityRepository;



    public GameRepositoryImpl(GameMapper gameMapper, GameEntityRepository gameRepository, TeamEntityRepository teamEntityRepository, CategoryEntityRepository categoryEntityRepository, CoachEntityRepository coachEntityRepository, RefereeEntityRepository refereeEntityRepository, ClubEntityRepository clubEntityRepository) {
        this.gameMapper = gameMapper;
        this.gameEntityRepository = gameRepository;
        this.teamEntityRepository = teamEntityRepository;
        this.categoryEntityRepository = categoryEntityRepository;
        this.coachEntityRepository = coachEntityRepository;
        this.refereeEntityRepository = refereeEntityRepository;
        this.clubEntityRepository = clubEntityRepository;
    }

    @Override
    public Game save(Game game) {
        return this.gameMapper.gameEntityToGame(this.gameEntityRepository.save(this.gameMapper.gameToGameEntity(game)));
    }

    private TeamEntity mergeTeam(TeamEntity team) {
        if(team.getId() !=null && this.teamEntityRepository.existsById(team.getId())) {
            return this.teamEntityRepository.save(team);
        }
        return team;
    }

    private CategoryEntity mergeCategory(CategoryEntity category) {
        if(category.getName() !=null && this.categoryEntityRepository.existsById(category.getName())) {
            return this.categoryEntityRepository.save(category);
        }
        return category;
    }

    private CoachEntity mergeCoach(CoachEntity coach) {
        if(coach.getId() !=null && this.coachEntityRepository.existsById(coach.getId())) {
            return this.coachEntityRepository.save(coach);
        }
        return coach;
    }

    private RefereeEntity mergeReferee(RefereeEntity referee) {
        if(referee.getId() !=null && this.refereeEntityRepository.existsById(referee.getId())) {
            return this.refereeEntityRepository.save(referee);
        }
        return referee;
    }

    private ClubEntity mergeClub(ClubEntity club) {
        if(club.getCode() !=null && this.clubEntityRepository.existsById(club.getCode())) {
            return this.clubEntityRepository.save(club);
        }
        return club;
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
