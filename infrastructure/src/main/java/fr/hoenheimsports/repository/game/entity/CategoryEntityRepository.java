package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, String> {
}