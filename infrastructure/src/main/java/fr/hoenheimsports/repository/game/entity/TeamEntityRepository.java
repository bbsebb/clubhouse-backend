package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.CategoryEntity;
import fr.hoenheimsports.repository.game.entity.game.ClubEntity;
import fr.hoenheimsports.repository.game.entity.game.GenderEntity;
import fr.hoenheimsports.repository.game.entity.game.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamEntityRepository extends JpaRepository<TeamEntity, UUID> {
    public Optional<TeamEntity> findByClubAndGenderAndCategoryAndNumber(ClubEntity club, GenderEntity gender, CategoryEntity category, int number);
    public List<TeamEntity> findByCategory_NameAndGender(String name,GenderEntity genderEntity);
}