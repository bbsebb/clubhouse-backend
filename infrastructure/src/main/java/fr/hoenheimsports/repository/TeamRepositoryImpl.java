package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Gender;
import fr.hoenheimsports.gamedomain.model.Team;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;
import fr.hoenheimsports.repository.entity.TeamEntityRepository;
import fr.hoenheimsports.repository.entity.game.TeamEntity;
import fr.hoenheimsports.service.mapper.CategoryMapper;
import fr.hoenheimsports.service.mapper.ClubMapper;
import fr.hoenheimsports.service.mapper.GenderMapper;
import fr.hoenheimsports.service.mapper.TeamMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class TeamRepositoryImpl implements TeamRepository {
    private final TeamEntityRepository teamEntityRepositoryJPA;
    private final TeamMapper teamMapper;
    private final ClubMapper clubMapper;
    private final CategoryMapper categoryMapper;
    private final GenderMapper genderMapper;

    public TeamRepositoryImpl(TeamEntityRepository teamEntityRepositoryJPA, TeamMapper teamMapper, ClubMapper clubMapper, CategoryMapper categoryMapper, GenderMapper genderMapper) {
        this.teamEntityRepositoryJPA = teamEntityRepositoryJPA;
        this.teamMapper = teamMapper;
        this.clubMapper = clubMapper;
        this.categoryMapper = categoryMapper;
        this.genderMapper = genderMapper;
    }

    @Override
    public Optional<Team> findTeamByKeys(Club club, Gender gender, Category category, int number) {
        Optional<TeamEntity> optionalTeamEntity = this.teamEntityRepositoryJPA
                .findByClubAndGenderAndCategoryAndNumber(
                        this.clubMapper.clubToClubEntity(club),
                        this.genderMapper.mapGenderToGenderEntity(gender),
                        this.categoryMapper.categoryToCategoryEntity(category),
                        number);
        return  optionalTeamEntity.map(this.teamMapper::teamEntityToTeam);
    }
}
