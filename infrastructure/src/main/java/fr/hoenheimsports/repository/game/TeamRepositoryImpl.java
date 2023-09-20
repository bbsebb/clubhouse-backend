package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;
import fr.hoenheimsports.repository.game.entity.TeamEntityRepository;
import fr.hoenheimsports.repository.game.entity.game.GenderEntity;
import fr.hoenheimsports.repository.game.entity.game.TeamEntity;
import fr.hoenheimsports.service.game.mapper.CategoryMapper;
import fr.hoenheimsports.service.game.mapper.ClubMapper;
import fr.hoenheimsports.service.game.mapper.GenderMapper;
import fr.hoenheimsports.service.game.mapper.TeamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
    private final TeamEntityRepository teamEntityRepository;
    private final TeamMapper teamMapper;
    private final ClubMapper clubMapper;
    private final CategoryMapper categoryMapper;
    private final GenderMapper genderMapper;

    public TeamRepositoryImpl(TeamEntityRepository teamEntityRepositoryJPA, TeamMapper teamMapper, ClubMapper clubMapper, CategoryMapper categoryMapper, GenderMapper genderMapper) {
        this.teamEntityRepository = teamEntityRepositoryJPA;
        this.teamMapper = teamMapper;
        this.clubMapper = clubMapper;
        this.categoryMapper = categoryMapper;
        this.genderMapper = genderMapper;
    }

    @Override
    public Optional<Team> findByKeys(Club club, Gender gender, Category category, int number) {
        Optional<TeamEntity> optionalTeamEntity = this.teamEntityRepository
                .findByClubAndGenderAndCategoryAndNumber(
                        this.clubMapper.clubToClubEntity(club),
                        this.genderMapper.mapGenderToGenderEntity(gender),
                        this.categoryMapper.categoryToCategoryEntity(category),
                        number);
        return  optionalTeamEntity.map(this.teamMapper::teamEntityToTeam);
    }

    @Override
    public List<Team> findAll() {
        return this.teamEntityRepository.findAll().stream().map(teamMapper::teamEntityToTeam).toList();
    }

    @Override
    public List<Team> findByCategoryAndGender(String categoryName, String genderName) {
        GenderEntity genderEntity = GenderEntity.valueOf(genderName);
        return this.teamEntityRepository.findByCategory_NameAndGender(categoryName, genderEntity).stream().map(teamMapper::teamEntityToTeam).toList();
    }

    @Override
    public Optional<Team> findById(String id) {
        return this.teamEntityRepository.findById(UUID.fromString(id)).map(teamMapper::teamEntityToTeam);
    }

    @Override
    public Team save(Team team) {
        return this.teamMapper.teamEntityToTeam(this.teamEntityRepository.save(this.teamMapper.teamToTeamEntity(team)));
    }
}
