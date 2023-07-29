package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.CategoryEntity;
import fr.hoenheimsports.repository.entity.game.ClubEntity;
import fr.hoenheimsports.repository.entity.game.GenderEntity;
import fr.hoenheimsports.repository.entity.game.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamEntityRepository extends JpaRepository<TeamEntity, UUID> {
    public Optional<TeamEntity> findByClubAndGenderAndCategoryAndNumber(ClubEntity club, GenderEntity gender, CategoryEntity category, int number);
    public List<TeamEntity> findByCategory_NameAndGender(String name,GenderEntity genderEntity);
}